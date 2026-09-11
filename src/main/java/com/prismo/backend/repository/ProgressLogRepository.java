package com.prismo.backend.repository;

import com.prismo.backend.model.ProgressLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgressLogRepository extends JpaRepository<ProgressLog, Long> {
    List<ProgressLog> findByProjectId(Long projectId);

    List<ProgressLog> findByTaskId(Long taskId);
}
