package com.prismo.backend.repository;

import com.prismo.backend.model.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
    List<Inquiry> findByCustomerEmail(String email);
    List<Inquiry> findByAssignedPmId(Long pmId);
}
