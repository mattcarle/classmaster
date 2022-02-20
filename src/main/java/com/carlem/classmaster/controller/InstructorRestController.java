package com.carlem.classmaster.controller;

import com.carlem.classmaster.model.Instructor;
import com.carlem.classmaster.persistence.InstructorEntity;
import com.carlem.classmaster.persistence.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/instructor")
public class InstructorRestController {
    @Autowired
    private InstructorRepository instructorRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Instructor>> getAllInstructors() {
        List<Instructor> instructors = StreamSupport.stream(instructorRepository.findAll().spliterator(), false)
                .map(InstructorEntity::toModel)
                .collect(toList());
        return new ResponseEntity<>(instructors, OK);
    }

    @PostMapping("/")
    public ResponseEntity<Void> createInstructor(@Valid @RequestBody Instructor instructor) {
        instructorRepository.save(InstructorEntity.fromModel(instructor));
        return new ResponseEntity<>(CREATED);
    }
}

