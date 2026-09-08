package com.prismo.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "proposal_responses")
public class ProposalResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proposal_id")
    private Proposal proposal;

    @Enumerated(EnumType.STRING)
    private ProposalStatus decision;

    @Column(length = 2000)
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "responded_by_id")
    private User respondedBy;

    private LocalDateTime respondedAt;

    @PrePersist
    protected void onCreate() {
        respondedAt = LocalDateTime.now();
    }
}
