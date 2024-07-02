package com.premiergaming.service;

import com.premiergaming.model.dto.UsersDTO;
import com.premiergaming.model.entity.Users;
import com.premiergaming.model.mapper.UsersMapper;
import com.premiergaming.repository.UsersRepository;
import com.premiergaming.service.interfaces.IUsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class UsersService implements IUsersService {
    @Autowired
    private UsersRepository userRepo;
    @Override
    public UsersDTO create(UsersDTO userDto) {
        Users user = UsersMapper.mapToUsers(userDto);
        Users savedUser = userRepo.save(user);
        return UsersMapper.mapToUsersDto(savedUser);
    }

    @Override
    public List<UsersDTO> getAll() {
        List<Users> users = userRepo.findAll();
        return users.stream().map((user) -> UsersMapper.mapToUsersDto(user))
                .collect(Collectors.toList());
    }
    @Override
    public UsersDTO getUserByEmail(String email) {
        Users user = userRepo.getUserByEmail(email);
        if (user != null){
            return UsersMapper.mapToUsersDto(user);
        }
        return null;
    }
    @Override
    public void deleteUserByUserId(Integer userId){
        Users user = userRepo.findByUserId(userId);
        if (user != null) {
            userRepo.delete(user);
        }
    }

    public UsersDTO getByUserId(Integer userId){
        Users user = userRepo.findByUserId(userId);
        if (user != null){
            return UsersMapper.mapToUsersDto(user);
        }
        return null;
    }

}