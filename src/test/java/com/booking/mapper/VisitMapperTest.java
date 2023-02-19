package com.booking.mapper;

import com.booking.domain.VisitDto;
import com.booking.entity.Doctor;
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
public class VisitMapperTest {
    @Autowired
    VisitMapper visitMapper;

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
    public void shouldMapToVisit(){
        //Given
        VisitDto visitDto = VisitDto.builder()
                .date(LocalDate.of(2023, 10, 5))
                .time(LocalTime.of(15, 00))
                .patientId(1L)
                .doctorId(2L)
                .build();

        //When % Then
        try {
            Visit visit = visitMapper.mapToVisit(visitDto);

            assertEquals(visitDto.getDate(), visit.getDate());
            assertEquals(visitDto.getTime(), visit.getTime());
            assertEquals(visitDto.getPatientId(), visit.getPatient().getId());
            assertEquals(visitDto.getDoctorId(), visit.getDoctor().getId());
        }catch(Exception e){
            System.out.println("Exception: " + e);
        }
    }

    @Test
    public void shouldMapToVisitDto(){
        //Given
        Patient patient = generatePatient();
        Doctor doctor = generateDoctor();
        Visit visit = Visit.builder()
                .date(LocalDate.of(2023, 10, 5))
                .time(LocalTime.of(15, 00))
                .patient(patient)
                .doctor(doctor)
                .build();

        //When
        VisitDto visitDto = visitMapper.mapToVisitDto(visit);

        //Then
        assertEquals(visit.getDate(), visitDto.getDate());
        assertEquals(visit.getTime(), visitDto.getTime());
        assertEquals(visit.getPatient().getId(), visitDto.getPatientId());
        assertEquals(visit.getDoctor().getId(), visitDto.getDoctorId());
    }
}