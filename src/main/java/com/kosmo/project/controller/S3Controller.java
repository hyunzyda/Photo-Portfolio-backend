package com.kosmo.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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
   
   // 파일 업로드
   @PostMapping("/upload")
   public String upload(@RequestParam("file") MultipartFile file) {
      return s3Service.saveFile(file);
   }
   
   // 파일 다운로드
   @GetMapping("/download/{filename}")
    public ResponseEntity<byte[]> download(@PathVariable("filename") String filename){
        HttpHeaders headers=new HttpHeaders();
        headers.add("Content-type", MediaType.ALL_VALUE);
        headers.add("Content-Disposition", "attachment; filename="+filename);
        byte[] bytes = s3Service.downloadFile(filename);
        return  ResponseEntity.status(HttpStatus.OK).headers(headers).body(bytes);
    }
 }