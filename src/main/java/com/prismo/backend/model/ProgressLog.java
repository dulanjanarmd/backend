package com.prismo.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "progress_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProgressLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    private LocalDate date;

    private String weather;

    private Integer manpower;

    @Column(columnDefinition = "TEXT")
    private String workDone;

    private Double percentageCompleted;

    @ManyToOne
    @JoinColumn(name = "site_engineer_id")
    private User siteEngineer;

    @OneToMany(mappedBy = "progressLog", cascade = CascadeType.ALL)
    private List<Photo> photos;
}
