package com.booking.service;

import com.booking.domain.HolidayDto;
import com.booking.entity.Holiday;
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
    private final HolidayLogService holidayLogService;
    private final HolidaysClient holidaysClient;

    public List<HolidayDto> fetchPublicHolidays(){
        return holidaysClient.getPublicHolidays();
    }

    public void createPublicHolidays(List<HolidayDto> holidayDtos){
        List<Holiday> holidays = holidayMapper.mapToHoliday(holidayDtos);
        for(int i=0; i<holidays.size(); i++){
            holidayDao.save(holidays.get(i));
            holidayLogService.createHolidayLog(holidays.get(i));
        }
    }
}