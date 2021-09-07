package dev.patika.homework.service;

import dev.patika.homework.model.Course;
import dev.patika.homework.model.Student;
import dev.patika.homework.repository.CourseDAO;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService implements BaseService<Course> {

    private final CourseDAO courseDAO;

    public CourseService(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    @Override
    @Transactional
    public List<Course> findAll() {
        List<Course> courseList = new ArrayList<>();
        Iterable<Course> courseIter = courseDAO.findAll();
        for (Course course : courseIter) {
            courseList.add(course);
        }
        return courseList;
    }

    @Override
    @Transactional
    public Course findById(int id) {
        return courseDAO.findById(id).get();
    }

    @Override
    @Transactional
    public Course save(Course course) {
        return courseDAO.save(course);
    }

    @Override
    public void deleteById(int id) {
        courseDAO.deleteById(id);
    }

    @Override
    @Transactional
    public Course update(Course course) {
        return courseDAO.save(course);
    }

    public int getNumberOfCourses() {
        return courseDAO.getNumberOfCourses();
    }


    public List<Student> findByAgeGreaterThanAndAgeBefore(int studentID, LocalDate birthDate) {
        return courseDAO.getAge();
    }
}
