package com.prismo.backend.dto;

import lombok.Data;
import java.util.Date;

@Data
public class InquiryRequest {
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private String projectType;
    private String location;
    private String initialNotes;
    
    // For updates
    private String status;
    private Date meetingDate;
    private String consultationNotes;
    private String proposalText;
    private Double budget;
    private Long assignedPmId;
}
