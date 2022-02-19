package com.carlem.classmaster.persistence;

import com.carlem.classmaster.model.Instructor;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity(name="instructor")
public class InstructorEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String firstName;

    private String lastName;

    private String emailAddress;

    private String phoneNumber;

    private LocalDate dateOfBirth;

    public Instructor toModel() {
        return new Instructor(id, firstName, lastName, emailAddress, emailAddress, dateOfBirth);
    }

    public static InstructorEntity fromModel(Instructor instructor) {
        return new InstructorEntity(instructor.id(), instructor.firstName(), instructor.lastName(), instructor.emailAddress(),
                instructor.phoneNumber(), instructor.dateOfBirth());
    }
}
