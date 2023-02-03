package com.booking.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Patient {

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
    @Positive
    private Long pesel;

    @NonNull
    private String phoneNumber;

    @OneToMany(
            mappedBy = "patient",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY)
    @Builder.Default
    private List<Visit> visits = new ArrayList<>();
}