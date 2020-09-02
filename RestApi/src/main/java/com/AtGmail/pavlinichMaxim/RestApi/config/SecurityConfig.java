package com.AtGmail.pavlinichMaxim.RestApi.config;

import com.AtGmail.pavlinichMaxim.RestApi.security.jwt.JwtConfigurer;
import com.AtGmail.pavlinichMaxim.RestApi.security.jwt.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    private final String ADMIN_ENDPOINT = "/*";
    private final String STUDENT_ENDPOINT = "/student/**";
    private final String TEACHER_ENDPOINT = "/teacher/*";
    private final String LOGIN_ENDPOINT = "/login";

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(LOGIN_ENDPOINT).permitAll()
                .antMatchers(this.ADMIN_ENDPOINT).hasRole("admin")
                .antMatchers(this.STUDENT_ENDPOINT).hasRole("student")
                .antMatchers(this.TEACHER_ENDPOINT).hasRole("teacher")
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(this.jwtTokenProvider));
    }
}
