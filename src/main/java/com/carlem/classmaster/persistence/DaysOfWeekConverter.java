package com.carlem.classmaster.persistence;

import com.google.common.collect.HashBiMap;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;

@Converter(autoApply = true)
public class DaysOfWeekConverter implements AttributeConverter<EnumSet<DayOfWeek>, String> {
    private static final Map<DayOfWeek, String> MAP_DAY_OF_WEEK_TO_ABBREV = Map.of(
        MONDAY, "Mo",
        TUESDAY, "Tu",
        WEDNESDAY, "We",
        THURSDAY, "Th",
        FRIDAY, "Fr",
        SATURDAY, "Sa",
        SUNDAY, "Su");
    private static final Map<String, DayOfWeek> MAP_ABBREV_TO_DAY_OF_WEEK = HashBiMap.create(MAP_DAY_OF_WEEK_TO_ABBREV).inverse();
    private static final Set<String> ABBREVS = MAP_ABBREV_TO_DAY_OF_WEEK.keySet();

    @Override
    public String convertToDatabaseColumn(EnumSet<DayOfWeek> attribute) {
        return attribute.stream().map(MAP_DAY_OF_WEEK_TO_ABBREV::get).collect(Collectors.joining(","));
    }

    @Override
    public EnumSet<DayOfWeek> convertToEntityAttribute(String dbData) {
        return dbData == null ? EnumSet.noneOf(DayOfWeek.class) : Arrays.stream(dbData.trim().split("\\s*,\\s*"))
                .filter(ABBREVS::contains)
                .map(MAP_ABBREV_TO_DAY_OF_WEEK::get)
                .collect(Collectors.toCollection(() -> EnumSet.noneOf(DayOfWeek.class)));
    }
}
