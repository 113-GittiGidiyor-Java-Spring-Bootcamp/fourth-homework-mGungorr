package dev.patika.homework.service;

import dev.patika.homework.dto.InstructorDTO;
import dev.patika.homework.dto.StudentDTO;
import dev.patika.homework.exceptions.CourseIsAlreadyExistException;
import dev.patika.homework.exceptions.InstructorIsAlreadyExistException;
import dev.patika.homework.mappers.InstructorMapper;
import dev.patika.homework.model.Instructor;
import dev.patika.homework.model.Student;
import dev.patika.homework.repository.InstructorDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InstructorService {

    private final InstructorDAO instructorDAO;
    private final InstructorMapper instructorMapper;


    public List<InstructorDTO> findAll() {
        List<InstructorDTO> instructorDTOList = new ArrayList<>();
        Iterable<Instructor> instructorIter = instructorDAO.findAll();
        for (Instructor instructor : instructorIter) {
            InstructorDTO instructorDTO = instructorMapper.mapFromInstructortoInstructorDTO(instructor);
            instructorDTOList.add(instructorDTO);
        }

        return instructorDTOList;
    }

    public InstructorDTO findById(int id) {
        return instructorMapper.mapFromInstructortoInstructorDTO(instructorDAO.findById(id).get());
    }

    @Transactional
    public Optional<Instructor> save(InstructorDTO instructorDTO) {
        InstructorExists(instructorDTO.getInstructorPhone());
        Instructor instructor = instructorMapper.mapFromInstructorDTOtoInstructor(instructorDTO);

        return Optional.of(instructorDAO.save(instructor));
    }

    public InstructorDTO deleteById(int id) {
        Instructor instructor = instructorDAO.findById(id).get();

        InstructorDTO instructorDTO = instructorMapper.mapFromInstructortoInstructorDTO(instructor);
        instructorDAO.deleteById(id);
        return instructorDTO;
    }

    @Transactional
    public Optional<Instructor> update(InstructorDTO instructorDTO, int id) {
        InstructorExists(instructorDTO.getInstructorPhone());
        Instructor instructor = instructorMapper.mapFromInstructorDTOtoInstructor(instructorDTO);

        instructor.setId(id);
        return Optional.of(instructorDAO.save(instructor));
    }

    private void InstructorExists(long instructorPhone) {

        if (instructorDAO.findInstructorByInstructorPhone(instructorPhone).isPresent()) {
            throw new InstructorIsAlreadyExistException("Instructor Is Already Exist With Same Phone Number!");
        }
    }
}
