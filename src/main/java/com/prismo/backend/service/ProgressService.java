package com.prismo.backend.service;

import com.prismo.backend.model.ProgressLog;
import com.prismo.backend.repository.ProgressLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgressService {

    private final ProgressLogRepository repository;

    public List<ProgressLog> getAllLogs() {
        return repository.findAll();
    }

    public List<ProgressLog> getLogsByProject(Long projectId) {
        return repository.findByProjectId(projectId);
    }
}
