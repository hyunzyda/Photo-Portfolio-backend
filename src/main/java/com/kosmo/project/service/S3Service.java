package com.kosmo.project.service;

import java.io.ByteArrayInputStream;
//import java.io.File;
//import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;



@Service
public class S3Service implements FileServiceImpl{

	@Value("${bucketName}")
	private String bucketName;	
	
	private final AmazonS3 s3;	
	
	public S3Service(AmazonS3 s3) {
		this.s3 = s3;
	}
	
	//파일 업로드
	@Override
    public String saveFile(MultipartFile file) {
        try {
            String fileName = generateFileName(file);
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(file.getContentType());
            metadata.setContentLength(file.getSize());
            s3.putObject(new PutObjectRequest(bucketName, fileName, file.getInputStream(), metadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
            return s3.getUrl(bucketName, fileName).toExternalForm();
        } catch (Exception e) {
            throw new RuntimeException("Failed to upload image to S3", e);
        }
    }	
	private String generateFileName(MultipartFile file) {
        return System.currentTimeMillis() + "_" + file.getOriginalFilename();
    }
	
//    String originalFileName = file.getOriginalFilename();
//
//    try {
//        byte[] bytes = file.getBytes();
//        ObjectMetadata metadata = new ObjectMetadata();
//        
//        metadata.setContentLength(bytes.length);
//        PutObjectRequest request = new PutObjectRequest(bucketName, originalFileName, new ByteArrayInputStream(bytes), metadata);
//        PutObjectResult putObjectResult = s3.putObject(request);
//        return putObjectResult.getContentMd5();
//    } catch (IOException e) {
//        throw new RuntimeException(e);
//    }
	
	//파일 다운로드
	@Override
	public byte[] downloadFile(String filename) {
		S3Object object = s3.getObject(bucketName, filename);
		S3ObjectInputStream objectContent = object.getObjectContent();
		try {
			return IOUtils.toByteArray(objectContent);
		} catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
}
