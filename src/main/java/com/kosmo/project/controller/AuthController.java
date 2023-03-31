package com.kosmo.project.controller;

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
        
        User user = new User(signUpRequest.getEmail(),signUpRequest.getPassword(), signUpRequest.getNickname(), signUpRequest.getPhone());
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
        
        // DB에서 이메일에 해당하는 사용자 정보(이메일,비밀번호) 가져오기
        User user = authDAO.getUserByEmail(email);
        
        if (user != null && user.getPassword().equals(password)){
            // JWT 토큰 생성
            String token = authDAO.createToken(email);
            return ResponseEntity.ok().body(token);
//          토큰을 헤더에 담아서 전달
//          HttpHeaders headers = new HttpHeaders();
//          headers.setBearerAuth(token);
//          return ResponseEntity.ok().headers(headers).build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 에러");
        }
    }
}