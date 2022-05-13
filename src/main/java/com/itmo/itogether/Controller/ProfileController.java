package com.itmo.itogether.Controller;

import com.itmo.itogether.Service.JwtUtils;
import com.itmo.itogether.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProfileController {

    private final MemberService ms;
    private final JwtUtils jwtUtils;

    @Autowired
    public ProfileController(MemberService ms, JwtUtils jwtUtils) {
        this.ms = ms;
        this.jwtUtils = jwtUtils;
    }

    @GetMapping("/profile")
    public Map<String, Object> Profile(@RequestHeader(value = "token") String token) {

        System.out.println(token);
        if(jwtUtils.validateToken(token)) {
            System.out.println("검증완료");
            String nickName = "";
            String email = "";
            String profileImage = "";

            String id = jwtUtils.getIdFromToken(token);
            System.out.println(id);


            nickName = String.valueOf(ms.findMemberById(Long.parseLong(id)).get().getNickname());
            email = String.valueOf(ms.findMemberById(Long.parseLong(id)).get().getEmail());
            profileImage = String.valueOf(ms.findMemberById(Long.parseLong(id)).get().getProfileImage());

            Map<String, Object> result = new HashMap<>();
            result.put("nickName", nickName);
            result.put("email", email);
            result.put("profileImage", profileImage);

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
