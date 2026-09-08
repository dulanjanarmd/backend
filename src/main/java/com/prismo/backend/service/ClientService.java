package com.prismo.backend.service;

import com.prismo.backend.model.ApprovalRequest;
import com.prismo.backend.repository.ApprovalRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ApprovalRequestRepository repository;

    public List<ApprovalRequest> getAllApprovals() {
        return repository.findAll();
    }
}
