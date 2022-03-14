package com.example.assignment2.controller;

import com.example.assignment2.model.Student;
import com.example.assignment2.persistence.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping(value = "/students")
    public ResponseEntity<List<Student>> getAllStudents(){

        try{

            List<Student> students = new ArrayList<>();
            studentRepository.findAll().forEach(students::add);

            if(students.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(value = "/students/{name}")
    public ResponseEntity<List<Student>> getStudentByName(@PathVariable("name") String name){

        try{

            List<Student> students = new ArrayList<>(studentRepository.findByName(name));

            if(students.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping(value = "/students")
    public ResponseEntity<Student> createStudent(@RequestBody Student student){

        try{

            Student newStudent = studentRepository.save(
                    new Student(student.getCourses(), student.getName(), student.getSurname()));

            return new ResponseEntity<>(newStudent, HttpStatus.CREATED);

        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
