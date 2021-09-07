package dev.patika.homework.mappers;

import dev.patika.homework.dto.CourseDTO;
import dev.patika.homework.model.Course;
import org.mapstruct.Mapper;

@Mapper
public interface CourseMapper {
    Course mapFromCourseDTOtoCourse(CourseDTO dto);
    CourseDTO mapFromCoursetoCourseDTO(Course course);
}
