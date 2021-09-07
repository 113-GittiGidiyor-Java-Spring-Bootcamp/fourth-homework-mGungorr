package dev.patika.homework.mappers;

import dev.patika.homework.dto.StudentDTO;
import dev.patika.homework.model.Student;
import org.mapstruct.Mapper;

@Mapper
public interface StudentMapper {
    Student mapFromStudentDTOtoStudent(StudentDTO dto);
    StudentDTO mapFromStudenttoStudentDTO(Student student);
}
