package com.AtGmail.pavlinichMaxim.RestApi.service.impl;

import com.AtGmail.pavlinichMaxim.RestApi.model.Student;
import com.AtGmail.pavlinichMaxim.RestApi.repository.StudentRepository;
import com.AtGmail.pavlinichMaxim.RestApi.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class StudentServiceIml implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<Student> findAll() {
        List<Student> listStudents = new ArrayList<>();
        studentRepository.findAll().forEach(listStudents::add);
        log.info("In getAll -: {} students found", listStudents.size());
        return listStudents;
    }

    @Override
    public Student getStudent(int id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            log.info("In getStudent - student: {}", optionalStudent.get());
            return optionalStudent.get();
        } else {
            throw new ObjectNotFoundException(id, "Student is not found");
        }
    }

    @Override
    public Student findStudentByUsername(String username) {
        Student student = studentRepository.findStudentByUserNickname(username);
        if (student == null) {
            throw new UsernameNotFoundException("Username " + username + " is not found.");
        }

        log.info("Student: {} is found", username);
        return student;
    }
}
