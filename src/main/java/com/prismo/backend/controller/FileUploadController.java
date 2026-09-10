package com.prismo.backend.controller;

import com.prismo.backend.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileUploadController {

    private final FileStorageService fileStorageService;

    /**
     * Upload any file (photo or document).
     * Returns JSON: { "url": "/uploads/abc123.jpg", "originalName": "photo.jpg", "size": 12345 }
     *
     * How to use (from frontend):
     *   POST http://localhost:8080/api/files/upload
     *   Headers: Authorization: Bearer <token>
     *   Body: multipart/form-data, field name = "file"
     *
     * The returned URL can be stored in the DB and accessed at:
     *   http://localhost:8080/uploads/abc123.jpg
     */
    @PostMapping("/upload")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Map<String, Object>> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String url = fileStorageService.store(file);
            return ResponseEntity.ok(Map.of(
                "url", url,
                "fullUrl", "http://localhost:8080" + url,
                "originalName", file.getOriginalFilename(),
                "size", file.getSize(),
                "type", file.getContentType()
            ));
        } catch (IOException e) {
            return ResponseEntity.status(500).body(Map.of("error", "Failed to upload file: " + e.getMessage()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    /**
     * Delete a previously uploaded file.
     * Body: { "url": "/uploads/abc123.jpg" }
     */
    @DeleteMapping("/delete")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Map<String, String>> deleteFile(@RequestBody Map<String, String> body) {
        String url = body.get("url");
        if (url == null || url.isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("error", "url is required"));
        }
        fileStorageService.delete(url);
        return ResponseEntity.ok(Map.of("message", "File deleted"));
    }
}
