package com.premiergaming.configuration;

import com.premiergaming.model.Users;
import com.premiergaming.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import reactor.core.publisher.Mono;

import java.util.Optional;

public class UsersUserDetailsService implements UserDetailsService {

    @Autowired
    UsersRepository usersRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> user = usersRepo.findUserByEmail(username);
        return user.map(UsersDetailsInfo ::new).orElseThrow(()-> new UsernameNotFoundException("User does not exist"));
    }
}
