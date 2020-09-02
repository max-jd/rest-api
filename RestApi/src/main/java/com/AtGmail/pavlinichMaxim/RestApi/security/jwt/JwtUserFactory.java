package com.AtGmail.pavlinichMaxim.RestApi.security.jwt;

import com.AtGmail.pavlinichMaxim.RestApi.model.Role;
import com.AtGmail.pavlinichMaxim.RestApi.model.Student;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class JwtUserFactory {
    JwtUserFactory() {
    }

    public static JwtStudent createJwtStudent(Student student) {
        return new JwtStudent(
                student.getId(),
                student.getUser().getNickname(),
                student.getUser().getPassword(),
                student.getUser().getEmail(),
                student.getUser().getCreated(),
                mapToGrantedAuthorities(student.getRole()));
    }

    private static Collection<GrantedAuthority> mapToGrantedAuthorities(Role role) {
        List<GrantedAuthority> authority = new ArrayList<GrantedAuthority>();
        authority.add(new SimpleGrantedAuthority(role.getName()));
        return authority;
    }
}
