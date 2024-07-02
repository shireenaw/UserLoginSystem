package com.premiergaming.service;

import com.premiergaming.model.entity.Users;
import com.premiergaming.model.UsersDetailsInfo;
import com.premiergaming.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersDetailsService implements UserDetailsService {

    @Autowired
    UsersRepository usersRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> user = usersRepo.findUserByEmail(username);
        return user.map(UsersDetailsInfo::new).orElseThrow(()-> new UsernameNotFoundException("User does not exist"));
    }
}
