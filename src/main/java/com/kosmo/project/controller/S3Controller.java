package com.kosmo.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kosmo.project.service.S3Service;

@RestController
public class S3Controller {

	@Autowired
	private S3Service s3Service;
	
	@PostMapping("upload")
	public String upload(@RequestParam("file") MultipartFile file) {
		return s3Service.saveFile(file);
	}
	
	@GetMapping("download/{filename}")
	public byte[] download(@PathVariable("filename") String filename) {
		return s3Service.downloadFile(filename);
	}
	
	@DeleteMapping("{filename}")
	public String deleteFile(@PathVariable("filename") String filename) {
		return s3Service.deleteFile(filename);
	}
	
	@GetMapping()
	public List<String> getAllFiles(){
		return s3Service.listAllFiles();
	}
 }
