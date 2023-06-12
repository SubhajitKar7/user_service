package com.user.demo.service;

import com.user.demo.model.User;
import com.user.demo.repository.UserRepository;
import lombok.ToString;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.input.LineSeparatorDetector;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void init()
    {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userService).build();
    }

    @Test
    public void addUserTest1()
    {
        User user=User.builder()
                .loginId("subha1")
                .firstName("a")
                .lastName("a")
                .contact(12563)
                .password("aa")
                .email("cv@gmail.com")
                .question("q")
                .role("customer")
                .answer("amj")
                .build();

        when(userRepository.saveAndFlush(any())).thenReturn(user);

        assertEquals(userService.addUser(user),user);
    }


    @Test
    public void addUserTest2()
    {
        User user=User.builder()
                .loginId("subha1")
                .firstName("a")
                .lastName("a")
                .contact(12563)
                .password("aa")
                .email("cv@gmail.com")
                .question("q")
                .role("customer")
                .answer("amj")
                .build();

        when(userRepository.saveAndFlush(any())).thenReturn(null);

        assertEquals(userService.addUser(user),null);
    }


    @Test
    public void loginUser1()
    {
        User user=User.builder()
                .loginId("subha1")
                .firstName("a")
                .lastName("a")
                .contact(12563)
                .password("aa")
                .email("cv@gmail.com")
                .question("q")
                .role("customer")
                .answer("amj")
                .build();

        when(userRepository.validateUser(any(),any())).thenReturn(user);

        assertEquals(userService.loginUser("subha1","password"),true);
    }

    @Test
    public void loginUser2()
    {


        when(userRepository.validateUser(any(),any())).thenReturn(null);

        assertEquals(userService.loginUser("subha1","password"),false);
    }


    @Test
    public  void getAllUSerTest1()
    {
        User user=User.builder()
                .loginId("subha1")
                .firstName("a")
                .lastName("a")
                .contact(12563)
                .password("aa")
                .email("cv@gmail.com")
                .question("q")
                .role("customer")
                .answer("amj")
                .build();
        List<User> userList=new ArrayList<>();
        userList.add(user);

        when(userRepository.findAll()).thenReturn(userList);

        assertEquals(userService.getAllUsers(),userList);
    }


    @Test
    public  void getAllUSerTest2()
    {


        when(userRepository.findAll()).thenReturn(null);

        assertEquals(userService.getAllUsers(),null);
    }

    @Test
    public  void getUserById1()
    {
        User user=User.builder()
                .loginId("subha1")
                .firstName("a")
                .lastName("a")
                .contact(12563)
                .password("aa")
                .email("cv@gmail.com")
                .question("q")
                .role("customer")
                .answer("amj")
                .build();

        when(userRepository.findById("subha1")).thenReturn(Optional.ofNullable(user));

        assertEquals(userService.getUserById("subha1"),user);
    }



    @Test
    public  void getUserById2()
    {

        when(userRepository.findById("subha1")).thenReturn(null);

        assertEquals(userService.getUserById("subha1"),null);
    }


    @Test
    public void updatePassword1()
    {
        User user=User.builder()
                .loginId("subha1")
                .firstName("a")
                .lastName("a")
                .contact(12563)
                .password("aa")
                .email("cv@gmail.com")
                .question("q")
                .role("customer")
                .answer("amj")
                .build();

        when(userRepository.findById("subha1")).thenReturn(Optional.ofNullable(user));
        when(userRepository.saveAndFlush(any())).thenReturn(user);

        assertEquals(userService.updatePassword(user,"subha1"),user);

    }




}