package com.booking.mapper;

import com.booking.domain.OpinionDto;
import com.booking.entity.Doctor;
import com.booking.entity.Opinion;
import com.booking.entity.Patient;
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

    @Test
    public void shoudMapToOpinionDtoList(){
        //Given
        Patient patient1 = generatePatient();
        Patient patient2 = generatePatient();
        Doctor doctor = generateDoctor();
        Opinion opinion1 = Opinion.builder()
                .description("Opinion 1 description for tests")
                .rating(4.0)
                .patient(patient1)
                .doctor(doctor)
                .build();

        Opinion opinion2 = Opinion.builder()
                .description("Opinion 2 description for tests")
                .rating(4.5)
                .patient(patient2)
                .doctor(doctor)
                .build();

        List<Opinion> opinionList = new ArrayList<>();
        opinionList.add(opinion1);
        opinionList.add(opinion2);

        //When
        List<OpinionDto> opinionDtos = opinionMapper.mapToOpinionDtoList(opinionList);

        //Then
        assertEquals(2, opinionDtos.size());
        assertEquals(opinion1.getRating(), opinionDtos.get(0).getRating());
        assertEquals(opinion2.getDescription(), opinionDtos.get(1).getDescription());
    }
}