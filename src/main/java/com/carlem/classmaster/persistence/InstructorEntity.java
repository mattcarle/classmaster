package com.carlem.classmaster.persistence;

import com.carlem.classmaster.model.Instructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Builder
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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "instructor_id")
    @ToString.Exclude
    private Set<ClassEntity> classes;

    public void addClass(ClassEntity classEntity) {
        classes.add(classEntity);
        classEntity.setInstructor(this);
    }

    public void removeClass(ClassEntity classEntity) {
        classes.remove(classEntity);
        classEntity.setInstructor(null);
    }

    public Instructor toModel() {
        return new Instructor(id, firstName, lastName, emailAddress, phoneNumber, dateOfBirth);
    }

    public static InstructorEntity fromModel(Instructor instructor) {
        return new InstructorEntity(instructor.id(), instructor.firstName(), instructor.lastName(),
                instructor.emailAddress(), instructor.phoneNumber(), instructor.dateOfBirth(), new HashSet<>());
    }
}
