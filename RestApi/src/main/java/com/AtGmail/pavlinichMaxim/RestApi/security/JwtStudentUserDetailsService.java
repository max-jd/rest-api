package com.AtGmail.pavlinichMaxim.RestApi.security;

import com.AtGmail.pavlinichMaxim.RestApi.security.jwt.JwtStudent;
import com.AtGmail.pavlinichMaxim.RestApi.security.jwt.JwtUserFactory;
import com.AtGmail.pavlinichMaxim.RestApi.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JwtStudentUserDetailsService implements UserDetailsService {
    @Autowired
    private StudentService studentService;

    @Override
    public UserDetails loadUserByUsername(String studentUserName) throws UsernameNotFoundException {
        JwtStudent jwtStudent = JwtUserFactory.createJwtStudent(studentService.findStudentByUsername(studentUserName));
        return jwtStudent;
    }
}
