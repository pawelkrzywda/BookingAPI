package com.booking.holidays.facade;

import com.booking.domain.HolidayDto;
import com.booking.entity.Holiday;
import com.booking.mapper.HolidayMapper;
import com.booking.service.HolidayService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class HolidaysFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(HolidaysFacade.class);

    private final HolidayService holidayService;
    private final HolidayMapper holidayMapper;

    public List<HolidayDto> fetchPublicHolidays() {
        List<Holiday> holidays = holidayMapper.mapToHoliday(holidayService.fetchPublicHolidays());
        return holidayMapper.mapToHolidayDto(holidays);
    }
}
