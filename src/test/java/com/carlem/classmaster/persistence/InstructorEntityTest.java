package com.carlem.classmaster.persistence;

import com.carlem.classmaster.model.Instructor;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.EnumSet;
import java.util.UUID;

import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.WEDNESDAY;
import static org.junit.jupiter.api.Assertions.*;

class InstructorEntityTest {
    @Test
    void testAddClass() {
        InstructorEntity instructorEntity = newInstructorEntity();
        instructorEntity.addClass(newClassEntity(instructorEntity));
        assertEquals(1, instructorEntity.getClasses().size());
    }

    private InstructorEntity newInstructorEntity() {
        return InstructorEntity.fromModel(new Instructor(UUID.randomUUID(), "Joe", "Bloggs",
                "jbloggs@acme.com", "01234 567890", LocalDate.of(1980, 12, 31)));
    }

    private ClassEntity newClassEntity(InstructorEntity instructorEntity) {
        return new ClassEntity(null,
                "Intermediate Pilates",
                EnumSet.of(MONDAY, WEDNESDAY, FRIDAY),
                LocalTime.of(13, 0, 0),
                60,
                LocalDate.of(2022, 2, 21),
                LocalDate.of(2022, 12, 31),
                8,
                50,
                instructorEntity,
                instructorEntity.getId());
    }
}