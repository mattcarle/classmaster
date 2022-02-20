package com.carlem.classmaster.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.EnumSet;
import java.util.Objects;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name="class")
public class ClassEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String description;

    private EnumSet<DayOfWeek> days;

    private LocalTime startTime;

    private Integer durationMinutes;

    private LocalDate startDate;

    private LocalDate endDate;

    private Integer maxParticipants;

    private Integer price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id", insertable = false, updatable = false)
    private InstructorEntity instructor;

    @Column(name = "instructor_id")
    private UUID instructorId;

    // hashCode()/equals() need to be able to handle the case when entity id is null (i.e. an unpersisted entity).
    // A default implementation of hashCode()/equals() based on the id field would treat two entities with null ids
    // as equal, which would result in incorrect behaviour when removing an entity from a HashSet, for example.
    //
    // Note also that hashCode() returns a constant value - if we used the default implementation based on id
    // (Objects.hash(id)) then an object would return a different hashCode after it has been persisted (i.e. because
    // id is null before, but non-null after persistence). Instead, by returning the same hashCode() for all
    // instances of the entity, we ensure that equals() always gets invoked which will test for object reference
    // equality as the first step.
    //
    // @see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (!(that instanceof InstructorEntity)) {
            return false;
        }
        return id != null && id.equals(((InstructorEntity)that).getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
