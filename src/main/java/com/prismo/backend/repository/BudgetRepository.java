package com.prismo.backend.repository;

import com.prismo.backend.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
    Optional<Budget> findByProposalId(Long proposalId);
}
