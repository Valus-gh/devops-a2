package com.example.assignment2.controller;

import com.example.assignment2.model.Course;
import com.example.assignment2.model.Student;
import com.example.assignment2.persistence.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CourseController {

    @Autowired
    CourseRepository courseRepository;

    @GetMapping(value = "/courses")
    public ResponseEntity<List<Course>> getAllCourses(){

        try{

            List<Course> courses = new ArrayList<>();
            courseRepository.findAll().forEach(courses::add);

            if(courses.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(value = "/courses/{name}")
    public ResponseEntity<List<Course>> getCourseByName(@PathVariable("name") String name){

        try{

            List<Course> courses = new ArrayList<>(courseRepository.findByName(name));

            if(courses.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping(value = "/courses")
    public ResponseEntity<Course> createCourse(@RequestBody Course course){

        try{

            Course newCourse = courseRepository.save(
                    new Course(course.getStudents(), course.getExams(), course.getName()));

            return new ResponseEntity<>(newCourse, HttpStatus.CREATED);

        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
