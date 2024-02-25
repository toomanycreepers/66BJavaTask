package com.TMC.WebFootballers.Entities;

import com.TMC.WebFootballers.Utility.Country;
import com.TMC.WebFootballers.Utility.CountryDBConverter;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "Footballers")
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
public class Footballer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private long id;
    @Column(name = "FirstName")
    @NonNull
    @Setter
    private String firstName;
    @Column(name = "Surname")
    @NonNull
    @Setter
    private String surname;
    @Column(name = "IsMale")
    @NonNull
    @Setter
    private Boolean isMale;
    @Column(name = "DOB")
    @NonNull
    @Setter
    private Date dateOfBirth;
    @ManyToOne
    @JoinColumn(name = "TeamId")
    @NonNull
    @Setter
    private FootballTeam team;
    @Column(name = "Country")
    @Convert(converter = CountryDBConverter.class)
    @NonNull
    @Setter
    private Country country;
}
