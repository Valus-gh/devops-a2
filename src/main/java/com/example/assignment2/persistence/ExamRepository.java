package com.example.assignment2.persistence;

import com.example.assignment2.model.Exam;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface ExamRepository extends CrudRepository<Exam, Long> {

    List<Exam> findByCourse_Name(String name);

    List<Exam> findByCourse_Id(long id);

    Exam findByName(String name);

    Exam findByDate(Date date);

}