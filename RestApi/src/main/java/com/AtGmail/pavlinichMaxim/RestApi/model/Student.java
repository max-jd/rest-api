package com.AtGmail.pavlinichMaxim.RestApi.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "students")
@Data
@EqualsAndHashCode(exclude = "teacher")
@ToString(exclude = "teacher")
public class Student implements Serializable {

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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id_fk")
    private Teacher teacher;
}
