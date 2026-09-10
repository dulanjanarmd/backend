package com.prismo.backend.controller;

import com.prismo.backend.repository.TaskRepository;
import com.prismo.backend.repository.MilestoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.stream.Collectors;

@RestController
public class DebugController {
    @Autowired
    private TaskRepository repository;
    @Autowired
    private MilestoneRepository milestoneRepository;

    @GetMapping("/api/debug/tasks")
    public String debugTasks() {
        return repository.findAll().stream()
            .map(t -> "Task ID: " + t.getId() + " - Title: " + t.getTitle() + " - Milestone: " + (t.getMilestone() != null ? t.getMilestone().getName() : "NULL"))
            .collect(Collectors.joining("\n"));
    }

    @GetMapping("/api/debug/milestones")
    public String debugMilestones() {
        return milestoneRepository.findAll().stream()
            .map(m -> "Milestone ID: " + m.getId() + " - Name: " + m.getName() + " - Project: " + (m.getProject() != null ? m.getProject().getId() : "NULL"))
            .collect(Collectors.joining("\n"));
    }
}
