package com.TMC.WebFootballers.DTOs;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FootballerCreationDTO {
    @NotBlank
    @Size(min = 1,max = 20)
    private final String FirstName;
    @NotBlank
    @Size(min = 1,max = 20)
    private final String Surname;
    @NotNull
    private final Boolean IsMale;
    @NotBlank
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}")
    private final String Dob;
    @NotBlank
    private final String Team;
    @Min(0)
    @NotNull
    private final Integer Country;
}