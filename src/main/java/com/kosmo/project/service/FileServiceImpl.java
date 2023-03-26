package com.kosmo.project.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileServiceImpl {
	
	String saveFile(MultipartFile file);	
	byte[] downloadFile(String filename);
}
