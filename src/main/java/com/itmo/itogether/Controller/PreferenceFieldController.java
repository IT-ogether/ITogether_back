package com.itmo.itogether.Controller;

import com.itmo.itogether.DTO.PostField;
import com.itmo.itogether.Service.JwtUtils;
import com.itmo.itogether.Service.PreferenceFieldService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequestMapping(value = "/preference")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PreferenceFieldController {
    private final JwtUtils jwtUtils;
    private final PreferenceFieldService preferenceFieldService;

    public PreferenceFieldController(JwtUtils jwtUtils, PreferenceFieldService preferenceFieldService) {
        this.jwtUtils = jwtUtils;
        this.preferenceFieldService = preferenceFieldService;
    }

    @PostMapping()
    public ResponseEntity<Object> PostPreferenceField(@RequestHeader(value = "token") String token, @RequestParam(value = "field", required = false) String field) {
        if(jwtUtils.validateToken(token)) {
            log.info("인증완료");
            String memberId = "";
            memberId = jwtUtils.getIdFromToken(token);
            log.info("id={}", memberId);
            log.info("field={}", field);
            log.info("nowField={}", preferenceFieldService.findField(Long.parseLong(memberId)).getField());

            if(preferenceFieldService.findField(Long.parseLong(memberId)).getField() == null) {
                // 선택 없는 경우
                PostField postField = new PostField(Long.parseLong(memberId), field);
                preferenceFieldService.postField(postField);
            } else {
                // 선택한 분야 있는 경우 삭제 후 생성
                PostField deleteField = new PostField(Long.parseLong(memberId), preferenceFieldService.findField(Long.parseLong(memberId)).getField());
                preferenceFieldService.deleteField(deleteField);

                if(field != null) {
                    PostField postField = new PostField(Long.parseLong(memberId), field);
                    preferenceFieldService.postField(postField);
                }
            }

            return new ResponseEntity<>("Success", HttpStatus.OK);

        } else {
            return new ResponseEntity<>("Fail", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping()
    public ResponseEntity<Object> GetPreferenceField(@RequestHeader(value = "token") String token) {

        if(jwtUtils.validateToken(token)) {
            log.info("인증완료");
            String memberId = "";
            memberId = jwtUtils.getIdFromToken(token);
            log.info("id={}", memberId);

            return new ResponseEntity<>(preferenceFieldService.findField(Long.parseLong(memberId)), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Fail", HttpStatus.BAD_REQUEST);
        }
    }
}
