package com.example.assignment2.persistence;

import com.example.assignment2.model.Course;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseRepository extends CrudRepository<Course, Long> {

    List<Course> findByName(String name);

    Course findByExams_Id(long id);

    Course findByExams_Name(String name);

}