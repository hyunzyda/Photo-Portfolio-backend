package com.kosmo.project.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kosmo.project.dto.User;

@RestController
public class FileImageUploadController {

	@PostMapping("/upload-file")
	public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
	    // 로그인한 사용자 정보 가져오기
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    User user = (User) authentication.getPrincipal();
	    
	    try {
	        // 이미지 저장 경로 생성
	        String imagePath = "uploads/" + user.getEmail() + "/" + UUID.randomUUID().toString() + ".jpg";
	        
	        // 이미지 저장
	        Path targetPath = Paths.get(imagePath);
	        Files.createDirectories(targetPath.getParent());
	        file.transferTo(targetPath);
	        
	        // 이미지 URL 반환
	        return ResponseEntity.ok("/" + imagePath);
	    } catch (IOException e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}
}
