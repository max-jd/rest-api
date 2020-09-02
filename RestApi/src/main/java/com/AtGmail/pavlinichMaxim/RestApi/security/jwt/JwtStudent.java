package com.AtGmail.pavlinichMaxim.RestApi.security.jwt;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;


public class JwtStudent implements UserDetails {
    private int id;
    private String username;
    private String password;
    private String email;
    private Date created;
    private Collection<GrantedAuthority> gratedAuthorities;

    public JwtStudent(int id, String username, String password, String email, Date created, Collection<GrantedAuthority> gratedAuthorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.created = created;
        this.gratedAuthorities = gratedAuthorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return gratedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
