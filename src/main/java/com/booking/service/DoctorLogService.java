package com.booking.service;

import com.booking.entity.Doctor;
import com.booking.entity.DoctorLog;
import com.booking.repository.DoctorLogDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DoctorLogService {
    private final DoctorLogDao doctorLogDao;

    public void createDoctorLog(Doctor doctor){
        DoctorLog doctorLog = DoctorLog.builder()
                .doctorId(doctor.getId())
                .name(doctor.getName())
                .surname(doctor.getSurname())
                .specialization(doctor.getSpecialization())
                .rating(doctor.getRating())
                .timeOfChange(LocalDateTime.now())
                .build();

        doctorLogDao.save(doctorLog);
    }
}