package com.booking.entity;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

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

    @OneToMany(
            mappedBy = "doctor",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY)
    @Builder.Default
    private List<Visit> visits = new ArrayList<>();
}