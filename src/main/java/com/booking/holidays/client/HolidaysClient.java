package com.booking.holidays.client;

import com.booking.domain.HolidayDto;
import com.booking.holidays.config.HolidaysConfig;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class HolidaysClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(HolidaysClient.class);
    private final RestTemplate restTemplate;
    private final HolidaysConfig holidaysConfig;

    public List<HolidayDto> getPublicHolidays() {
        List<HolidayDto> holidaysList = new ArrayList<>();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://public-holiday.p.rapidapi.com/2019/US"))
                .header("X-RapidAPI-Key", "6200e94becmsh46379f482b341c0p18b79ajsn90731b264090")
                .header("X-RapidAPI-Host", "public-holiday.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        URI url = request.uri();

        try {
            HolidayDto[] holidayResponse = restTemplate.getForObject(url, HolidayDto[].class);
            return Optional.ofNullable(holidayResponse)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList())
                    .stream()
                    .filter(p -> Objects.nonNull(p.getId()) && Objects.nonNull(p.getDate()))
                    .collect(Collectors.toList());

        }catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }
}