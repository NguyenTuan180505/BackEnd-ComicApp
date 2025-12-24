package com.comicapp.comic_api.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api")
public class UploadController {

    private final String UPLOAD_DIR = "D:/uploads/images/";


    @PostMapping("/uploads")
    public ResponseEntity<String> uploadImage(
            @RequestParam("file") MultipartFile file) throws IOException {

        if (file.isEmpty()) {
            throw new RuntimeException("File rỗng");
        }

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path path = Paths.get(UPLOAD_DIR, fileName);

        Files.createDirectories(path.getParent());
        Files.write(path, file.getBytes());

        // 👉 TRẢ URL
        String imageUrl = "http://localhost:8080/api/images/" + fileName;
        return ResponseEntity.ok(imageUrl);
    }
    @GetMapping("/images/{fileName}")
    public ResponseEntity<Resource> getImage(@PathVariable String fileName) throws IOException {

        Path imagePath = Paths.get(UPLOAD_DIR + fileName);
        if (!Files.exists(imagePath)) {
            return ResponseEntity.notFound().build();
        }

        Resource resource = new UrlResource(imagePath.toUri());

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG) // hoặc PNG
                .body(resource);
    }
}