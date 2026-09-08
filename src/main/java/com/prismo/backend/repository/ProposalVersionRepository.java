package com.prismo.backend.repository;

import com.prismo.backend.model.ProposalVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProposalVersionRepository extends JpaRepository<ProposalVersion, Long> {
    List<ProposalVersion> findByProposalIdOrderByVersionNumberDesc(Long proposalId);
}
