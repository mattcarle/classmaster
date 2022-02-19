package com.carlem.classmaster.controller;

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
@RequestMapping("/api")
public class InstructorRestController {
    @Autowired
    private InstructorRepository instructorRepository;

    @GetMapping("/instructor/all")
    public ResponseEntity<List<InstructorEntity>> getAllInstructors() {
        List<InstructorEntity> instructors = StreamSupport.stream(instructorRepository.findAll().spliterator(), false).collect(toList());
        return new ResponseEntity<>(instructors, OK);
    }

    @PostMapping("/instructor")
    public ResponseEntity<Void> createInstructor(@Valid @RequestBody InstructorEntity instructor) {
        instructorRepository.save(instructor);
        return new ResponseEntity<>(CREATED);
    }
}

