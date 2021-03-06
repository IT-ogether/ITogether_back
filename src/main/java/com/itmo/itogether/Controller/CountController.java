package com.itmo.itogether.Controller;

import com.itmo.itogether.DTO.FavorFieldDTO;
import com.itmo.itogether.Domain.CountShare;
import com.itmo.itogether.Service.CountService;
import com.itmo.itogether.Service.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequestMapping(value = "/count")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CountController {

    private final JwtUtils jwtUtils;
    private final CountService countService;

    public CountController(JwtUtils jwtUtils, CountService countService) {
        this.jwtUtils = jwtUtils;
        this.countService = countService;
    }

    @PutMapping(value = "/{informationId}")
    public ResponseEntity<Object> UpdateCount(@RequestHeader(value = "token", required = false) String token, @PathVariable(value= "informationId") int informationId) {

        CountShare countShare = new CountShare();
        if(token == null) {
            // 비회원 공유 count_sum + 1
            countShare.setInformationId(informationId);
            countShare.setSumCount(countService.findCountByInformationId(informationId).get().getSumCount() + 1);
            countService.updateOnlySumCount(countShare);
            log.info("sumCount = {}", countShare.getSumCount());


        }
        else {
            if(jwtUtils.validateToken(token)) {
                log.info("검증완료");
                String memberId = "";
                memberId = jwtUtils.getIdFromToken(token);
                countShare.setInformationId(informationId);
                countShare.setSumCount(countService.findCountByInformationId(informationId).get().getSumCount() + 1);
                // 회원 선호분야 선택했으면 선호분야 가져오기
                //List<favorFieldDTO> selectFields = countService.findFields(memberId) 만들기
                // 리스트 하나씩 꺼내서 있으면 해당 분야 +1 없으면 그대로
                FavorFieldDTO fieldDTO = countService.findField(Long.parseLong(memberId));
                log.info("field = {}", fieldDTO.getField());

                if(!fieldDTO.getField().isEmpty()) {

                    if(fieldDTO.getField().equals("frontend") ) {
                        countShare.setFrontend(countService.findCountByInformationId(informationId).get().getFrontend() + 1);
                    } else if(fieldDTO.getField().equals("backend")) {
                        countShare.setBackend(countService.findCountByInformationId(informationId).get().getBackend() + 1);
                    } else if(fieldDTO.getField().equals("ios")) {
                        countShare.setIos(countService.findCountByInformationId(informationId).get().getIos() + 1);
                    } else if(fieldDTO.getField().equals("android")) {
                        countShare.setAndroid(countService.findCountByInformationId(informationId).get().getAndroid() + 1);
                    } else if(fieldDTO.getField().equals("ai")) {
                        countShare.setAi(countService.findCountByInformationId(informationId).get().getAi() + 1);
                    } else if(fieldDTO.getField().equals("cloud")) {
                        countShare.setCloud(countService.findCountByInformationId(informationId).get().getCloud() + 1);
                    } else if(fieldDTO.getField().equals("security")) {
                        countShare.setSecurity(countService.findCountByInformationId(informationId).get().getSecurity() + 1);
                    } else if(fieldDTO.getField().equals("blockchain")) {
                        countShare.setBlockchain(countService.findCountByInformationId(informationId).get().getBlockchain() + 1);
                    }

                    countService.updateCount(countShare);
                } else {
                    // 회원 선호분야 없으면 count_sum만 + 1
                    countService.updateOnlySumCount(countShare);
                }

            }
        }
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }



}
