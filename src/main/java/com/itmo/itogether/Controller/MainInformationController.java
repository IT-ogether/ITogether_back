package com.itmo.itogether.Controller;

import com.itmo.itogether.DTO.CriteriaDTO;
import com.itmo.itogether.DTO.MainInformationDTO;
import com.itmo.itogether.DTO.PageDTO;
import com.itmo.itogether.Service.InformationServiceImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/main-info")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MainInformationController {

    private InformationServiceImpl informationService;

    public MainInformationController(InformationServiceImpl informationService) {
        this.informationService = informationService;
    }
    int perPageNum = 5;

    @GetMapping("/club")
    public ResponseEntity<Object> findAllClub(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum) throws SQLException {
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        Map<String, Object> map = new HashMap<>();
        CriteriaDTO criteriaDTO = new CriteriaDTO(pageNum, perPageNum);
        PageDTO pageDTO = new PageDTO();
        pageDTO.setCri(criteriaDTO);
        pageDTO.setTotalCount(informationService.countInfo(1));

        List<MainInformationDTO> mainInformationDTOS = informationService.findAllClub((pageNum - 1) * perPageNum, criteriaDTO.getPerPageNum());

        map.put("mainInfo", mainInformationDTOS);
        map.put("pageDTO", pageDTO);

        return new ResponseEntity<>(map, header, HttpStatus.OK);
    }

    @GetMapping("/education")
    public ResponseEntity<Object> findAllEducation(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum) throws SQLException {
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        Map<String, Object> map = new HashMap<>();
        CriteriaDTO criteriaDTO = new CriteriaDTO(pageNum, perPageNum);
        PageDTO pageDTO = new PageDTO();
        pageDTO.setCri(criteriaDTO);
        pageDTO.setTotalCount(informationService.countInfo(2));

        List<MainInformationDTO> mainInformationDTOS = informationService.findAllEducation((pageNum - 1) * perPageNum, criteriaDTO.getPerPageNum());

        map.put("mainInfo", mainInformationDTOS);
        map.put("pageDTO", pageDTO);

        return new ResponseEntity<>(map, header, HttpStatus.OK);
    }

    @GetMapping("/seminar")
    public ResponseEntity<Object> findAllSeminar(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum) throws SQLException {
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        Map<String, Object> map = new HashMap<>();
        CriteriaDTO criteriaDTO = new CriteriaDTO(pageNum, perPageNum);
        PageDTO pageDTO = new PageDTO();
        pageDTO.setCri(criteriaDTO);
        pageDTO.setTotalCount(informationService.countInfo(3));

        List<MainInformationDTO> mainInformationDTOS = informationService.findAllSeminar((pageNum - 1) * perPageNum, criteriaDTO.getPerPageNum());

        map.put("mainInfo", mainInformationDTOS);
        map.put("pageDTO", pageDTO);

        return new ResponseEntity<>(map, header, HttpStatus.OK);
    }

    @GetMapping("/certificate")
    public ResponseEntity<Object> findAllCertificate(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum) throws SQLException {
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        Map<String, Object> map = new HashMap<>();
        CriteriaDTO criteriaDTO = new CriteriaDTO(pageNum, perPageNum);
        PageDTO pageDTO = new PageDTO();
        pageDTO.setCri(criteriaDTO);
        pageDTO.setTotalCount(informationService.countInfo(4));

        List<MainInformationDTO> mainInformationDTOS = informationService.findAllCertificate((pageNum - 1) * perPageNum, criteriaDTO.getPerPageNum());

        map.put("mainInfo", mainInformationDTOS);
        map.put("pageDTO", pageDTO);

        return new ResponseEntity<>(map, header, HttpStatus.OK);
    }

    @GetMapping("/kdt")
    public ResponseEntity<Object> findAllKdt(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum) throws SQLException {
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        Map<String, Object> map = new HashMap<>();
        CriteriaDTO criteriaDTO = new CriteriaDTO(pageNum, perPageNum);
        PageDTO pageDTO = new PageDTO();
        pageDTO.setCri(criteriaDTO);
        pageDTO.setTotalCount(informationService.countInfo(5));

        List<MainInformationDTO> mainInformationDTOS = informationService.findAllKdt((pageNum - 1) * perPageNum, criteriaDTO.getPerPageNum());

        map.put("mainInfo", mainInformationDTOS);
        map.put("pageDTO", pageDTO);

        return new ResponseEntity<>(map, header, HttpStatus.OK);
    }

    @GetMapping("/contest")
    public ResponseEntity<Object> findAllContest(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum) throws SQLException {
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        Map<String, Object> map = new HashMap<>();
        CriteriaDTO criteriaDTO = new CriteriaDTO(pageNum, perPageNum);
        PageDTO pageDTO = new PageDTO();
        pageDTO.setCri(criteriaDTO);
        pageDTO.setTotalCount(informationService.countInfo(6));

        List<MainInformationDTO> mainInformationDTOS = informationService.findAllContest((pageNum - 1) * perPageNum, criteriaDTO.getPerPageNum());

        map.put("mainInfo", mainInformationDTOS);
        map.put("pageDTO", pageDTO);

        return new ResponseEntity<>(map, header, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<Object> findByField(@RequestParam String field, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum) throws SQLException {
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        Map<String, Object> map = new HashMap<>();
        CriteriaDTO criteriaDTO = new CriteriaDTO(pageNum, perPageNum);
        PageDTO pageDTO = new PageDTO();
        pageDTO.setCri(criteriaDTO);
        pageDTO.setTotalCount(informationService.countFieldInfo(field));

        List<MainInformationDTO> mainInformationDTOS = informationService.findByField(field, (pageNum - 1) * perPageNum, criteriaDTO.getPerPageNum());

        map.put("mainInfo", mainInformationDTOS);
        map.put("pageDTO", pageDTO);

        return new ResponseEntity<>(map, header, HttpStatus.OK);
    }
}