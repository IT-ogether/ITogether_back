package com.itmo.itogether.Controller;

import com.itmo.itogether.DTO.MainInformationDTO;
import com.itmo.itogether.Service.InformationServiceImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/main-info")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MainInformationController {

    private InformationServiceImpl informationService;

    public MainInformationController(InformationServiceImpl informationService) {
        this.informationService = informationService;
    }

    @GetMapping("/club")
    public ResponseEntity<Object> findAllClub() throws SQLException {
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        List<MainInformationDTO> mainInformationDTOS = informationService.findAllClub();

        return new ResponseEntity<>(mainInformationDTOS, header, 200);
    }

    @GetMapping("/education")
    public ResponseEntity<Object> findAllEducation() throws SQLException {
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        List<MainInformationDTO> mainInformationDTOS = informationService.findAllEducation();

        return new ResponseEntity<>(mainInformationDTOS, header, 200);
    }

    @GetMapping("/seminar")
    public ResponseEntity<Object> findAllSeminar() throws SQLException {
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        List<MainInformationDTO> mainInformationDTOS = informationService.findAllSeminar();

        return new ResponseEntity<>(mainInformationDTOS, header, 200);
    }

    @GetMapping("/certificate")
    public ResponseEntity<Object> findAllCertificate() throws SQLException {
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        List<MainInformationDTO> mainInformationDTOS = informationService.findAllCertificate();

        return new ResponseEntity<>(mainInformationDTOS, header, 200);
    }

    @GetMapping("/kdt")
    public ResponseEntity<Object> findAllKdt() throws SQLException {
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        List<MainInformationDTO> mainInformationDTOS = informationService.findAllKdt();

        return new ResponseEntity<>(mainInformationDTOS, header, 200);
    }

    @GetMapping("/contest")
    public ResponseEntity<Object> findAllContest() throws SQLException {
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        List<MainInformationDTO> mainInformationDTOS = informationService.findAllContest();

        return new ResponseEntity<>(mainInformationDTOS, header, 200);
    }
}