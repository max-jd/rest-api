package com.AtGmail.pavlinichMaxim.RestApi.controller;

import com.AtGmail.pavlinichMaxim.RestApi.dto.AuthenticationRequestDto;
import com.AtGmail.pavlinichMaxim.RestApi.dto.AuthenticationResponseDto;
import com.AtGmail.pavlinichMaxim.RestApi.model.Student;
import com.AtGmail.pavlinichMaxim.RestApi.security.jwt.JwtTokenProvider;
import com.AtGmail.pavlinichMaxim.RestApi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    BCryptPasswordEncoder bc;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDto> login(@RequestBody AuthenticationRequestDto authenticationRequest) {

        System.out.println("Pass: " + bc.encode(authenticationRequest.getPassword()));

        try {
            String username = authenticationRequest.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, authenticationRequest.getPassword()));
            Student student = studentService.findStudentByUsername(username);

            if (student == null) {
                throw new UsernameNotFoundException("Student was not found by username " + username);
            }

            String token = jwtTokenProvider.createToken(student.getUser().getNickname(), student.getRole());

            AuthenticationResponseDto response = new AuthenticationResponseDto(student.getUser().getNickname(), token);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException ex) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
