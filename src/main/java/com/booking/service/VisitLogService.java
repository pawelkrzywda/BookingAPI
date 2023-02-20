package com.booking.service;

import com.booking.entity.Visit;
import com.booking.entity.VisitLog;
import com.booking.repository.VisitLogDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class VisitLogService {
    private  final VisitLogDao visitLogDao;

    public void createVisitLog(Visit visit){
        VisitLog visitLog = VisitLog.builder()
                .visitId(visit.getId())
                .date(visit.getDate())
                .time(visit.getTime())
                .patientId(visit.getPatient().getId())
                .doctorId(visit.getDoctor().getId())
                .timeOfChange(LocalDateTime.now())
                .build();

        visitLogDao.save(visitLog);
    }
}