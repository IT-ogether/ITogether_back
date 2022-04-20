package com.itmo.itogether.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.itmo.itogether.Domain.Member;
import com.itmo.itogether.Service.JwtUtils;
import com.itmo.itogether.Service.MemberService;
import com.itmo.itogether.Service.RedisRefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoginController {

    private final MemberService ms;
    private final JwtUtils jwtUtils;

    private static int refreshTokenIndex = 0;

    @Autowired
    public LoginController(MemberService ms, JwtUtils jwtUtils) {
        this.ms = ms;
        this.jwtUtils = jwtUtils;
    }

    @Autowired
    private RedisRefreshTokenService redisRefreshTokenService;

    @PostMapping("/oauth/kakao/login")
    public ResponseEntity<Object> kakaoCallback(@RequestParam(value = "code", required = false) String code) throws JsonProcessingException, UnsupportedEncodingException {

        System.out.println("authorizationCode: " + code);
        System.err.println("authorizationCode: " + code);

        System.out.println("getAccessToken 진입 전");
        System.err.println("getAccessToken 진입 전");
        String accessToken = ms.getAccessToken(code);
        System.out.println("getAccessToken 진입 후 받아 온 accessToken: " + accessToken);
        System.err.println("getAccessToken 진입 후 받아 온 accessToken: " + accessToken);

        HashMap<String, Object> userInfo = ms.getUserInfo(accessToken);

        Member member = new Member();
        member.setId(Long.parseLong(userInfo.get("id").toString())-10000);
        member.setNickname(userInfo.get("nickname").toString());
        member.setEmail(userInfo.get("email").toString());
        member.setProfileImage(userInfo.get("profileImage").toString());

        Boolean isMemberPresent = ms.findMemberById(member.getId()).isPresent();

        if(isMemberPresent == false) {
            ms.join(member);
        }
        else {
            System.out.println("The member is already sign up");
        }

        String jwtAccessToken = jwtUtils.createJwt(member);
        String refreshToken = jwtUtils.createRefreshToken(member);

        redisRefreshTokenService.setRedisRefreshTokenValue(++refreshTokenIndex, refreshToken);
        System.out.println("refreshTokenIndex: " + refreshTokenIndex);
        System.out.println("refreshToken: " + refreshToken);

        HttpHeaders headers = new HttpHeaders();
        headers.set("jwtAccessToken", jwtAccessToken);
        headers.set("refreshTokenIndex", String.valueOf(refreshTokenIndex));

        System.out.println("headers" + headers);
        System.out.println("headers jwtAccessToken" + headers.get("jwtAccessToken"));
        System.out.println("headers refreshTokenIndex" + headers.get("refreshTokenIndex"));

        return new ResponseEntity<>("로그인 성공", headers, 200);
    }

//    @GetMapping("/refresh")
//    public Map<String, Object> Refresh(@RequestHeader(value="RefreshToken") String refreshToken) {
//
//        if(redisRefreshTokenService.isExistRefreshToken(refreshToken)) {
//            String id = jwtUtils.getIdFromToken(refreshToken);
//
//            Member member = new Member();
//            member.setId(ms.findMemberById(Long.parseLong(id)).get().getId());
//            member.setNickname(ms.findMemberById(Long.parseLong(id)).get().getNickname());
//            member.setEmail(ms.findMemberById(Long.parseLong(id)).get().getEmail());
//
//            String jwtAccessToken = jwtUtils.createJwt(member);
//
//            Map<String, Object> token = new HashMap<>();
//            token.put("jwtAcceessToken", jwtAccessToken);
//
//            return token;
//        }
//        else {
//            Map<String, Object> token = new HashMap<>();
//            token.put("fail", "fail");
//            return token;
//        }
//
//    }


}
