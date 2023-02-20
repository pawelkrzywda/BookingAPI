package com.booking.service;

import com.booking.entity.Patient;
import com.booking.entity.PatientLog;
import com.booking.repository.PatientLogDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PatientLogService {
    private final PatientLogDao patientLogDao;

    public void createPatientLog(Patient patient){
        PatientLog patientLog = PatientLog.builder()
                .patientId(patient.getId())
                .name(patient.getName())
                .surname(patient.getSurname())
                .pesel(patient.getPesel())
                .phoneNumber(patient.getPhoneNumber())
                .timeOfChange(LocalDateTime.now())
                .build();

        patientLogDao.save(patientLog);
    }
}