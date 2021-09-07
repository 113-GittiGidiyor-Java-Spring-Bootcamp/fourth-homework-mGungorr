package dev.patika.homework.mappers;

import dev.patika.homework.dto.InstructorDTO;
import dev.patika.homework.model.Instructor;
import org.mapstruct.Mapper;

@Mapper
public interface InstructorMapper {
    Instructor mapFromInstructorDTOtoInstructor(InstructorDTO dto);
    InstructorDTO mapFromInstructortoInstructorDTO(Instructor instructor);
}
