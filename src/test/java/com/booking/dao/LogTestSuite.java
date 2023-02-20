package com.booking.dao;

import com.booking.entity.*;
import com.booking.repository.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class LogTestSuite {
    @Autowired
    private DoctorLogDao doctorLogDao;

    @Autowired
    private HolidayLogDao holidayLogDao;

    @Autowired
    private OpinionLogDao opinionLogDao;

    @Autowired
    private PatientLogDao patientLogDao;

    @Autowired
    private VisitLogDao visitLogDao;

    @Test
    public void createDoctorLogs(){
        //Given
        DoctorLog doctorLog = DoctorLog.builder()
                .doctorId(1L)
                .name("William")
                .surname("White")
                .specialization("Cardiologist")
                .rating(4.5)
                .timeOfChange(LocalDateTime.now())
                .build();

        //When
        doctorLogDao.save(doctorLog);

        //Then
        assertEquals(1, doctorLogDao.findAll().size());
        assertTrue(doctorLogDao.findById(doctorLog.getId()).isPresent());
        assertEquals(doctorLog.getDoctorId(), doctorLogDao.findById(doctorLog.getId()).get().getDoctorId());
        assertEquals(doctorLog.getName(), doctorLogDao.findById(doctorLog.getId()).get().getName());
        assertEquals(doctorLog.getSurname(), doctorLogDao.findById(doctorLog.getId()).get().getSurname());
        assertEquals(doctorLog.getSpecialization(), doctorLogDao.findById(doctorLog.getId()).get().getSpecialization());
        assertEquals(doctorLog.getRating(), doctorLogDao.findById(doctorLog.getId()).get().getRating(), 0.01);

        //CleanUp
        doctorLogDao.deleteById(doctorLog.getId());
    }

    @Test
    public void createHolidayLogs(){
        //Given
        Random random = new Random();
        List<HolidayLog> holidayLogList = new ArrayList<>();
        for(int i=0; i<9; i++) {
            HolidayLog holidayLog = HolidayLog.builder()
                    .holidayId(random.nextLong(2L, 200L))
                    .date(LocalDate.of(random.nextInt(2023, 2030), random.nextInt(1,12), random.nextInt(1,30)))
                    .timeOfChange(LocalDateTime.now())
                    .build();
            holidayLogList.add(holidayLog);
        }

        HolidayLog holidayLog = HolidayLog.builder()
                .holidayId(1L)
                .date(LocalDate.of(2025, 3, 5))
                .timeOfChange(LocalDateTime.now())
                .build();

        holidayLogList.add(holidayLog);

        //When
        for(int i=0; i<holidayLogList.size(); i++){
            holidayLogDao.save(holidayLogList.get(i));
        }

        //Then
        assertEquals(10, holidayLogDao.findAll().size());
        assertEquals(holidayLog.getDate(), holidayLogDao.findById(holidayLog.getId()).get().getDate());
        assertEquals(holidayLog.getHolidayId(), holidayLogDao.findById(holidayLog.getId()).get().getHolidayId());

        //CleanUp
        for(int j=0; j<holidayLogList.size(); j++){
            holidayLogDao.deleteById(holidayLogList.get(j).getId());
        }
    }

    @Test
    public void createOpinionLogs(){
        //Given
        OpinionLog opinionLog = OpinionLog.builder()
                .opinionId(5L)
                .description("Test description")
                .rating(4.0)
                .patientId(2L)
                .doctorId(4L)
                .timeOfChange(LocalDateTime.now())
                .build();

        //When
        opinionLogDao.save(opinionLog);

        //Then
        assertEquals(1, opinionLogDao.findAll().size());
        assertEquals(opinionLog.getOpinionId(), opinionLogDao.findById(opinionLog.getId()).get().getOpinionId());
        assertEquals(opinionLog.getDescription(), opinionLogDao.findById(opinionLog.getId()).get().getDescription());
        assertEquals(opinionLog.getRating(), opinionLogDao.findById(opinionLog.getId()).get().getRating(), 0.01);
        assertEquals(opinionLog.getPatientId(), opinionLogDao.findById(opinionLog.getId()).get().getPatientId());
        assertEquals(opinionLog.getDoctorId(), opinionLogDao.findById(opinionLog.getId()).get().getDoctorId());

        //CleanUp
        opinionLogDao.deleteById(opinionLog.getId());
    }

    @Test
    public void createPatientLogs(){
        //Given
        PatientLog patientLog = PatientLog.builder()
                .patientId(4L)
                .name("John")
                .surname("Smith")
                .pesel(83031117279L)
                .phoneNumber("+48 123 456 789")
                .timeOfChange(LocalDateTime.now())
                .build();

        //When
        patientLogDao.save(patientLog);

        //Then
        assertEquals(1, patientLogDao.findAll().size());
        assertEquals(patientLog.getPatientId(), patientLogDao.findById(patientLog.getId()).get().getPatientId());
        assertEquals(patientLog.getName(), patientLogDao.findById(patientLog.getId()).get().getName());
        assertEquals(patientLog.getSurname(), patientLogDao.findById(patientLog.getId()).get().getSurname());
        assertEquals(patientLog.getPesel(), patientLogDao.findById(patientLog.getId()).get().getPesel());
        assertEquals(patientLog.getPhoneNumber(), patientLogDao.findById(patientLog.getId()).get().getPhoneNumber());

        //CleanUp
        patientLogDao.deleteById(patientLog.getId());
    }

    @Test
    public void createVisitLogs(){
        //Given
        VisitLog visitLog = VisitLog.builder()
                .visitId(6L)
                .date(LocalDate.of(2023, 10, 5))
                .time(LocalTime.of(15, 00))
                .patientId(3L)
                .doctorId(7L)
                .timeOfChange(LocalDateTime.now())
                .build();

        //When
        visitLogDao.save(visitLog);

        //Then
        assertEquals(1, visitLogDao.findAll().size());
        assertEquals(visitLog.getVisitId(), visitLogDao.findById(visitLog.getId()).get().getVisitId());
        assertEquals(visitLog.getDate(), visitLogDao.findById(visitLog.getId()).get().getDate());
        assertEquals(visitLog.getTime(), visitLogDao.findById(visitLog.getId()).get().getTime());
        assertEquals(visitLog.getPatientId(), visitLogDao.findById(visitLog.getId()).get().getPatientId());
        assertEquals(visitLog.getDoctorId(), visitLogDao.findById(visitLog.getId()).get().getDoctorId());

        //CleanUp
        visitLogDao.deleteById(visitLog.getId());
    }
}