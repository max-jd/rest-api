package com.AtGmail.pavlinichMaxim.RestApi.service;

import com.AtGmail.pavlinichMaxim.RestApi.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> findAll();

    Student getStudent(int id);

    Student findStudentByUsername(String username);
}
