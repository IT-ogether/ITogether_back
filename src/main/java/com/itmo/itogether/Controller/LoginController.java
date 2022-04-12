package com.itmo.itogether.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.itmo.itogether.Domain.Member;
import com.itmo.itogether.Service.JwtUtils;
import com.itmo.itogether.Service.MemberService;
import com.itmo.itogether.Service.RedisRefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoginController {

    private final MemberService ms;
    private final JwtUtils jwtUtils;

    @Autowired
    public LoginController(MemberService ms, JwtUtils jwtUtils) {
        this.ms = ms;
        this.jwtUtils = jwtUtils;
    }

    @Autowired
    private RedisRefreshTokenService redisRefreshTokenService;

    @PostMapping("/oauth/kakao/login")
    public Map<String, Object> kakaoCallback(@RequestParam(value = "code", required = false) String code) throws JsonProcessingException, UnsupportedEncodingException {

        String accessToken = ms.getAccessToken(code);
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

        Map<String, Object> tokens = new HashMap<>();
        tokens.put("jwtAccessToken", jwtAccessToken);

        redisRefreshTokenService.setRedisRefreshTokenValue(refreshToken, member.getNickname());

        return tokens;
    }

    @GetMapping("/refresh")
    public Map<String, Object> Refresh(@RequestHeader(value="RefreshToken") String refreshToken) {

        if(redisRefreshTokenService.isExistRefreshToken(refreshToken)) {
            String id = jwtUtils.getIdFromToken(refreshToken);

            Member member = new Member();
            member.setId(ms.findMemberById(Long.parseLong(id)).get().getId());
            member.setNickname(ms.findMemberById(Long.parseLong(id)).get().getNickname());
            member.setEmail(ms.findMemberById(Long.parseLong(id)).get().getEmail());

            String jwtAccessToken = jwtUtils.createJwt(member);

            Map<String, Object> token = new HashMap<>();
            token.put("jwtAcceessToken", jwtAccessToken);

            return token;
        }
        else {
            Map<String, Object> token = new HashMap<>();
            token.put("fail", "fail");
            return token;
        }

    }


}
