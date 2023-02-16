package com.booking.dao;

import com.booking.entity.Doctor;
import com.booking.entity.Patient;
import com.booking.entity.Visit;
import com.booking.repository.DoctorDao;
import com.booking.repository.PatientDao;
import com.booking.repository.VisitDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class VisitTestSuite {
    @Autowired
    private PatientDao patientDao;

    @Autowired
    private DoctorDao doctorDao;

    @Autowired
    private VisitDao visitDao;

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

    public Visit generateVisit(Patient patient, Doctor doctor){
        return Visit.builder()
                .date(LocalDate.of(2023, 10, 5))
                .time(LocalTime.of(15, 00))
                .patient(patient)
                .doctor(doctor)
                .build();
    }

    @Test
    public void testCreateVisit(){
        //Given
        Patient patient = generatePatient();
        Doctor doctor = generateDoctor();
        Visit visit = generateVisit(patient, doctor);
        patient.getVisits().add(visit);
        doctor.getVisits().add(visit);

        //When
        visitDao.save(visit);

        //Then
        assertEquals(1, visitDao.findAll().size());
        assertTrue(visitDao.findById(visit.getId()).isPresent());

        //CleanUp
        visitDao.deleteById(visit.getId());
        patientDao.deleteById(patient.getId());
        doctorDao.deleteById(doctor.getId());
    }

    @Test
    public void testUpdateVisit(){
        //Given
        Patient patient = generatePatient();
        Doctor doctor = generateDoctor();
        Visit visit = generateVisit(patient, doctor);
        patient.getVisits().add(visit);
        doctor.getVisits().add(visit);
        visitDao.save(visit);

        //When
        visit.setDate(LocalDate.of(2023, 10, 15));
        visitDao.save(visit);

        //Then
        Optional<Visit> updatedVisit = visitDao.findById(visit.getId());
        assertTrue(updatedVisit.isPresent());
        assertTrue(updatedVisit.get().getDate().isEqual(LocalDate.of(2023,10,15)));

        //CleanUp
        visitDao.deleteById(visit.getId());
        doctorDao.deleteById(doctor.getId());
        patientDao.deleteById(patient.getId());
    }

    @Test
    public void testDeleteVisit(){
        //Given
        Patient patient = generatePatient();
        Doctor doctor = generateDoctor();
        Visit visit = generateVisit(patient, doctor);
        patient.getVisits().add(visit);
        doctor.getVisits().add(visit);
        visitDao.save(visit);

        //When
        visitDao.deleteById(visit.getId());

        //Then
        assertEquals(0, visitDao.findAll().size());
        assertFalse(visitDao.findById(visit.getId()).isPresent());
        assertTrue(patientDao.findById(patient.getId()).isPresent());
        assertTrue(doctorDao.findById(doctor.getId()).isPresent());

        //CleanUp
        patientDao.deleteById(patient.getId());
        doctorDao.deleteById(doctor.getId());
    }
}