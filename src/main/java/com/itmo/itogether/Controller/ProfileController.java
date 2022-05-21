package com.itmo.itogether.Controller;

import com.itmo.itogether.DTO.GetMarkRequest;
import com.itmo.itogether.DTO.GetMarkResponse;
import com.itmo.itogether.Service.BookMarkService;
import com.itmo.itogether.Service.JwtUtils;
import com.itmo.itogether.Service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProfileController {

    private final MemberService ms;
    private final JwtUtils jwtUtils;
    private final BookMarkService bookMarkService;

    @Autowired
    public ProfileController(MemberService ms, JwtUtils jwtUtils, BookMarkService bookMarkService) {
        this.ms = ms;
        this.jwtUtils = jwtUtils;
        this.bookMarkService = bookMarkService;
    }

    @GetMapping("/profile")
    public Map<String, Object> Profile(@RequestHeader(value = "token") String token) throws SQLException {

        log.info(token);
        if(jwtUtils.validateToken(token)) {
            log.info("검증완료");
            String nickName = "";
            String email = "";
            String profileImage = "";

            String id = jwtUtils.getIdFromToken(token);

            nickName = String.valueOf(ms.findMemberById(Long.parseLong(id)).get().getNickname());
            email = String.valueOf(ms.findMemberById(Long.parseLong(id)).get().getEmail());
            profileImage = String.valueOf(ms.findMemberById(Long.parseLong(id)).get().getProfileImage());

            GetMarkRequest getMarkRequest = new GetMarkRequest(Long.parseLong(id));
            List<GetMarkResponse> bookMarkInfo = bookMarkService.getMark(getMarkRequest);

            Map<String, Object> result = new HashMap<>();
            result.put("nickName", nickName);
            result.put("email", email);
            result.put("profileImage", profileImage);
            result.put("bookMark", bookMarkInfo);

            return result;
        }
        else {
            // 이 부분 구현 필요
            Map<String, Object> fail = new HashMap<>();
            fail.put("fail", "fail");

            return fail;
        }
    }
}
