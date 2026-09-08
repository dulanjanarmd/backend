package com.prismo.backend.repository;

import com.prismo.backend.model.ProposalResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProposalResponseRepository extends JpaRepository<ProposalResponse, Long> {
    List<ProposalResponse> findByProposalIdOrderByRespondedAtDesc(Long proposalId);
}
