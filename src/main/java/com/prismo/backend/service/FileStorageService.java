package com.prismo.backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Service
public class FileStorageService {

    @Value("${file.upload-dir:uploads}")
    private String uploadDir;

    /**
     * Saves a file to the uploads directory.
     * Returns the public URL path like: /uploads/abc123_photo.jpg
     */
    public String store(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Cannot store empty file.");
        }

        // Sanitize original filename
        String original = Paths.get(file.getOriginalFilename()).getFileName().toString();
        String extension = "";
        int dotIdx = original.lastIndexOf('.');
        if (dotIdx >= 0) extension = original.substring(dotIdx); // e.g. ".jpg"

        // Unique filename to prevent collisions
        String storedName = UUID.randomUUID().toString().replace("-", "") + extension;

        // Create upload directory if it doesn't exist
        Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
        Files.createDirectories(uploadPath);

        // Copy file bytes to disk
        Path destination = uploadPath.resolve(storedName);
        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);

        // Return the public URL (served via /uploads/** mapping)
        return "/uploads/" + storedName;
    }

    /**
     * Delete a file by its stored URL path.
     */
    public void delete(String fileUrl) {
        try {
            String filename = Paths.get(fileUrl).getFileName().toString();
            Path filePath = Paths.get(uploadDir).toAbsolutePath().normalize().resolve(filename);
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            // Log but don't crash if file is already gone
            System.err.println("Could not delete file: " + fileUrl + " — " + e.getMessage());
        }
    }
}
