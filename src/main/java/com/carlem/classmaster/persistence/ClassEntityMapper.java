package com.carlem.classmaster.persistence;

import org.mapstruct.Mapper;
import com.carlem.classmaster.model.Class;

@Mapper(componentModel = "spring")
public interface ClassEntityMapper {
    ClassEntity toEntity(Class classDto);
    Class fromEntity(ClassEntity classEntity);
}
