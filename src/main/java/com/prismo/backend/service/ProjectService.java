package com.prismo.backend.service;

import com.prismo.backend.model.Project;
import com.prismo.backend.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository repository;

    public List<Project> getAllProjects() {
        return repository.findAll();
    }

    public Project getProjectById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Project not found"));
    }

    public Project createProject(Project project) {
        return repository.save(project);
    }

    public Project updateProject(Long id, Project projectDetails) {
        Project project = getProjectById(id);
        project.setName(projectDetails.getName());
        project.setDescription(projectDetails.getDescription());
        project.setLocation(projectDetails.getLocation());
        project.setStartDate(projectDetails.getStartDate());
        project.setEndDate(projectDetails.getEndDate());
        project.setStatus(projectDetails.getStatus());
        project.setProgressPercentage(projectDetails.getProgressPercentage());
        return repository.save(project);
    }

    public void deleteProject(Long id) {
        repository.deleteById(id);
    }
}
