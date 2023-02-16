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
public class DoctorTestSuite {
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
    public void testCreateDoctor(){
        //Given
        Patient patient = generatePatient();
        Doctor doctor = generateDoctor();
        Visit visit = generateVisit(patient, doctor);
        patient.getVisits().add(visit);
        doctor.getVisits().add(visit);

        //When
        doctorDao.save(doctor);

        //Then
        assertEquals(1, doctorDao.findAll().size());
        assertTrue(doctorDao.findById(doctor.getId()).isPresent());

        //CleanUp
        doctorDao.deleteById(doctor.getId());
        patientDao.deleteById(patient.getId());
        visitDao.deleteById(visit.getId());
    }

    @Test
    public void testUpdateDoctor(){
        //Given
        Patient patient = generatePatient();
        Doctor doctor = generateDoctor();
        Visit visit = generateVisit(patient, doctor);
        patient.getVisits().add(visit);
        doctor.getVisits().add(visit);
        doctorDao.save(doctor);

        //When
        doctor.setRating(4.8);
        doctorDao.save(doctor);

        //Then
        Optional<Doctor> updatedDoctor = doctorDao.findById(doctor.getId());
        assertTrue(updatedDoctor.isPresent());
        assertTrue(updatedDoctor.get().getRating() == 4.8);

        //CleanUp
        doctorDao.deleteById(doctor.getId());
        patientDao.deleteById(patient.getId());
        visitDao.deleteById(visit.getId());
    }

    @Test
    public void testDeleteDoctor(){
        //Given
        Patient patient = generatePatient();
        Doctor doctor = generateDoctor();
        Visit visit = generateVisit(patient, doctor);
        patient.getVisits().add(visit);
        doctor.getVisits().add(visit);
        doctorDao.save(doctor);

        //When
        doctorDao.deleteById(doctor.getId());

        //Then
        assertEquals(0, doctorDao.findAll().size());
        assertFalse(doctorDao.findById(doctor.getId()).isPresent());
        assertTrue(patientDao.findById(patient.getId()).isPresent());
        assertTrue(visitDao.findById(visit.getId()).isPresent());

        //CleanUp
        patientDao.deleteById(patient.getId());
        visitDao.deleteById(visit.getId());
    }
}