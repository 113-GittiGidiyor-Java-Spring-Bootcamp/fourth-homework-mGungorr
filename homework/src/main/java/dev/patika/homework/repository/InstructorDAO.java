package dev.patika.homework.repository;

import dev.patika.homework.model.Instructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorDAO extends CrudRepository<Instructor,Integer> {
}
