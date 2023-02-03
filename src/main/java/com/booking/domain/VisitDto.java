package com.booking.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@Builder
public class VisitDto {
    private Long id;
    private LocalDate date;
    private LocalTime time;
    private Long patientId;
    private Long doctorId;
}