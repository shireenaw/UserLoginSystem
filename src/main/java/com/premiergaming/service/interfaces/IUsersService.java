package com.premiergaming.service.interfaces;

import com.premiergaming.model.Users;

import java.util.List;
import java.util.Optional;

public interface IUsersService {
    Users create(Users user);
    List<Users> getAll();
    Users getByUserId(Integer userId);
    Optional<Users> findUserByEmail(String email);
    Users getUserByEmail(String email);
    void deleteUserByUserId(Integer userId);
}
