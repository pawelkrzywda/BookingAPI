package com.booking.entity;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class VisitLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NonNull
    private Long visitId;

    @NonNull
    private LocalDate date;

    @NonNull
    private LocalTime time;

    @NonNull
    private Long patientId;

    @NonNull
    private Long doctorId;

    @NonNull
    private LocalDateTime timeOfChange;
}