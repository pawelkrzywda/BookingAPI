package com.booking;

import com.booking.domain.HolidayDto;
import com.booking.holidays.facade.HolidaysFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/trello")
@RequiredArgsConstructor
@CrossOrigin("*")
public class PublicHolidaysController {

    private final HolidaysFacade holidaysFacade;

    @GetMapping("publicholidays")
    public ResponseEntity<List<HolidayDto>> getPublicHolidays() {
        return ResponseEntity.ok(holidaysFacade.fetchPublicHolidays());
    }
}