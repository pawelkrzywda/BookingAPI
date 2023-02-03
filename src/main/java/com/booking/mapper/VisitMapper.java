package com.booking.mapper;

import com.booking.domain.VisitDto;
import com.booking.entity.Visit;
import com.booking.exception.DoctorNotFoundException;
import com.booking.exception.PatientNotFoundException;
import com.booking.service.DoctorService;
import com.booking.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VisitMapper {
    private final PatientService patientService;
    private final DoctorService doctorService;

    public Visit mapToVisit(final VisitDto visitDto) throws PatientNotFoundException, DoctorNotFoundException {
        return Visit.builder()
                .id(visitDto.getId())
                .date(visitDto.getDate())
                .time(visitDto.getTime())
                .patient(patientService.findPatient(visitDto.getPatientId()))
                .doctor(doctorService.findDoctor(visitDto.getDoctorId()))
                .build();
    }

    public VisitDto mapToVisitDto(final Visit visit) {
        return VisitDto.builder()
                .id(visit.getId())
                .date(visit.getDate())
                .time(visit.getTime())
                .patientId(visit.getPatient().getId())
                .doctorId(visit.getDoctor().getId())
                .build();
    }
}