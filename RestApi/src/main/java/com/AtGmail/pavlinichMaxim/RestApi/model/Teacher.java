package com.AtGmail.pavlinichMaxim.RestApi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "teachers")
@Data
@EqualsAndHashCode(exclude = "students")
@ToString(exclude = "students")
public class Teacher implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id_fk")
    private Role role;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id_fk")
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY)
    private Set<Student> students = new HashSet<>();
}
