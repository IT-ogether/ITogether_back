package com.itmo.itogether.Controller;

import com.itmo.itogether.DTO.MainInformationDTO;
import com.itmo.itogether.Service.InformationServiceImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SearchController {
    private InformationServiceImpl informationService;

    public SearchController(InformationServiceImpl informationService) {
        this.informationService = informationService;
    }

    @GetMapping("/{keyword}")
    public ResponseEntity<Object> searchKeyword(@PathVariable String keyword) throws SQLException {
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        List<MainInformationDTO> mainInformationDTOS = informationService.searchKeyword(keyword);

        return new ResponseEntity<>(mainInformationDTOS, header, 200);
    }
}