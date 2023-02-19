package com.booking.dao;

import com.booking.entity.Doctor;
import com.booking.entity.Opinion;
import com.booking.entity.Patient;
import com.booking.repository.DoctorDao;
import com.booking.repository.OpinionDao;
import com.booking.repository.PatientDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Optional;
import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OpinionTestSuite {
    @Autowired
    OpinionDao opinionDao;

    @Autowired
    private PatientDao patientDao;

    @Autowired
    private DoctorDao doctorDao;

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
    public void testCreateOpinion(){
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
        opinionDao.save(opinion);

        //Then
        assertEquals(1, opinionDao.findAll().size());
        assertTrue(opinionDao.findById(opinion.getId()).isPresent());

        //CleanUp
        opinionDao.deleteById(opinion.getId());
        patientDao.deleteById(patient.getId());
        doctorDao.deleteById(doctor.getId());
    }

    @Test
    public void testUpdateOpinion(){
        //Given
        Patient patient = generatePatient();
        Doctor doctor = generateDoctor();
        Opinion opinion = Opinion.builder()
                .description("Opinion description for tests")
                .rating(4.0)
                .patient(patient)
                .doctor(doctor)
                .build();
        opinionDao.save(opinion);

        //When
        opinion.setRating(4.7);
        opinionDao.save(opinion);

        //Then
        Optional<Opinion> updatedOpinion = opinionDao.findById(opinion.getId());
        assertTrue(updatedOpinion.isPresent());
        assertTrue(updatedOpinion.get().getRating() == 4.7);

        //CleanUp
        opinionDao.deleteById(opinion.getId());
        doctorDao.deleteById(doctor.getId());
        patientDao.deleteById(patient.getId());
    }

    @Test
    public void testDeleteOpinion(){
        //Given
        Patient patient = generatePatient();
        Doctor doctor = generateDoctor();
        Opinion opinion = Opinion.builder()
                .description("Opinion description for tests")
                .rating(4.0)
                .patient(patient)
                .doctor(doctor)
                .build();
        opinionDao.save(opinion);

        //When
        opinionDao.deleteById(opinion.getId());

        //Then
        assertEquals(0, opinionDao.findAll().size());
        assertFalse(opinionDao.findById(opinion.getId()).isPresent());
        assertTrue(patientDao.findById(patient.getId()).isPresent());
        assertTrue(doctorDao.findById(doctor.getId()).isPresent());

        //CleanUp
        patientDao.deleteById(patient.getId());
        doctorDao.deleteById(doctor.getId());
    }
}