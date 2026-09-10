package com.prismo.backend.repository;

import com.prismo.backend.model.IssueMeeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueMeetingRepository extends JpaRepository<IssueMeeting, Long> {
    List<IssueMeeting> findByIssueIdOrderByCreatedAtAsc(Long issueId);
}
