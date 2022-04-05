package com.itmo.itogether.Controller;

import com.itmo.itogether.Domain.Information;
import com.itmo.itogether.Service.InformationService;
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
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HomeController {

    private final InformationService informationService;

    @Autowired
    public HomeController(InformationService informationService) {
        this.informationService = informationService;
    }

    @GetMapping("/popularity")
    public ResponseEntity<Object> Home() {
        List<Information> popularInfo = informationService.findPopularInfo();

        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        return new ResponseEntity<>(popularInfo, header, 200);
    }

}
