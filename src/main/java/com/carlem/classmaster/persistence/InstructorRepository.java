package com.carlem.classmaster.persistence;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface InstructorRepository extends CrudRepository<InstructorEntity, UUID> {
}
