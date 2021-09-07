package dev.patika.homework.service;

import dev.patika.homework.model.Student;
import dev.patika.homework.repository.StudentDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService implements BaseService<Student> {

    private final StudentDAO studentDAO;

    public StudentService(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @Override
    public List<Student> findAll() {
        List<Student> studentList = new ArrayList<>();
        Iterable<Student> studentIter = studentDAO.findAll();
        for (Student student : studentIter) {
            studentList.add(student);
        }
        return studentList;
    }

    @Override
    public Student findById(int id) {
        return studentDAO.findById(id).get();
    }

    @Override
    @Transactional
    public Student save(Student student) {
        return studentDAO.save(student);
    }

    @Override
    public void deleteById(int id) {
        studentDAO.deleteById(id);
    }

    @Override
    @Transactional
    public Student update(Student student) {
        return studentDAO.save(student);
    }

    public int calculateAgeFromBirthDate(Date birthDate){
        SimpleDateFormat formatNowYear = new SimpleDateFormat("YYYY");

        String birthYear = formatNowYear.format(birthDate);
        LocalDate localDate = LocalDate.now();
        int year = localDate.getYear();
        return year - Integer.parseInt(birthYear);

    }
}
