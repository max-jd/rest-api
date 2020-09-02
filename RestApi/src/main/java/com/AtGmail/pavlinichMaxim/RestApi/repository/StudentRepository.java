package com.AtGmail.pavlinichMaxim.RestApi.repository;

import com.AtGmail.pavlinichMaxim.RestApi.model.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Integer> {
    Student findStudentByUserNickname(String username);
}
