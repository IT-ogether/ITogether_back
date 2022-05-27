package com.itmo.itogether.Controller;

import com.itmo.itogether.Domain.Information;
import com.itmo.itogether.Service.InformationServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.util.List;


@RestController
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HomeController {

    private final InformationServiceImpl informationService;

    @Autowired
    public HomeController(InformationServiceImpl informationService) {
        this.informationService = informationService;
    }

    @GetMapping("/popularity")
    public ResponseEntity<Object> Home() {
        log.info("popularity 정보");
        List<Information> popularInfo = informationService.findPopularInfo();

        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        return new ResponseEntity<>(popularInfo, header, 200);
    }

}
