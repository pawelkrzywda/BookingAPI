package com.booking.mapper;

import com.booking.domain.PatientDto;
import com.booking.entity.Patient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PatientMapperTest {

    @Autowired
    PatientMapper patientMapper;

    @Test
    public void shouldMapToPaient(){
        //Given
        PatientDto patientDto = PatientDto.builder()
                .name("John")
                .surname("Smith")
                .pesel(83031117279L)
                .phoneNumber("+48 123 456 789")
                .build();

        //When
        Patient patient = patientMapper.mapToPatient(patientDto);

        //Then
        assertEquals(patientDto.getName(), patient.getName());
        assertEquals(patientDto.getSurname(), patient.getSurname());
        assertEquals(patientDto.getPesel(), patient.getPesel());
        assertEquals(patientDto.getPhoneNumber(), patient.getPhoneNumber());
    }

    @Test
    public void shouldMapToPaientDto(){
        //Given
        Patient patient = Patient.builder()
                .name("John")
                .surname("Smith")
                .pesel(83031117279L)
                .phoneNumber("+48 123 456 789")
                .build();

        //When
        PatientDto patientDto = patientMapper.mapToPatientDto(patient);

        //Then
        assertEquals(patient.getName(), patientDto.getName());
        assertEquals(patient.getSurname(), patientDto.getSurname());
        assertEquals(patient.getPesel(), patientDto.getPesel());
        assertEquals(patient.getPhoneNumber(), patientDto.getPhoneNumber());
    }
}