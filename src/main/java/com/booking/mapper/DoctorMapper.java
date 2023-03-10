package com.booking.mapper;

import com.booking.domain.DoctorDto;
import com.booking.entity.Doctor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorMapper {
    public Doctor mapToDoctor(final DoctorDto doctorDto) {
        return Doctor.builder()
                .id(doctorDto.getId())
                .name(doctorDto.getName())
                .surname(doctorDto.getSurname())
                .specialization(doctorDto.getSpecialization())
                .rating(doctorDto.getRating())
                .build();
    }

    public DoctorDto mapToDoctorDto(final Doctor doctor) {
        return DoctorDto.builder()
                .id(doctor.getId())
                .name(doctor.getName())
                .surname(doctor.getSurname())
                .specialization(doctor.getSpecialization())
                .rating(doctor.getRating())
                .build();
    }

    public List<DoctorDto> mapToDoctorDtoList(final List<Doctor> doctors){
        return doctors.stream()
                .map(this::mapToDoctorDto)
                .collect(Collectors.toList());
    }
}