package dev.patika.homework.repository;

import dev.patika.homework.model.Course;
import dev.patika.homework.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseDAO extends CrudRepository<Course,Integer> {

    @Query("select count(e) from Course e")
    int getNumberOfCourses();

}
