package com.kosmo.project.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kosmo.project.dao.UserDAO;
import com.kosmo.project.dto.User;
import com.kosmo.project.service.S3Service;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDAO userDao;
    @Autowired
	private S3Service s3Service;
    
    // 모든 사용자 정보 조회
    @GetMapping("/all")
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
    
    // 사용자 프로필사진 변경
    @PostMapping(value="/editProfile" ,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> editProfile(@Valid @ModelAttribute User user,@RequestParam(value="file", required = false) MultipartFile file){
    	String email = SecurityContextHolder.getContext().getAuthentication().getName();
    	String fileUrl = s3Service.saveFile(file);
    	user.setProImage(fileUrl);
    	boolean result = userDao.editProfile(email,user);
    	if(result) {
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}	
    }
    
    // 내 정보 조회
    @GetMapping("/me")
    public ResponseEntity<User> getUserInfo(Authentication authentication){
        User user = userDao.getUserByEmail(authentication.getName());
        return ResponseEntity.ok(user);
    }
    

    // 사용자 정보 수정
    @PutMapping("/update")
    public ResponseEntity<Void> updateUser(@Valid @RequestBody User user) {
    	String email = SecurityContextHolder.getContext().getAuthentication().getName();
        boolean result = userDao.updateUser(user,email);
        if(result) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 사용자 정보 삭제
    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteUser(@PathVariable(value = "email") String email) {
        boolean result = userDao.deleteUser(email);
        if(result) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
