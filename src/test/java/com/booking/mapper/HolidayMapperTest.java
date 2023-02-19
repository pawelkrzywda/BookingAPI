package com.booking.mapper;

import com.booking.domain.HolidayDto;
import com.booking.entity.Holiday;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class HolidayMapperTest {
    @Autowired
    HolidayMapper holidayMapper;

    @Test
    public void shouldMapToHoliday(){
        //Given
        Random random = new Random();
        List<HolidayDto> holidayDtoList = new ArrayList<>();
        for(int i=0; i<9; i++) {
            HolidayDto holidayDto = HolidayDto.builder()
                    .date(LocalDate.of(random.nextInt(2023, 2030), random.nextInt(1,12), random.nextInt(1,30)))
                    .build();
            holidayDtoList.add(holidayDto);
        }
        HolidayDto holidayDto = HolidayDto.builder()
                .date(LocalDate.of(2023, 11, 5))
                .build();
        holidayDtoList.add(holidayDto);

        //When
        List<Holiday> holidayList= holidayMapper.mapToHoliday(holidayDtoList);

        //Then
        assertEquals(10, holidayList.size());
        assertEquals(LocalDate.of(2023,11,5), holidayList.get(9).getDate());
    }

    @Test
    public void shouldMapToHolidayDto(){
        //Given
        Random random = new Random();
        List<Holiday> holidayList = new ArrayList<>();
        for(int i=0; i<9; i++) {
            Holiday holiday = Holiday.builder()
                    .date(LocalDate.of(random.nextInt(2023, 2030), random.nextInt(1,12), random.nextInt(1,30)))
                    .build();
            holidayList.add(holiday);
        }
        Holiday holiday = Holiday.builder()
                .date(LocalDate.of(2025, 3, 5))
                .build();
        holidayList.add(holiday);

        //When
        List<HolidayDto> holidayDtoList= holidayMapper.mapToHolidayDto(holidayList);

        //Then
        assertEquals(10, holidayList.size());
        assertEquals(LocalDate.of(2025,3,5), holidayList.get(9).getDate());
    }
}