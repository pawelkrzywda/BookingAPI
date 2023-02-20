package com.booking.mapper;

import com.booking.domain.OpinionDto;
import com.booking.entity.Opinion;
import com.booking.exception.DoctorNotFoundException;
import com.booking.exception.PatientNotFoundException;
import com.booking.service.DoctorService;
import com.booking.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OpinionMapper {
    private final PatientService patientService;
    private final DoctorService doctorService;

    public Opinion mapToOpinion(final OpinionDto opinionDto) throws PatientNotFoundException, DoctorNotFoundException {
        return Opinion.builder()
                .id(opinionDto.getId())
                .description(opinionDto.getDescription())
                .rating(opinionDto.getRating())
                .patient(patientService.findPatient(opinionDto.getPatientId()))
                .doctor(doctorService.findDoctor(opinionDto.getDoctorId()))
                .build();
    }

    public OpinionDto mapToOpinionDto(final Opinion opinion) {
        return OpinionDto.builder()
                .id(opinion.getId())
                .description(opinion.getDescription())
                .rating(opinion.getRating())
                .patientId(opinion.getPatient().getId())
                .doctorId(opinion.getDoctor().getId())
                .build();
    }

    public List<OpinionDto> mapToOpinionDtoList(final List<Opinion> opinions){
        return opinions.stream()
                .map(this::mapToOpinionDto)
                .collect(Collectors.toList());
    }
}