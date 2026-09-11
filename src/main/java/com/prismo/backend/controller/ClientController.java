package com.prismo.backend.controller;

import com.prismo.backend.model.ApprovalRequest;
import com.prismo.backend.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService service;

    @GetMapping("/approvals")
    @PreAuthorize("hasAnyRole('CEO', 'PROJECT_MANAGER', 'CLIENT')")
    public ResponseEntity<List<ApprovalRequest>> getAllApprovals() {
        return ResponseEntity.ok(service.getAllApprovals());
    }

    @PostMapping("/approvals")
    @PreAuthorize("hasAnyRole('CEO', 'PROJECT_MANAGER', 'CLIENT')")
    public ResponseEntity<ApprovalRequest> createApproval(@RequestBody com.prismo.backend.dto.ApprovalRequestDTO dto) {
        return ResponseEntity.ok(service.createApproval(dto));
    }

    @PutMapping("/approvals/{id}")
    @PreAuthorize("hasAnyRole('CEO', 'PROJECT_MANAGER', 'CLIENT')")
    public ResponseEntity<ApprovalRequest> updateApproval(
            @PathVariable Long id,
            @RequestBody com.prismo.backend.dto.ApprovalRequestDTO dto) {
        return ResponseEntity.ok(service.updateApproval(id, dto));
    }
}
