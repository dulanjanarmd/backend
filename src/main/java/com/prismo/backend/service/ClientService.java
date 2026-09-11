package com.prismo.backend.service;

import com.prismo.backend.model.ApprovalRequest;
import com.prismo.backend.model.ApprovalStatus;
import com.prismo.backend.model.Project;
import com.prismo.backend.model.User;
import com.prismo.backend.dto.ApprovalRequestDTO;
import com.prismo.backend.repository.ApprovalRequestRepository;
import com.prismo.backend.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ApprovalRequestRepository repository;
    private final ProjectRepository projectRepository;

    public List<ApprovalRequest> getAllApprovals() {
        return repository.findAll();
    }

    public ApprovalRequest createApproval(ApprovalRequestDTO dto) {
        Project project = projectRepository.findById(dto.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found"));
        
        ApprovalRequest request = ApprovalRequest.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .dateRequested(dto.getDateRequested() != null ? dto.getDateRequested() : LocalDate.now())
                .status(ApprovalStatus.valueOf(dto.getStatus() != null ? dto.getStatus().toUpperCase() : "PENDING"))
                .project(project)
                .client(project.getClient())
                .build();
                
        return repository.save(request);
    }

    public ApprovalRequest updateApproval(Long id, ApprovalRequestDTO dto) {
        ApprovalRequest request = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Approval request not found"));
        
        if (dto.getStatus() != null) {
            request.setStatus(ApprovalStatus.valueOf(dto.getStatus().toUpperCase()));
        }
        
        return repository.save(request);
    }
}
