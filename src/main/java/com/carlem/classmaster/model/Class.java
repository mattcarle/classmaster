package com.carlem.classmaster.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.EnumSet;
import java.util.UUID;

public record Class (
    UUID id,
    @NotBlank String description,
    @NotNull EnumSet<DayOfWeek> days,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    @NotNull LocalTime startTime,
    @NotNull @Min(5) @Max(1440) Integer durationMinutes,
    @NotNull LocalDate startDate,
    LocalDate endDate,
    @NotNull @Min(1) Integer maxParticipants,
    @NotNull @Min(0) Integer price,
    UUID instructorId) implements Serializable {
}
