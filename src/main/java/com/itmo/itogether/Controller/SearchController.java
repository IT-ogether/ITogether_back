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
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SearchController {
    private InformationServiceImpl informationService;

    public SearchController(InformationServiceImpl informationService) {
        this.informationService = informationService;
    }

    @GetMapping("/search")
    public ResponseEntity<Object> searchKeyword(@RequestParam String keyword, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum) throws SQLException {
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        int perPageNum = 5;
        Map<String, Object> map = new HashMap<>();
        CriteriaDTO criteriaDTO = new CriteriaDTO(pageNum, perPageNum);
        PageDTO pageDTO = new PageDTO();
        pageDTO.setCri(criteriaDTO);
        pageDTO.setTotalCount(informationService.countKeywordInfo(keyword));

        List<MainInformationDTO> mainInformationDTOS = informationService.searchKeyword(keyword, (pageNum - 1) * perPageNum, criteriaDTO.getPerPageNum());

        map.put("mainInfo", mainInformationDTOS);
        map.put("pageDTO", pageDTO);

        if (mainInformationDTOS.isEmpty()){
            return new ResponseEntity<>(map, header, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(map, header, HttpStatus.OK);
    }
}