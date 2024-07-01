package com.premiergaming.service;

import com.premiergaming.model.Users;
import com.premiergaming.repository.UsersRepository;
import com.premiergaming.service.interfaces.IUsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class UsersService implements IUsersService {
    @Autowired
    private UsersRepository userRepo;

    @Override
    public Users create(Users user) {
        return userRepo.save(user);
    }

    @Override
    public List<Users> getAll() {
        return userRepo.findAll();
    }

    @Override
    public Optional<Users> findUserByEmail(String email) {
        return userRepo.findUserByEmail(email);
    }
    @Override
    public Users getUserByEmail(String email) {
        return userRepo.getUserByEmail(email);
    }
    @Override
    public void deleteUserByUserId(Integer userId){
        Users user = userRepo.findByUserId(userId);
        if (user != null) {
            userRepo.delete(user);
        }
    }

    public Users getByUserId(Integer userId){
        return userRepo.findByUserId(userId);
    }

    public UsersRepository getUserRepo() {
        return userRepo;
    }
}