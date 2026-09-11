package com.prismo.backend.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ApprovalRequestDTO {
    private String title;
    private String description;
    private String dueDate;
    private Long projectId;
    private String status;
    private LocalDate dateRequested;
}
