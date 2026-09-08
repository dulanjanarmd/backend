package com.prismo.backend.controller;

import com.prismo.backend.dto.InquiryRequest;
import com.prismo.backend.model.Inquiry;
import com.prismo.backend.service.InquiryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inquiries")
@RequiredArgsConstructor
public class InquiryController {

    private final InquiryService inquiryService;

    @PostMapping
    public ResponseEntity<Inquiry> createInquiry(@RequestBody InquiryRequest request) {
        return ResponseEntity.ok(inquiryService.createInquiry(request));
    }

    @GetMapping
    public ResponseEntity<List<Inquiry>> getAllInquiries() {
        return ResponseEntity.ok(inquiryService.getAllInquiries());
    }

    @GetMapping("/customer/{email}")
    public ResponseEntity<List<Inquiry>> getCustomerInquiries(@PathVariable String email) {
        return ResponseEntity.ok(inquiryService.getInquiriesByCustomerEmail(email));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inquiry> getInquiry(@PathVariable Long id) {
        return ResponseEntity.ok(inquiryService.getInquiryById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inquiry> updateInquiry(@PathVariable Long id, @RequestBody InquiryRequest request) {
        return ResponseEntity.ok(inquiryService.updateInquiry(id, request));
    }

    @PostMapping("/{id}/accept")
    public ResponseEntity<Inquiry> acceptProposal(@PathVariable Long id) {
        return ResponseEntity.ok(inquiryService.acceptProposal(id));
    }
    
    @PostMapping("/{id}/reject")
    public ResponseEntity<Inquiry> rejectProposal(@PathVariable Long id) {
        InquiryRequest request = new InquiryRequest();
        request.setStatus("REJECTED");
        return ResponseEntity.ok(inquiryService.updateInquiry(id, request));
    }
}
