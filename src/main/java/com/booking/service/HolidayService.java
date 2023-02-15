package com.booking.service;

import com.booking.domain.HolidayDto;
import com.booking.exception.DoctorNotFoundException;
import com.booking.holidays.client.HolidaysClient;
import com.booking.mapper.HolidayMapper;
import com.booking.repository.HolidayDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HolidayService {
    private final HolidayMapper holidayMapper;
    private final HolidayDao holidayDao;
    private final HolidaysClient holidaysClient;

    public List<HolidayDto> fetchPublicHolidays(){
        return holidaysClient.getPublicHolidays();
    }
}