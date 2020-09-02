package com.AtGmail.pavlinichMaxim.RestApi.service.impl;

import com.AtGmail.pavlinichMaxim.RestApi.model.Teacher;
import com.AtGmail.pavlinichMaxim.RestApi.repository.TeacherRepository;
import com.AtGmail.pavlinichMaxim.RestApi.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public List<Teacher> getAll() {
        List<Teacher> listTeacher = new ArrayList<>();
        teacherRepository.findAll().forEach(listTeacher::add);
        log.info("int foundAll - {} teachers found" + listTeacher.size());
        return listTeacher;
    }

    @Override
    public Teacher getTeacher(int id) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(id);
        if (optionalTeacher.isPresent()) {
            log.info("In getTeacher - {} teacher" + optionalTeacher.get());
            return optionalTeacher.get();
        } else {
            throw new ObjectNotFoundException(id, "Teacher is not found");
        }
    }
}
