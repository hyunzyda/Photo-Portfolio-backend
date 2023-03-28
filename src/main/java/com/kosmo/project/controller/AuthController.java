package com.kosmo.project.controller;


import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kosmo.project.dao.AuthDAO;
import com.kosmo.project.dto.LoginRequest;
import com.kosmo.project.dto.SignupRequest;
import com.kosmo.project.dto.User;

@RestController
public class AuthController {
    @Autowired
    private AuthDAO authDAO;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (authDAO.existsByUsername(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(false);
        }

        User user = new User(signUpRequest.getEmail(), signUpRequest.getPassword(), signUpRequest.getNickname(), signUpRequest.getPhone());
        int result = authDAO.createUser(user);

        if (result > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        String email = request.getEmail();
        String password = request.getPassword();
        
        // 데이터베이스에서 이메일과 비밀번호 일치 여부 확인
        int count = authDAO.loginUser(email, password);
        
        if (count == 1) {
            // JWT 토큰 생성
            String token = authDAO.createToken(email);
//            HttpHeaders headers = new HttpHeaders();
//            headers.setBearerAuth(token); // 토큰을 헤더에 담아서 전달
//            return ResponseEntity.ok().headers(headers).build();
            return ResponseEntity.ok().body(token);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 에러");
        }
    }
}