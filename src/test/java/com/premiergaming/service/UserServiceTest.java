package com.premiergaming.service;

import com.premiergaming.model.Users;
import com.premiergaming.repository.UsersRepository;
import com.premiergaming.service.UsersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    @MockBean
    private UsersRepository usersRepository;
    @InjectMocks
    private UsersService userService;
    private Users userOne;
    private Users userTwo;
    private Users userThree;

    @BeforeEach
    void setUp() {
        userOne = new Users();
        userOne.setUserId(1);
        userOne.setFirstName("Shireen");
        userOne.setLastName("Test");
        userOne.setMobileNum("77077889");
        userOne.setEmail("shireen.test@gmail.com");
        userOne.setRole("USER");
        userTwo = new Users();
        userTwo.setUserId(2);
        userTwo.setFirstName("Jennie");
        userTwo.setLastName("Mifsud");
        userTwo.setMobileNum("99078864");
        userTwo.setEmail("jennie.mifsud@gmail.com");
        userTwo.setRole("USER");
        userThree = new Users();
        userThree.setUserId(3);
        userThree.setFirstName("Sarah");
        userThree.setLastName("Calleja");
        userThree.setMobileNum("77089909");
        userThree.setEmail("sarah.calleja@gmail.com");
        userThree.setRole("USER");
    }

    @Test
    public void getAllTest()
    {
        List<Users> list = new ArrayList<Users>();
        list.add(userOne);
        list.add(userTwo);
        list.add(userThree);

        Mockito.when(usersRepository.findAll()).thenReturn(list);
        //test
        List<Users> usersList = userService.getAll();

        assertEquals(3, usersList.size());
    }

    @Test
    public void createTest()
    {
        userService.create(userOne);
        Mockito.verify(usersRepository, Mockito.times(1)).save(userOne);
    }

    @Test
    public void getByUserIdTest()
    {
        userService.create(userOne);
        Mockito.when(usersRepository.findByUserId(1)).thenReturn(userOne);

        Users user = userService.getByUserId(1);

        assertEquals("Shireen", user.getFirstName());
        assertEquals("Test", user.getLastName());
        assertEquals("shireen.test@gmail.com",user.getEmail());
        assertEquals("77077889", user.getMobileNum());
    }

    @Test
    public void findUserByMobileNumTest()
    {
        userService.create(userOne);
        Mockito.when(usersRepository.getUserByEmail("shireen.test@gmail.com")).thenReturn(userOne);

        Users user = userService.getUserByEmail("shireen.test@gmail.com");

        assertEquals("Shireen", user.getFirstName());
        assertEquals("Test", user.getLastName());
        assertEquals("shireen.test@gmail.com",user.getEmail());
        assertEquals("77077889", user.getMobileNum());
    }


//    @Test
//    public void updateTest()
//    {
//        userService.create(userOne);
//        List<Users> usersList = new ArrayList<>();
//        usersList.add(userOne);
//
//        String initialLastName = userOne.getLastName();
//
//        userOne.setLastName("Loh");
//        userService.update(userOne);
//
//        Mockito.verify(usersRepository, Mockito.times(1)).save(userOne);
//        Mockito.when(usersRepository.findByUserId(userOne.getUserId())).thenReturn(new Users());
//
//        assertNotEquals(initialLastName,userOne.getLastName());
//    }

    @Test
    public void deleteUserTest()
    {
        userService.create(userOne);
        Mockito.verify(usersRepository, Mockito.times(1)).save(userOne);
        Mockito.when(usersRepository.findByUserId(1))
                .thenReturn(userOne).thenReturn(null);

        // when
        userService.deleteUserByUserId(userOne.getUserId());

        // then
        Mockito.verify(usersRepository, Mockito.times(1))
                .delete(userOne);

    }

}
