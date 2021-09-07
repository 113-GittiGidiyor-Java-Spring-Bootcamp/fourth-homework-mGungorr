package dev.patika.homework.service;

import dev.patika.homework.model.Instructor;
import dev.patika.homework.repository.InstructorDAO;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class InstructorService implements BaseService<Instructor> {

    private final InstructorDAO instructorDAO;

    public InstructorService(InstructorDAO instructorDAO) {
        this.instructorDAO = instructorDAO;
    }

    @Override
    public List<Instructor> findAll() {
        List<Instructor> instructorList = new ArrayList<>();
        Iterable<Instructor> instructorIter = instructorDAO.findAll();
        for (Instructor instructor : instructorIter) {
            instructorList.add(instructor);
        }
        return instructorList;
    }

    @Override
    public Instructor findById(int id) {
        return instructorDAO.findById(id).get();
    }

    @Override
    @Transactional
    public Instructor save(Instructor instructor) {
        return instructorDAO.save(instructor);
    }

    @Override
    public void deleteById(int id) {
        instructorDAO.deleteById(id);
    }

    @Override
    @Transactional
    public Instructor update(Instructor instructor) {
        return instructorDAO.save(instructor);
    }
}
