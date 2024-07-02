package com.premiergaming.service;

import com.premiergaming.model.dto.UsersDTO;
import com.premiergaming.model.entity.Users;
import com.premiergaming.repository.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class UsersDetailsServiceTest {
    @MockBean
    private UsersRepository usersRepository;
    @InjectMocks
    private UsersDetailsService usersDetailsService;

    private Users user;

    @BeforeEach
    void setUp() {
        user = new Users(1,"Shireen","Test","77077889","shireen.test@gmail.com","USER", "shireen1234");
    }

    @Test
    public void loadUserByUsernameTest()
    {
        Mockito.when(usersRepository.save(any(Users.class))).then(returnsFirstArg());
        Mockito.when(usersRepository.findUserByEmail("shireen.test@gmail.com")).thenReturn(Optional.of(user));
        UserDetails userDetails = usersDetailsService.loadUserByUsername(user.getEmail());
        assertThat(userDetails).isNotNull();
    }
}
