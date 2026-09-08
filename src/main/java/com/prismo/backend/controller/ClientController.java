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
}
