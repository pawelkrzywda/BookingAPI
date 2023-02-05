package com.booking.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class OpinionDto {
    private Long id;
    private String description;
    private double rating;
    private Long patientId;
    private Long doctorId;
}