package com.booking.mapper;

import com.booking.domain.PatientDto;
import com.booking.entity.Patient;
import org.springframework.stereotype.Service;

@Service
public class PatientMapper {
    public Patient mapToPatient(final PatientDto patientDto) {
        return Patient.builder()
                .id(patientDto.getId())
                .name(patientDto.getName())
                .surname(patientDto.getSurname())
                .pesel(patientDto.getPesel())
                .phoneNumber(patientDto.getPhoneNumber())
                .build();
    }

    public PatientDto mapToPatientDto(final Patient patient) {
        return PatientDto.builder()
                .id(patient.getId())
                .name(patient.getName())
                .surname(patient.getSurname())
                .pesel(patient.getPesel())
                .phoneNumber(patient.getPhoneNumber())
                .build();
    }
}