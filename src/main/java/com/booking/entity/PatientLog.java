package com.booking.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class PatientLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NonNull
    private Long patientId;

    @NonNull
    @Column(length = 100)
    @Length(min = 2, max = 100)
    private String name;

    @NonNull
    @Column(length = 100)
    @Length(min = 2, max = 100)
    private String surname;

    @NonNull
    @Positive
    private Long pesel;

    @NonNull
    private String phoneNumber;

    @NonNull
    private LocalDateTime timeOfChange;
}