package com.prismo.backend.service;

import com.prismo.backend.model.Project;
import com.prismo.backend.model.Task;
import com.prismo.backend.model.TaskStatus;
import com.prismo.backend.model.User;
import com.prismo.backend.repository.ProjectRepository;
import com.prismo.backend.repository.TaskRepository;
import com.prismo.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository repository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public List<Task> getTasksByProject(Long projectId) {
        return repository.findByProjectId(projectId);
    }

    public Task createTask(Long projectId, Long assigneeId, Task task) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        User assignee = userRepository.findById(assigneeId)
                .orElseThrow(() -> new RuntimeException("Assignee not found"));
        task.setProject(project);
        task.setAssignee(assignee);
        task.setStatus(TaskStatus.TO_DO);
        return repository.save(task);
    }

    public Task updateTask(Long id, Task updates) {
        Task task = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        if (updates.getTitle() != null) task.setTitle(updates.getTitle());
        if (updates.getDescription() != null) task.setDescription(updates.getDescription());
        if (updates.getPriority() != null) task.setPriority(updates.getPriority());
        if (updates.getDueDate() != null) task.setDueDate(updates.getDueDate());
        if (updates.getStatus() != null) task.setStatus(updates.getStatus());
        if (updates.getCompletionEvidence() != null) task.setCompletionEvidence(updates.getCompletionEvidence());
        return repository.save(task);
    }
}
