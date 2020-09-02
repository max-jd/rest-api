package com.AtGmail.pavlinichMaxim.RestApi.controller;

import com.AtGmail.pavlinichMaxim.RestApi.model.Teacher;
import com.AtGmail.pavlinichMaxim.RestApi.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherRestController {
    @Autowired
    private TeacherService teacherService;

    @GetMapping("/all")
    public List<Teacher> getAllTeachers() {
        return teacherService.getAll();
    }

    @GetMapping("{id}")
    public Teacher getTeacherById(@PathVariable(name = "id") int id) {
        return teacherService.getTeacher(id);
    }
}
