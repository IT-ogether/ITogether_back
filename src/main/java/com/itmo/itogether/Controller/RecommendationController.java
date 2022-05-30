package com.itmo.itogether.Controller;

import com.itmo.itogether.DTO.RecommendationDTO;
import com.itmo.itogether.Service.JwtUtils;
import com.itmo.itogether.Service.PreferenceFieldService;
import com.itmo.itogether.Service.RecommendationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            List<RecommendationDTO> recommendationList = recommendationService.findRecommendation(field);
            for(int i = 0; i < 3; i++) {
                if(recommendationList.get(i).getCategoryName().equals("동아리")) {
                    log.info("b={}", recommendationList.get(i).getCategoryName());
                    recommendationList.get(i).setCategoryName("club");
                    log.info("a={}", recommendationList.get(i).getCategoryName());
                }
                else if(recommendationList.get(i).getCategoryName().equals("교육")) {
                    recommendationList.get(i).setCategoryName("education");
                }
                else if(recommendationList.get(i).getCategoryName().equals("세미나")) {
                    recommendationList.get(i).setCategoryName("seminar");
                }
                else if(recommendationList.get(i).getCategoryName().equals("자격증")) {
                    recommendationList.get(i).setCategoryName("certificate");
                }
                else if(recommendationList.get(i).getCategoryName().equals("국비지원")) {
                    recommendationList.get(i).setCategoryName("kdt");
                }
                else if(recommendationList.get(i).getCategoryName().equals("대회")) {
                    recommendationList.get(i).setCategoryName("contest");
                }

            }
            log.info("recommendation={}", recommendationList.get(0).getInformationId());
            log.info("recommendation={}", recommendationList.get(1).getInformationId());
            log.info("recommendation={}", recommendationList.get(0).getCategoryName());
            log.info("recommendation={}", recommendationList.get(1).getCategoryName());
            log.info("recommendation={}", recommendationList.get(2).getCategoryName());

            if(field == null) {
                field = "sum_count";
                return new ResponseEntity<>(recommendationList, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(recommendationList, HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>("Fail", HttpStatus.BAD_REQUEST);
        }

    }
}
