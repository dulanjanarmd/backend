package com.prismo.backend.controller;

import com.prismo.backend.model.ProgressLog;
import com.prismo.backend.service.ProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/progress")
@RequiredArgsConstructor
public class ProgressController {

    private final ProgressService service;

    @GetMapping
    @PreAuthorize("hasAnyRole('CEO', 'PROJECT_MANAGER', 'SITE_ENGINEER')")
    public ResponseEntity<List<ProgressLog>> getAllLogs() {
        return ResponseEntity.ok(service.getAllLogs());
    }

    @GetMapping("/project/{projectId}")
    @PreAuthorize("hasAnyRole('CEO', 'PROJECT_MANAGER', 'SITE_ENGINEER')")
    public ResponseEntity<List<ProgressLog>> getLogsByProject(@PathVariable Long projectId) {
        return ResponseEntity.ok(service.getLogsByProject(projectId));
    }
}
