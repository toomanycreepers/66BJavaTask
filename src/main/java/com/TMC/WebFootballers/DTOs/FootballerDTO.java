package com.TMC.WebFootballers.DTOs;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FootballerDTO {
    @Min(1)
    private Long Id;
    @NotBlank
    @Size(min = 1,max = 20)
    private String FirstName;
    @NotBlank
    @Size(min = 1,max = 20)
    private String Surname;
    @NotNull
    private Boolean IsMale;
    @NotBlank
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}")
    private String Dob;
    @NotBlank
    private String Team;
    @Min(0)
    @NotNull
    private Integer Country;
}
