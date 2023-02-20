package com.booking.entity;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class DoctorLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NonNull
    private Long doctorId;

    @NonNull
    @Column(length = 100)
    @Length(min = 2, max = 100)
    private String name;

    @NonNull
    @Column(length = 100)
    @Length(min = 2, max = 100)
    private String surname;

    @NonNull
    @Column(length = 100)
    @Length(min = 3, max = 100)
    private String specialization;

    @NonNull
    @Positive
    private double rating;

    @NonNull
    private LocalDateTime timeOfChange;
}