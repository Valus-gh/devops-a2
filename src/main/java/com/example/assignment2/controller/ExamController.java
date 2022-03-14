package com.example.assignment2.controller;

import com.example.assignment2.model.Course;
import com.example.assignment2.model.Exam;
import com.example.assignment2.persistence.CourseRepository;
import com.example.assignment2.persistence.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class ExamController {

    @Autowired
    ExamRepository examRepository;

    @GetMapping(value = "/exams")
    public ResponseEntity<List<Exam>> getAllExams(){

        try{

            List<Exam> exams = new ArrayList<>();
            examRepository.findAll().forEach(exams::add);

            if(exams.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(value = "/exams/{name}")
    public ResponseEntity<Exam> getExamByName(@PathVariable("name") String name){

        try{

            Exam exam = examRepository.findByName(name);

            if(exam == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping(value = "/exams")
    public ResponseEntity<Exam> createExam(@RequestBody Exam exam){

        try{

            Exam newExam = examRepository.save(
                    new Exam(exam.getCourse(), new Date(), exam.getName()));

            return new ResponseEntity<>(newExam, HttpStatus.CREATED);

        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
