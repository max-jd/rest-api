package com.AtGmail.pavlinichMaxim.RestApi.security.jwt;

import com.AtGmail.pavlinichMaxim.RestApi.model.Role;
import com.AtGmail.pavlinichMaxim.RestApi.security.JwtStudentUserDetailsService;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${jwt.token.secret}")
    private String secret;

    @Value("${jwt.token.expired}")
    private long durationOfValidityMsec;

    @Autowired
    JwtStudentUserDetailsService jwtStudentDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @PostConstruct
    protected void init() {
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    public String createToken(String userName, Role role) {
        Claims claims = Jwts.claims().setSubject(userName);
        claims.put("role", role);

        Date currentDate = new Date();
        Date validity = new Date(currentDate.getTime() + durationOfValidityMsec);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(currentDate)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = this.jwtStudentDetailsService.loadUserByUsername(getUserName(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }


    public String getUserName(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }


    public String resolveToken(HttpServletRequest request) {
        String barerToken = request.getHeader("Authorization");
        if (barerToken != null && barerToken.startsWith("Bearer_")) {
            return barerToken.substring(7, barerToken.length());
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);

            if (claims.getBody().getExpiration().before(new Date())) {
                return false;
            }
            return true;
        } catch (JwtException | IllegalArgumentException ex) {
            throw new JwtAuthenticationException("JWT token is expired or invalid.");
        }
    }

    public String getRoleName(Role role) {
        return role.getName();
    }

}
