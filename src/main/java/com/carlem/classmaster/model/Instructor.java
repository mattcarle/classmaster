package com.carlem.classmaster.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

public record Instructor(
    UUID id,
    @NotBlank String firstName,
    @NotBlank String lastName,
    @NotBlank String emailAddress,
    @NotBlank String phoneNumber,
    @NotNull LocalDate dateOfBirth) {
}
