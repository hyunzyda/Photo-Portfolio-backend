//package com.kosmo.project.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.ByteArrayResource;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.kosmo.project.service.ImageDownloadService;
//
//import java.io.IOException;
//
//@RestController
//@RequestMapping("/api/image")
//public class ImageRestController {
//
//    private final ImageDownloadService imageDownloadService;
//
//    @Autowired
//    public ImageRestController(ImageDownloadService imageDownloadService) {
//        this.imageDownloadService = imageDownloadService;
//    }
//
//    @PostMapping
//    public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile image) throws IOException {
//        String imageUrl = imageDownloadService.uploadImage(image);
//        return new ResponseEntity<>(imageUrl, HttpStatus.CREATED);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<ByteArrayResource> getImage(@PathVariable("id") String id) throws IOException {
//        byte[] imageBytes = imageDownloadService.getImage(id);
//        ByteArrayResource resource = new ByteArrayResource(imageBytes);
//        return ResponseEntity.ok()
//                .contentLength(imageBytes.length)
//                .header("Content-type", "image/png")
//                .header("Content-disposition", "attachment; filename=\"" + id + ".png\"")
//                .body(resource);
//    }
//}
