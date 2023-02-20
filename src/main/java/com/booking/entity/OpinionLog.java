package com.booking.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class OpinionLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NonNull
    private Long opinionId;

    @NonNull
    @Column(length = 200)
    @Length(min = 10, max = 200)
    private String description;

    @NonNull
    private double rating;

    @NonNull
    private Long patientId;

    @NonNull
    private Long doctorId;

    @NonNull
    private LocalDateTime timeOfChange;
}