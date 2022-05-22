package com.itmo.itogether.Controller;

import com.itmo.itogether.Domain.Member;
import com.itmo.itogether.Service.JwtUtils;
import com.itmo.itogether.Service.RedisRefreshTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping(value = "/refresh")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class refreshTokenController {
    private final JwtUtils jwtUtils;
    private final RedisRefreshTokenService redisRefreshTokenService;

    public refreshTokenController(JwtUtils jwtUtils, RedisRefreshTokenService redisRefreshTokenService) {
        this.jwtUtils = jwtUtils;
        this.redisRefreshTokenService = redisRefreshTokenService;
    }

    @GetMapping()
    public ResponseEntity<Object> getRefreshTokenIndex(@RequestHeader(value="refreshTokenIndex") String refreshTokenIndex) {
        if(redisRefreshTokenService.isExistRefreshToken(Integer.parseInt(refreshTokenIndex))) {
            String refreshToken = redisRefreshTokenService.getRedisRefreshTokenValue(Integer.parseInt(refreshTokenIndex));
            Member member = new Member();
            String memberId = "";
            memberId = jwtUtils.getIdFromToken(refreshToken);
            member.setId(Long.parseLong(memberId));

            String jwtAccessToken = jwtUtils.createJwt(member);
            HttpHeaders headers = new HttpHeaders();
            headers.set("jwtAccessToken", jwtAccessToken);

            return new ResponseEntity<>("새 토큰 발급", headers, HttpStatus.OK);

        } else {
            return new ResponseEntity<>("유저 정보가 존재하지 않습니다.", HttpStatus.BAD_REQUEST);
        }

    }

}
