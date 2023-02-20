package com.booking.service;

import com.booking.entity.Opinion;
import com.booking.entity.OpinionLog;
import com.booking.repository.OpinionLogDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OpinionLogService {
    private final OpinionLogDao opinionLogDao;

    public void createOpinionLog(Opinion opinion){
        OpinionLog opinionLog = OpinionLog.builder()
                .opinionId(opinion.getId())
                .description(opinion.getDescription())
                .rating(opinion.getRating())
                .patientId(opinion.getPatient().getId())
                .doctorId(opinion.getDoctor().getId())
                .timeOfChange(LocalDateTime.now())
                .build();

        opinionLogDao.save(opinionLog);
    }
}