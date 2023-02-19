package com.booking.mapper;

import com.booking.domain.DoctorDto;
import com.booking.entity.Doctor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DoctorMapperTest {
    @Autowired
    DoctorMapper doctorMapper;

    @Test
    public void shouldMapToDoctor(){
        //Given
        DoctorDto doctorDto = DoctorDto.builder()
                .name("William")
                .surname("White")
                .specialization("Cardiologist")
                .rating(4.5)
                .build();

        //When
        Doctor doctor = doctorMapper.mapToDoctor(doctorDto);

        //Then
        assertEquals(doctorDto.getName(), doctor.getName());
        assertEquals(doctorDto.getSurname(), doctor.getSurname());
        assertEquals(doctorDto.getSpecialization(), doctor.getSpecialization());
        assertEquals(doctorDto.getRating(), doctor.getRating());
    }

    @Test
    public void shouldMapToDoctorDto(){
        //Given
        Doctor doctor = Doctor.builder()
                .name("William")
                .surname("White")
                .specialization("Cardiologist")
                .rating(4.5)
                .build();

        //When
        DoctorDto doctorDto = doctorMapper.mapToDoctorDto(doctor);

        //Then
        assertEquals(doctor.getName(), doctorDto.getName());
        assertEquals(doctor.getSurname(), doctorDto.getSurname());
        assertEquals(doctor.getSpecialization(), doctorDto.getSpecialization());
        assertEquals(doctor.getRating(), doctorDto.getRating());
    }
}