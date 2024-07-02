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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    @MockBean
    private UsersRepository usersRepository;
    @InjectMocks
    private UsersService userService;
    private UsersDTO userDTOOne;
    private UsersDTO userDTOTwo;
    private UsersDTO userDTOThree;
    private Users userOne;
    private Users userTwo;
    private Users userThree;

    @BeforeEach
    void setUp() {
        userOne = new Users(1,"Shireen","Test","77077889","shireen.test@gmail.com","USER", "shireen1234");
        userTwo = new Users(2,"Jennie","Mifsud","99078864","jennie.mifsud@gmail.com","USER","jennie1234");
        userThree = new Users(3,"Sarah","Calleja","77089909","sarah.calleja@gmail.com","USER","sarah1234");
        userDTOOne = new UsersDTO(1,"Shireen","Test","77077889","shireen.test@gmail.com","USER", "shireen1234");
//        userDTOTwo = new UsersDTO(2,"Jennie","Mifsud","99078864","jennie.mifsud@gmail.com","USER","jennie1234");
//        userDTOThree = new UsersDTO(3,"Sarah","Calleja","77089909","sarah.calleja@gmail.com","USER","sarah1234");

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
        List<UsersDTO> usersList = userService.getAll();

        assertEquals(3, usersList.size());
    }

    @Test
    public void createTest()
    {
        Mockito.when(usersRepository.save(any(Users.class))).then(returnsFirstArg());
        UsersDTO savedUser = userService.create(userDTOOne);
        assertThat(savedUser).isNotNull();
    }

    @Test
    public void getByUserIdTest()
    {
        Mockito.when(usersRepository.save(any(Users.class))).then(returnsFirstArg());
        userService.create(userDTOOne);
        Mockito.when(usersRepository.findByUserId(1)).thenReturn(userOne);

        UsersDTO user = userService.getByUserId(1);

        assertEquals("Shireen", user.getFirstName());
        assertEquals("Test", user.getLastName());
        assertEquals("shireen.test@gmail.com",user.getEmail());
        assertEquals("77077889", user.getMobileNum());
    }

    @Test
    public void findUserByEmailTest()
    {
        Mockito.when(usersRepository.save(any(Users.class))).then(returnsFirstArg());
        UsersDTO savedUser = userService.create(userDTOOne);
        Mockito.when(usersRepository.getUserByEmail("shireen.test@gmail.com")).thenReturn(userOne);
        UsersDTO user = userService.getUserByEmail(savedUser.getEmail());

        assertEquals("Shireen", user.getFirstName());
        assertEquals("Test", user.getLastName());
        assertEquals("shireen.test@gmail.com",user.getEmail());
        assertEquals("77077889", user.getMobileNum());
    }

    @Test
    public void deleteUserTest()
    {
        Mockito.when(usersRepository.save(any(Users.class))).then(returnsFirstArg());
        userService.create(userDTOOne);
        Mockito.when(usersRepository.findByUserId(1)).thenReturn(userOne).thenReturn(userOne);

        // when
        userService.deleteUserByUserId(userOne.getUserId());
        // then
        Mockito.verify(usersRepository, Mockito.times(1)).delete(userOne);

        Mockito.when(usersRepository.findByUserId(1)).thenReturn(null);

        UsersDTO user = userService.getByUserId(1);

        assertThat(user).isNull();


    }

}
