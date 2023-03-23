//package com.kosmo.project.service;
//
//import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.model.ObjectMetadata;
//import com.amazonaws.services.s3.model.PutObjectRequest;
//import com.amazonaws.util.IOUtils;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//import java.io.IOException;
//import java.util.UUID;
//
//@Service
//public class ImageDownloadService {
//
//    private final AmazonS3 s3Client;
//
//    @Value("${amazon.s3.bucket-name}")
//    private String bucketName;
//
//    public ImageDownloadService(AmazonS3 s3Client) {
//        this.s3Client = s3Client;
//    }
//
//    public String uploadImage(MultipartFile file) throws IOException {
//        String imageName = UUID.randomUUID().toString();
//        String imageUrl = "https://" + bucketName + ".s3.amazonaws.com/" + imageName + ".png";
//        ObjectMetadata metadata = new ObjectMetadata();
//        metadata.setContentLength(file.getSize());
//        metadata.setContentType(file.getContentType());
//        s3Client.putObject(new PutObjectRequest(bucketName, imageName + ".png", file.getInputStream(), metadata));
//        return imageUrl;
//    }
//
//    public byte[] getImage(String imageName) throws IOException {
//        return IOUtils.toByteArray(s3Client.getObject(bucketName, imageName + ".png").getObjectContent());
//    }
//}
