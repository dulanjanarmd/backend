package com.prismo.backend.repository;

import com.prismo.backend.model.ConsultationNote;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ConsultationNoteRepository extends JpaRepository<ConsultationNote, Long> {
    Optional<ConsultationNote> findByInquiryId(Long inquiryId);
}
