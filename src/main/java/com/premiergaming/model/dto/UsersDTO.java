package com.premiergaming.model.dto;

import com.premiergaming.model.entity.Users;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsersDTO {

    private Integer userId;
    private String firstName;
    private String lastName;
    private String mobileNum;
    private String email;
    private String role;
    private String password;

}