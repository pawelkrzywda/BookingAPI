package com.booking.mapper;

import com.booking.domain.OpinionDto;
import com.booking.domain.VisitDto;
import com.booking.entity.Doctor;
import com.booking.entity.Opinion;
import com.booking.entity.Patient;
import com.booking.entity.Visit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OpinionMapperTest {
    @Autowired
    OpinionMapper opinionMapper;

    public Patient generatePatient(){
        return Patient.builder()
                .name("John")
                .surname("Smith")
                .pesel(83031117279L)
                .phoneNumber("+48 555-444-333")
                .build();
    }

    public Doctor generateDoctor(){
        return Doctor.builder()
                .name("William")
                .surname("White")
                .specialization("Cardiologist")
                .rating(4.5)
                .build();
    }

    @Test
    public void shouldMapToOpinion(){
        //Given
        OpinionDto opinionDto = OpinionDto.builder()
                .description("Opinion description for tests")
                .rating(4.0)
                .patientId(1L)
                .doctorId(2L)
                .build();

        //When % Then
        try {
            Opinion opinion = opinionMapper.mapToOpinion(opinionDto);

            assertEquals(opinion.getDescription(), opinionDto.getDescription());
            assertEquals(opinion.getRating(), opinionDto.getRating());
            assertEquals(opinion.getPatient().getId(), opinionDto.getPatientId());
            assertEquals(opinion.getDoctor().getId(), opinionDto.getDoctorId());
        }catch(Exception e){
            System.out.println("Exception: " + e);
        }
    }

    @Test
    public void shouldMapToOpinionDto(){
        //Given
        Patient patient = generatePatient();
        Doctor doctor = generateDoctor();
        Opinion opinion = Opinion.builder()
                .description("Opinion description for tests")
                .rating(4.0)
                .patient(patient)
                .doctor(doctor)
                .build();

        //When
        OpinionDto opinionDto = opinionMapper.mapToOpinionDto(opinion);

        //Then
        assertEquals(opinionDto.getDescription(), opinion.getDescription());
        assertEquals(opinionDto.getRating(), opinion.getRating());
        assertEquals(opinionDto.getDoctorId(), opinion.getDoctor().getId());
        assertEquals(opinionDto.getPatientId(), opinion.getPatient().getId());
    }
}