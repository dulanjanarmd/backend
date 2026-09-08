package com.prismo.backend.repository;

import com.prismo.backend.model.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface ProposalRepository extends JpaRepository<Proposal, Long> {
    Optional<Proposal> findByInquiryId(Long inquiryId);
    List<Proposal> findByInquiryCustomerEmail(String email);
}
