package com.booking.mapper;

import com.booking.domain.DoctorDto;
import com.booking.domain.HolidayDto;
import com.booking.entity.Doctor;
import com.booking.entity.Holiday;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HolidayMapper {

    public List<Holiday> mapToHoliday(final List<HolidayDto> holidayDto) {
        return holidayDto.stream()
                .map(holidayDto1 -> new Holiday(holidayDto1.getId(), holidayDto1.getDate()))
                .collect(Collectors.toList());
    }

    public List<HolidayDto> mapToHolidayDto(final List<Holiday> holiday) {
        return holiday.stream()
                .map(holiday1 -> new HolidayDto(holiday1.getId(), holiday1.getDate()))
                .collect(Collectors.toList());
    }
}