package com.itmo.itogether.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.itmo.itogether.DTO.*;
import com.itmo.itogether.Service.BookMarkService;
import com.itmo.itogether.Service.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(value = "/bookmarks")
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BookMarkController {

    @Autowired
    private BookMarkService bookMarkService;

    private final JwtUtils jwtUtils;

    @Autowired
    public BookMarkController(BookMarkService bookMarkService, JwtUtils jwtUtils) {
        this.bookMarkService = bookMarkService;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping()
    public ResponseEntity<Object> postBookMark(@RequestHeader(value = "token") String token, @RequestBody InformationIdDTO informationIdDTO) throws JsonProcessingException {

        System.out.println(token);
        if(jwtUtils.validateToken(token)) {
            System.out.println("검증완료");

            String memberId = "";
            memberId = jwtUtils.getIdFromToken(token);

            log.info("member={}", memberId);
            log.info("infoID={}", informationIdDTO.getInformationId());
            PostMarkRequest postMarkRequest = new PostMarkRequest(Long.valueOf(memberId), informationIdDTO.getInformationId());
            bookMarkService.postMark(postMarkRequest);

            return new ResponseEntity<>("Success", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Fail", HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping()
    public ResponseEntity<Object> getBookMark(@RequestHeader(value = "token") String token) throws SQLException {

        System.out.println(token);
        if(jwtUtils.validateToken(token)) {
            System.out.println("검증완료");

            String memberId = "";
            memberId = jwtUtils.getIdFromToken(token);
            System.out.println("memberId: " + memberId);

            GetMarkRequest getMarkRequest = new GetMarkRequest(Long.valueOf(memberId));

            List<GetMarkResponse> bookMarkInfo = bookMarkService.getMark(getMarkRequest);

            HttpHeaders header=new HttpHeaders();
            header.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

            return new ResponseEntity<>(bookMarkInfo,header,200);
        } else {
            return new ResponseEntity<>("Fail", HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/{informationId}")
    public ResponseEntity DeleteBookMark(@RequestHeader(value = "token") String token, @PathVariable(value= "informationId") int informationId) {

        System.out.println(token);
        if(jwtUtils.validateToken(token)) {
            System.out.println("검증완료");

            String memberId = "";
            memberId = jwtUtils.getIdFromToken(token);
            System.out.println("memberId: " + memberId);

            DeleteRequest deleteRequest = new DeleteRequest(Long.valueOf(memberId), informationId);
            bookMarkService.deleteMark(deleteRequest);

            return new ResponseEntity<>("Success", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Fail", HttpStatus.BAD_REQUEST);
        }

    }
}
