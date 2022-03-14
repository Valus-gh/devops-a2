package com.example.assignment2.persistence;

import com.example.assignment2.model.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends CrudRepository<Student, Long> {

    List<Student> findByName(String name);

    List<Student> findBySurname(String surname);

}