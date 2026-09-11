package com.prismo.backend.repository;

import com.prismo.backend.model.GlobalMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GlobalMessageRepository extends JpaRepository<GlobalMessage, Long> {
    List<GlobalMessage> findAllByProjectIdOrderByCreatedAtAsc(Long projectId);
}
