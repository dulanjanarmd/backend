package com.prismo.backend.controller;

import com.prismo.backend.model.Project;
import com.prismo.backend.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService service;

    @GetMapping
    @PreAuthorize("hasAnyRole('CEO', 'PROJECT_MANAGER', 'CLIENT')")
    public ResponseEntity<List<Project>> getAllProjects() {
        return ResponseEntity.ok(service.getAllProjects());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('CEO', 'PROJECT_MANAGER', 'CLIENT')")
    public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getProjectById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('PROJECT_MANAGER')")
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        return ResponseEntity.ok(service.createProject(project));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('PROJECT_MANAGER')")
    public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody Project project) {
        return ResponseEntity.ok(service.updateProject(id, project));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('PROJECT_MANAGER')")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        service.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}
