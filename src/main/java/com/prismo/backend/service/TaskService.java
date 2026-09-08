package com.prismo.backend.service;

import com.prismo.backend.model.Task;
import com.prismo.backend.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository repository;

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public List<Task> getTasksByProject(Long projectId) {
        return repository.findByProjectId(projectId);
    }
}
