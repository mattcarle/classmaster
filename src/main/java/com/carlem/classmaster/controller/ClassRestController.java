package com.carlem.classmaster.controller;

import com.carlem.classmaster.model.Class;
import com.carlem.classmaster.persistence.ClassEntity;
import com.carlem.classmaster.persistence.ClassEntityMapper;
import com.carlem.classmaster.persistence.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/class")
public class ClassRestController {
    @Autowired
    private ClassEntityMapper classEntityMapper;

    @Autowired
    private ClassRepository classRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Class>> getAllClasses() {
        List<Class> classes = StreamSupport.stream(classRepository.findAll().spliterator(), false)
                .map(classEntityMapper::fromEntity)
                .collect(toList());
        return new ResponseEntity<>(classes, OK);
    }

    @PostMapping("/")
    public ResponseEntity<Class> createClass(@Valid @RequestBody Class newClass) {
        ClassEntity savedClass = classRepository.save(classEntityMapper.toEntity(newClass));
        return new ResponseEntity<>(classEntityMapper.fromEntity(savedClass), CREATED);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteClass(@PathVariable("id") UUID id) {
        try {
            classRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            return new ResponseEntity<>(NOT_FOUND);
        }
        return new ResponseEntity<>(OK);
    }
}
