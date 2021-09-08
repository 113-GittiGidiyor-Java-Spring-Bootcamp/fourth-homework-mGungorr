package dev.patika.homework.service;

import dev.patika.homework.dto.CourseDTO;
import dev.patika.homework.dto.StudentDTO;
import dev.patika.homework.mappers.CourseMapper;
import dev.patika.homework.model.Course;
import dev.patika.homework.model.Student;
import dev.patika.homework.repository.CourseDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService{

    private final CourseDAO courseDAO;
    private final CourseMapper courseMapper;

    @Transactional
    public List<CourseDTO> findAll() {
        List<CourseDTO> courseList = new ArrayList<>();
        Iterable<Course> courseIter = courseDAO.findAll();
        for (Course course : courseIter) {
            CourseDTO courseDTO = courseMapper.mapFromCoursetoCourseDTO(course);
            courseList.add(courseDTO);
        }
        return courseList;
    }

    @Transactional
    public CourseDTO findById(int id) {
        return courseMapper.mapFromCoursetoCourseDTO(courseDAO.findById(id).get());
    }

    @Transactional
    public Optional<Course> save(CourseDTO courseDTO) {
//        calculateAgeFromBirthDate(studentDTO.getStudentBirthDate());

        Course course = courseMapper.mapFromCourseDTOtoCourse(courseDTO);

        return Optional.of(courseDAO.save(course));
    }

    public CourseDTO deleteById(int id) {
//        studentDAO.deleteById(id);
        Course course = courseDAO.findById(id).get();

        CourseDTO courseDTO = courseMapper.mapFromCoursetoCourseDTO(course);
        courseDAO.deleteById(id);
        return courseDTO;
    }

    @Transactional
    public Optional<Course> update(CourseDTO courseDTO, int id) {

//        calculateAgeFromBirthDate(courseDTO.getStudentBirthDate());
        Course course = courseMapper.mapFromCourseDTOtoCourse(courseDTO);

        course.setId(id);
        return Optional.of(courseDAO.save(course));
    }

}
