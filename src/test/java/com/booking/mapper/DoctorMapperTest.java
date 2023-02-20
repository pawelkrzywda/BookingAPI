package com.booking.mapper;

import com.booking.domain.DoctorDto;
import com.booking.entity.Doctor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void shouldMapToDoctorDtoList(){
        //Given
        Doctor doctor1 = Doctor.builder()
                .name("William")
                .surname("White")
                .specialization("Cardiologist")
                .rating(4.5)
                .build();

        Doctor doctor2 = Doctor.builder()
                .name("Brian")
                .surname("Brown")
                .specialization("Surgeon")
                .rating(4.8)
                .build();

        List<Doctor> doctorList = new ArrayList<>();
        doctorList.add(doctor1);
        doctorList.add(doctor2);

        //When
        List<DoctorDto> doctorDtos = doctorMapper.mapToDoctorDtoList(doctorList);

        //Then
        assertEquals(2, doctorDtos.size());
        assertEquals("William", doctorDtos.get(0).getName());
        assertEquals(4.8, doctorDtos.get(1).getRating());
    }
}