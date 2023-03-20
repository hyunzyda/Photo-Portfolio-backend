package com.kosmo.project.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kosmo.project.dao.AuthDAO;
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
    
}