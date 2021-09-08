package dev.patika.homework.service;

import dev.patika.homework.dto.CourseDTO;
import dev.patika.homework.dto.StudentDTO;
import dev.patika.homework.exceptions.CourseIsAlreadyExistException;
import dev.patika.homework.exceptions.StudentNumberForOneCourseExceededException;
import dev.patika.homework.mappers.CourseMapper;
import dev.patika.homework.model.Course;
import dev.patika.homework.model.Student;
import dev.patika.homework.repository.CourseDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
        CourseExists(courseDTO.getCourseCode());
        NumberOfStudentsInCourse(courseDTO.getId());
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
        CourseExists(courseDTO.getCourseCode());
        NumberOfStudentsInCourse(id);
        Course course = courseMapper.mapFromCourseDTOtoCourse(courseDTO);

        course.setId(id);
        return Optional.of(courseDAO.save(course));
    }

    private void CourseExists(int courseCode) {

        if (courseDAO.findCourseByCourseCode(courseCode).isPresent()) {
            throw new CourseIsAlreadyExistException("Course Is Already Exist!");
        }
    }

    private void NumberOfStudentsInCourse(int numberOfStudents) {
        if (numberOfStudents > 20) {
            throw new StudentNumberForOneCourseExceededException("This course is limited to 20 people!");
        }
    }

}
