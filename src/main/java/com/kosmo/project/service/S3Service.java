package com.kosmo.project.service;

import java.io.ByteArrayInputStream;
//import java.io.File;
//import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;



@Service
public class S3Service implements FileServiceImpl{

	@Value("${BName}")
	private String BName;	
	
	private final AmazonS3 s3;	
	
	public S3Service(AmazonS3 s3) {
		this.s3 = s3;
	}
	
	//파일 업로드
	@Override
	public String saveFile(MultipartFile file) {
	    String originalFileName = file.getOriginalFilename();
	    try {
	        InputStream inputStream = file.getInputStream();
	        byte[] bytes = IOUtils.toByteArray(inputStream);
	        ObjectMetadata metadata = new ObjectMetadata();
	        metadata.setContentLength(bytes.length);
	        metadata.setContentType(file.getContentType());
	        PutObjectRequest request = new PutObjectRequest(BName, originalFileName, new ByteArrayInputStream(bytes), metadata);
	        PutObjectResult putObjectResult = s3.putObject(request);
	        String url = s3.getUrl(BName, originalFileName).toString();
	        // 임시 저장소에 저장된 파일 삭제
	        file.getInputStream().close();
	        return url;
	    } catch (IOException e) {
	        throw new RuntimeException(e);
	    }
	}	
	//파일 다운로드
	@Override
	public byte[] downloadFile(String filename) {
		S3Object object = s3.getObject(BName, filename);
		S3ObjectInputStream objectContent = object.getObjectContent();
		try {
			return IOUtils.toByteArray(objectContent);
		} catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
}
