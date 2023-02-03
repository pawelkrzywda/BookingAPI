package com.booking.domain;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
public class DoctorDto {

    private Long id;
    private String name;
    private String surname;
    private String specialization;
    private double rating;
}