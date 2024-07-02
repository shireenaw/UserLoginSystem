package com.premiergaming.model.mapper;

import com.premiergaming.model.dto.UsersDTO;
import com.premiergaming.model.entity.Users;

public class UsersMapper {
    public static UsersDTO mapToUsersDto(Users user){
        return new UsersDTO(
                user.getUserId(),
                user.getFirstName(),
                user.getLastName(),
                user.getMobileNum(),
                user.getEmail(),
                user.getRole(),
                user.getPassword()
        );
    }
    public static Users mapToUsers(UsersDTO usersDTO){
        return new Users(
                usersDTO.getUserId(),
                usersDTO.getFirstName(),
                usersDTO.getLastName(),
                usersDTO.getMobileNum(),
                usersDTO.getEmail(),
                usersDTO.getRole(),
                usersDTO.getPassword()
        );
    }
}
