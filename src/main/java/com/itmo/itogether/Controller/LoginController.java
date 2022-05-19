package com.itmo.itogether.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.itmo.itogether.Domain.Member;
import com.itmo.itogether.Service.JwtUtils;
import com.itmo.itogether.Service.MemberService;
import com.itmo.itogether.Service.PreferenceFieldService;
import com.itmo.itogether.Service.RedisRefreshTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoginController {

    private final MemberService ms;
    private final JwtUtils jwtUtils;
    private RedisRefreshTokenService redisRefreshTokenService;
    private PreferenceFieldService preferenceFieldService;

    private static int refreshTokenIndex = 0;

    public LoginController(MemberService ms, JwtUtils jwtUtils, RedisRefreshTokenService redisRefreshTokenService, PreferenceFieldService preferenceFieldService) {
        this.ms = ms;
        this.jwtUtils = jwtUtils;
        this.redisRefreshTokenService = redisRefreshTokenService;
        this.preferenceFieldService = preferenceFieldService;
    }

    @PostMapping("/oauth/kakao/login")
    public ResponseEntity<Object> kakaoCallback(@RequestParam(value = "code", required = false) String code) throws JsonProcessingException, UnsupportedEncodingException {

        log.info("authorizationCode={}", code);

        String accessToken = ms.getAccessToken(code);
        log.info("getAccessToken 진입 후 받아 온 accessToken = {}", accessToken);

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
            log.info("The member is already sign up");
        }

        String jwtAccessToken = jwtUtils.createJwt(member);
        String refreshToken = jwtUtils.createRefreshToken(member);

        AtomicInteger count = new AtomicInteger(refreshTokenIndex);
        redisRefreshTokenService.setRedisRefreshTokenValue(count.getAndIncrement(), refreshToken);
        refreshTokenIndex = count.get();

        log.info("refreshTokenIndex = {}", refreshTokenIndex);
        log.info("refreshToken = {}" + refreshToken);

        HttpHeaders headers = new HttpHeaders();
        headers.set("jwtAccessToken", jwtAccessToken);
        headers.set("refreshTokenIndex", String.valueOf(refreshTokenIndex));
        headers.set("nickname", member.getNickname());
        headers.set("email", member.getNickname());
        headers.set("field", preferenceFieldService.findField(member.getId()).getField());


        log.info("headers = {}", headers);
        log.info("headers jwtAccessToken = {}", headers.get("jwtAccessToken"));
        log.info("headers refreshTokenIndex" + headers.get("refreshTokenIndex"));

        return new ResponseEntity<>("로그인 성공", headers, HttpStatus.OK);
    }
}
