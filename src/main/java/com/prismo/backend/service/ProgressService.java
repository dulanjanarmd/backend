package com.prismo.backend.service;

import com.prismo.backend.model.ProgressLog;
import com.prismo.backend.model.Project;
import com.prismo.backend.model.User;
import com.prismo.backend.model.Task;
import com.prismo.backend.repository.ProgressLogRepository;
import com.prismo.backend.repository.ProjectRepository;
import com.prismo.backend.repository.TaskRepository;
import com.prismo.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgressService {

    private final ProgressLogRepository repository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public List<ProgressLog> getAllLogs() {
        return repository.findAll();
    }

    public List<ProgressLog> getLogsByProject(Long projectId) {
        return repository.findByProjectId(projectId);
    }

    public ProgressLog createLog(Long projectId, Long taskId, Long engineerId, ProgressLog log) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        User engineer = userRepository.findById(engineerId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        log.setProject(project);
        log.setSiteEngineer(engineer);
        
        if (taskId != null) {
            Task task = taskRepository.findById(taskId).orElse(null);
            log.setTask(task);
        }
        if (log.getPhotos() != null) {
            log.getPhotos().forEach(photo -> photo.setProgressLog(log));
        }
        return repository.save(log);
    }
}
