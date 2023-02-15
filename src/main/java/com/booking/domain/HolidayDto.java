package com.booking.domain;

import lombok.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
@Getter
public class HolidayDto {

    private Long id;
    private LocalDate date;
}