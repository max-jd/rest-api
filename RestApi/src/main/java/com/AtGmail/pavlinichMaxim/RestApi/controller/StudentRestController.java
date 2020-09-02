package com.AtGmail.pavlinichMaxim.RestApi.controller;

import com.AtGmail.pavlinichMaxim.RestApi.model.Student;
import com.AtGmail.pavlinichMaxim.RestApi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentRestController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/all")
    public List<Student> getAllStudents() {
        return studentService.findAll();
    }

    @GetMapping("{id}")
    public Student getStudentById(@PathVariable(name = "id") int id) {
        return studentService.getStudent(id);
    }

}
