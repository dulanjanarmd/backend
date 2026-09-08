package com.prismo.backend.service;

import com.prismo.backend.dto.InquiryRequest;
import com.prismo.backend.model.*;
import com.prismo.backend.repository.InquiryRepository;
import com.prismo.backend.repository.ProjectRepository;
import com.prismo.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InquiryService {

    private final InquiryRepository inquiryRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    public Inquiry createInquiry(InquiryRequest request) {
        Inquiry inquiry = Inquiry.builder()
                .customerName(request.getCustomerName())
                .customerEmail(request.getCustomerEmail())
                .customerPhone(request.getCustomerPhone())
                .projectType(request.getProjectType())
                .location(request.getLocation())
                .initialNotes(request.getInitialNotes())
                .status(InquiryStatus.NEW)
                .build();
        return inquiryRepository.save(inquiry);
    }

    public List<Inquiry> getAllInquiries() {
        return inquiryRepository.findAll();
    }

    public List<Inquiry> getInquiriesByCustomerEmail(String email) {
        return inquiryRepository.findByCustomerEmail(email);
    }

    public Inquiry getInquiryById(Long id) {
        return inquiryRepository.findById(id).orElseThrow(() -> new RuntimeException("Inquiry not found"));
    }

    public Inquiry updateInquiry(Long id, InquiryRequest request) {
        Inquiry inquiry = getInquiryById(id);
        
        if (request.getStatus() != null) {
            inquiry.setStatus(InquiryStatus.valueOf(request.getStatus()));
        }
        if (request.getMeetingDate() != null) {
            inquiry.setMeetingDate(request.getMeetingDate());
        }
        if (request.getConsultationNotes() != null) {
            inquiry.setConsultationNotes(request.getConsultationNotes());
        }
        if (request.getProposalText() != null) {
            inquiry.setProposalText(request.getProposalText());
        }
        if (request.getBudget() != null) {
            inquiry.setBudget(request.getBudget());
        }
        if (request.getAssignedPmId() != null) {
            User pm = userRepository.findById(request.getAssignedPmId()).orElse(null);
            inquiry.setAssignedPm(pm);
        }
        
        return inquiryRepository.save(inquiry);
    }

    @Transactional
    public Inquiry acceptProposal(Long id) {
        Inquiry inquiry = getInquiryById(id);
        inquiry.setStatus(InquiryStatus.ACCEPTED);
        
        // Convert to Project
        Project project = new Project();
        project.setName(inquiry.getProjectType() + " for " + inquiry.getCustomerName());
        project.setDescription(inquiry.getProposalText() != null ? inquiry.getProposalText() : "Generated from Inquiry");
        project.setLocation(inquiry.getLocation());
        project.setStatus(ProjectStatus.PLANNING);
        project.setBudget(inquiry.getBudget());
        project.setStartDate(new java.util.Date());
        
        project = projectRepository.save(project);
        
        inquiry.setResultingProject(project);
        return inquiryRepository.save(inquiry);
    }
}
