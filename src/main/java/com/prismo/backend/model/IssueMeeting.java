package com.prismo.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

@Entity
@Table(name = "issue_meetings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IssueMeeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "issue_id")
    private SiteIssue issue;

    @ManyToOne
    @JoinColumn(name = "organizer_id")
    private User organizer;

    private String title;
    private LocalDateTime scheduledTime;
    private String meetingLink;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) createdAt = LocalDateTime.now();
    }
}
