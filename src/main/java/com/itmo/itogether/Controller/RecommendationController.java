package com.itmo.itogether.Controller;

import com.itmo.itogether.Service.JwtUtils;
import com.itmo.itogether.Service.PreferenceFieldService;
import com.itmo.itogether.Service.RecommendationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;

@RestController
@Slf4j
@RequestMapping(value = "/recommendation")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RecommendationController {
    private final JwtUtils jwtUtils;
    private final RecommendationService recommendationService;
    private final PreferenceFieldService preferenceFieldService;


    public RecommendationController(JwtUtils jwtUtils, RecommendationService recommendationService, PreferenceFieldService preferenceFieldService) {
        this.jwtUtils = jwtUtils;
        this.recommendationService = recommendationService;
        this.preferenceFieldService = preferenceFieldService;
    }

    @GetMapping()
    public ResponseEntity<Object> GetRecommendation(@RequestHeader(value = "token") String token) {
        if(jwtUtils.validateToken(token)) {
            log.info("인증완료");
            String memberId = "";
            memberId = jwtUtils.getIdFromToken(token);
            log.info("id={}", memberId);
            String field = preferenceFieldService.findField(Long.parseLong(memberId)).getField();
            HttpHeaders header = new HttpHeaders();
            header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
            if(field == null) {
                field = "sum_count";
                return new ResponseEntity<>(recommendationService.findRecommendation(field), header, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(recommendationService.findRecommendation(field), header, HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>("Fail", HttpStatus.BAD_REQUEST);
        }

    }
}
