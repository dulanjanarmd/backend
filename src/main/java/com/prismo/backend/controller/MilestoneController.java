package com.prismo.backend.controller;

import com.prismo.backend.model.Milestone;
import com.prismo.backend.service.MilestoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects/{projectId}/milestones")
@RequiredArgsConstructor
public class MilestoneController {

    private final MilestoneService service;

    @GetMapping
    @PreAuthorize("hasAnyRole('CEO', 'PROJECT_MANAGER', 'SITE_ENGINEER', 'CLIENT')")
    public ResponseEntity<List<Milestone>> getMilestones(@PathVariable Long projectId) {
        return ResponseEntity.ok(service.getMilestonesByProject(projectId));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('PROJECT_MANAGER', 'CEO')")
    public ResponseEntity<Milestone> addMilestone(@PathVariable Long projectId, @RequestBody Milestone milestone) {
        return ResponseEntity.ok(service.addMilestone(projectId, milestone));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('PROJECT_MANAGER', 'CEO')")
    public ResponseEntity<Milestone> updateMilestone(@PathVariable Long id, @RequestBody Milestone milestone) {
        return ResponseEntity.ok(service.updateMilestone(id, milestone));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('PROJECT_MANAGER', 'CEO')")
    public ResponseEntity<Void> deleteMilestone(@PathVariable Long id) {
        service.deleteMilestone(id);
        return ResponseEntity.noContent().build();
    }
}
