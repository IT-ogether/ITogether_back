package com.itmo.itogether.Controller;

import com.itmo.itogether.Service.InformationServiceImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/detail-info")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DetailInformationController {
    private InformationServiceImpl informationService;

    public DetailInformationController(InformationServiceImpl informationService) {
        this.informationService = informationService;
    }

    @GetMapping(value = {"/club/{informationId}", "/education/{informationId}", "/seminar/{informationId}"
            , "/certificate/{informationId}", "/kdt/{informationId}", "/contest/{informationId}"})
    public ResponseEntity<Object> findByInfoId(@PathVariable int informationId) throws SQLException {
        Map<String, Object> map = new HashMap<>();
        map.put("detailInfo", informationService.findByInfoId(informationId));
        map.put("reviews", informationService.findReviewById(informationId));

        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        return new ResponseEntity<>(map, header, HttpStatus.OK);
    }
}