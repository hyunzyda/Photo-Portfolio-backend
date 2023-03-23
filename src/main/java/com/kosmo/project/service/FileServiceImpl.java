package com.kosmo.project.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface FileServiceImpl {
	
	String saveFile(MultipartFile file);
	
	byte[] downloadFile(String filename);
	
	String deleteFile(String filename);

	List<String> listAllFiles();
}
