package com.prismo.backend.repository;

import com.prismo.backend.model.ProposalDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProposalDocumentRepository extends JpaRepository<ProposalDocument, Long> {
    List<ProposalDocument> findByProposalId(Long proposalId);
}
