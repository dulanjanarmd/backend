package com.prismo.backend.controller;

import com.prismo.backend.model.Task;
import com.prismo.backend.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService service;

    @GetMapping
    @PreAuthorize("hasAnyRole('CEO', 'PROJECT_MANAGER', 'SITE_ENGINEER')")
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(service.getAllTasks());
    }

    @GetMapping("/project/{projectId}")
    @PreAuthorize("hasAnyRole('CEO', 'PROJECT_MANAGER', 'SITE_ENGINEER')")
    public ResponseEntity<List<Task>> getTasksByProject(@PathVariable Long projectId) {
        return ResponseEntity.ok(service.getTasksByProject(projectId));
    }
}
