package com.kosmo.project.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kosmo.project.dao.UserDAO;
import com.kosmo.project.dto.User;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDAO userDao;
    
    // 모든 사용자 정보 조회
    @GetMapping("")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userDao.getAllUsers();
        if(users.size() > 0) {
            return ResponseEntity.ok(users);
        } else {
            return ResponseEntity.notFound().build();
        }
    }   
    
    // 사용자 정보 추가    
    @PostMapping("/add")
    public ResponseEntity<Void> addUser(@Valid @RequestBody User user) {
        boolean result = userDao.addUser(user);
        if(result) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }    
    
    // 내 정보 조회
    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<User> getUserInfo(Authentication authentication){
        User user = userDao.getUserByEmail(authentication.getName());
        return ResponseEntity.ok(user);
    }
    

    // 사용자 정보 수정
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable(value = "id") int id,@Valid @RequestBody User user) {
        boolean result = userDao.updateUser(id, user);
        if(result) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 사용자 정보 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable(value = "id") int id) {
        boolean result = userDao.deleteUser(id);
        if(result) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
