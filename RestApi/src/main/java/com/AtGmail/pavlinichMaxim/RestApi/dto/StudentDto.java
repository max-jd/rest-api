package com.AtGmail.pavlinichMaxim.RestApi.dto;

import com.AtGmail.pavlinichMaxim.RestApi.model.Student;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class StudentDto {
    private int id;
    private String nickname;
    private String email;

    public StudentDto fromStudent(Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.id = student.getId();
        studentDto.nickname = student.getUser().getNickname();
        studentDto.email = student.getUser().getEmail();

        return studentDto;
    }

    public Student toStudent() {
        Student student = new Student();

        student.setId(this.id);
        student.getUser().setNickname(this.nickname);
        student.getUser().setEmail(this.email);

        return student;
    }
}
