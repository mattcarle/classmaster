package com.carlem.classmaster.persistence;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.util.EnumSet;

import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;
import static org.junit.jupiter.api.Assertions.*;

class DaysOfWeekConverterTest {
    private final DaysOfWeekConverter converter = new DaysOfWeekConverter();

    @Test
    void testConvertToDatabaseColumn() {
        assertEquals("Mo,We,Fr", converter.convertToDatabaseColumn(EnumSet.of(MONDAY, WEDNESDAY, FRIDAY)));
        assertEquals("", converter.convertToDatabaseColumn(EnumSet.noneOf(DayOfWeek.class)));
    }

    @Test
    void testConvertToEntityAttribute() {
        assertEquals(EnumSet.of(MONDAY, WEDNESDAY, FRIDAY), converter.convertToEntityAttribute("Mo,We,Fr"));
        assertEquals(EnumSet.of(MONDAY, WEDNESDAY, FRIDAY), converter.convertToEntityAttribute("Fr,We,Mo"));
        assertEquals(EnumSet.of(TUESDAY, THURSDAY), converter.convertToEntityAttribute(" Th , Tu,"));
        assertEquals(EnumSet.of(TUESDAY, THURSDAY), converter.convertToEntityAttribute(" Th , Tu, XY"));
        assertEquals(EnumSet.noneOf(DayOfWeek.class), converter.convertToEntityAttribute(""));
        assertEquals(EnumSet.noneOf(DayOfWeek.class), converter.convertToEntityAttribute(null));
    }
}