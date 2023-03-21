package com.kosmo.project.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileImageUploadController {

	@PostMapping("/upload-file")
	public String uploadImage(@RequestParam("file") MultipartFile file) {
		
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getName());
		System.out.println(file.getSize());
		
		String Path_Directory="";
		
		return "이미지 업로드 성공";
	}
	
}
