package com.prismo.backend.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class DocumentResponse {
    Long id;
    String fileName;
    String fileUrl;
    String category;
    String note;
    String uploadedBy;
}
