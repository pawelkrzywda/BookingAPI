package com.booking.domain;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
public class PatientDto {

    private Long id;
    private String name;
    private String surname;
    private Long pesel;
    private String phoneNumber;
}