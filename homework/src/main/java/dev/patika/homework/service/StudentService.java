package dev.patika.homework.service;

import dev.patika.homework.dto.StudentDTO;
import dev.patika.homework.exceptions.StudentAgeNotValidException;
import dev.patika.homework.mappers.StudentMapper;
import dev.patika.homework.model.Student;
import dev.patika.homework.repository.StudentDAO;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService{

    private final StudentDAO studentDAO;
    private final StudentMapper studentMapper;

    public List<StudentDTO> findAll() {
        List<StudentDTO> studentList = new ArrayList<>();
        Iterable<Student> studentIter = studentDAO.findAll();
        for (Student student : studentIter) {
            StudentDTO studentDTO = studentMapper.mapFromStudenttoStudentDTO(student);
            studentList.add(studentDTO);
        }

        return studentList;
    }

    public StudentDTO findById(int id) {
        return studentMapper.mapFromStudenttoStudentDTO(studentDAO.findById(id).get());
    }

    @Transactional
    public Optional<Student> save (StudentDTO studentDTO) {
        calculateAgeFromBirthDate(studentDTO.getStudentBirthDate());

        Student student = studentMapper.mapFromStudentDTOtoStudent(studentDTO);

        return Optional.of(studentDAO.save(student));
    }

    public StudentDTO deleteById(int id) {
//        studentDAO.deleteById(id);
        Student student = studentDAO.findById(id).get();

        StudentDTO studentDTO = studentMapper.mapFromStudenttoStudentDTO(student);
        studentDAO.deleteById(id);
        return studentDTO;
    }

    @Transactional
    public Optional<Student> update(StudentDTO studentDTO, int id) {

        calculateAgeFromBirthDate(studentDTO.getStudentBirthDate());
        Student student = studentMapper.mapFromStudentDTOtoStudent(studentDTO);

        student.setId(id);
        return Optional.of(studentDAO.save(student));
    }

    public void calculateAgeFromBirthDate(Date birthDate) {
        SimpleDateFormat formatNowYear = new SimpleDateFormat("YYYY");

        String birthYear = formatNowYear.format(birthDate);
        int currentYear = LocalDate.now().getYear();
        int age = currentYear - Integer.parseInt(birthYear);
        if (age < 18 || age > 40) {
            throw new StudentAgeNotValidException("Student age is not between 18 and 40!!!");
        }
    }
}
