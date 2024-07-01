package com.premiergaming.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
@Data
@Getter
@Setter
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "mobile_num")
    private String mobileNum;
    @Column(name="email", unique = true)
    private String email;
    @Column(name="role")
    private String role;
    @Column(name="password")
    private String password;


}