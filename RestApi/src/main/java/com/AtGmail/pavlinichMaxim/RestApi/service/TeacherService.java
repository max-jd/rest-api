package com.AtGmail.pavlinichMaxim.RestApi.service;

import com.AtGmail.pavlinichMaxim.RestApi.model.Teacher;

import java.util.List;

public interface TeacherService {
    List<Teacher> getAll();

    Teacher getTeacher(int id);
}
