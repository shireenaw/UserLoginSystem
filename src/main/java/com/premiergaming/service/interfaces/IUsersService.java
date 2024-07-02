package com.premiergaming.service.interfaces;

import com.premiergaming.model.dto.UsersDTO;
import com.premiergaming.model.entity.Users;

import java.util.List;
import java.util.Optional;

public interface IUsersService {
    UsersDTO create(UsersDTO user);
    List<UsersDTO> getAll();
    UsersDTO getByUserId(Integer userId);
    UsersDTO getUserByEmail(String email);
    void deleteUserByUserId(Integer userId);
}
