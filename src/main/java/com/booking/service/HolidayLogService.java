package com.booking.service;

import com.booking.entity.Holiday;
import com.booking.entity.HolidayLog;
import com.booking.repository.HolidayLogDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class HolidayLogService {
    private final HolidayLogDao holidayLogDao;

    public void createHolidayLog(Holiday holiday){
        HolidayLog holidayLog = HolidayLog.builder()
                .holidayId(holiday.getId())
                .date(holiday.getDate())
                .timeOfChange(LocalDateTime.now())
                .build();

        holidayLogDao.save(holidayLog);
    }
}