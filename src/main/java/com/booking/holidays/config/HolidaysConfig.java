package com.booking.holidays.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class HolidaysConfig {

    @Value("${publicholidays.api.endpoint.prod}")
    private String publicHolidaysApiEndpoint;
    @Value("${publicholidays.app.key}")
    private String publicHolidaysAppKey;
    @Value("${publicholidays.app.host}")
    private String publicHolidaysHost;
}