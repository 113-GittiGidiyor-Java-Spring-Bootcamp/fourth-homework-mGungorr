package dev.patika.homework.controller;

import dev.patika.homework.model.Course;
import dev.patika.homework.model.Student;
import dev.patika.homework.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CourseController {

    CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/courses")
    public ResponseEntity<List<Course>> findAll() {
        return new ResponseEntity<>(courseService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/courses")
    public Course saveCourse(@RequestBody Course course) {
        return courseService.save(course);
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<Course> findCourseById(@PathVariable int id){
        return new ResponseEntity<>(courseService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/courses")
    public Course updateCourse(@RequestBody Course course){
        return courseService.update(course);
    }

    @DeleteMapping("/courses/{id}")
    public String deleteCourseById(@PathVariable int id){
        courseService.deleteById(id);
        return "Deleted...";
    }

    @GetMapping("/getNumberOfCourses")
    public String getNumberOfCourses(){
        int courseNumber = courseService.getNumberOfCourses();
        return "Total course number on DB : " + courseNumber ;
    }

    @GetMapping("/findByAgeGreaterThanAndAgeBefore/{studentID}/{birthDate}")
    public List<Student> findByAgeGreaterThanAndAgeBefore(@PathVariable int studentID, @PathVariable LocalDate birthDate){
        return courseService.findByAgeGreaterThanAndAgeBefore(studentID, birthDate);
    }
}
