package com.users.registration.controller;

import com.users.registration.application.service.UserService;
import com.users.registration.domain.model.User;
import com.users.registration.infrastructure.controller.UserController;
import com.users.registration.infrastructure.dto.PhoneRequestDto;
import com.users.registration.infrastructure.dto.UserRequestDto;
import com.users.registration.infrastructure.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserController userController;

    private UserRequestDto userRequestDto;
    private User user;

    @BeforeEach
    void setUp() {
        userRequestDto = new UserRequestDto();
        userRequestDto.setName("Juan Rodriguez");
        userRequestDto.setEmail("juan@rodriguez.org");
        userRequestDto.setPassword("hunter2");

        PhoneRequestDto phone = new PhoneRequestDto();
        phone.setNumber("1234567");
        phone.setCityCode("1");
        phone.setCountryCode("57");
        userRequestDto.setPhones(List.of(phone));

        user = new User();
        user.setId(UUID.randomUUID());
        user.setName("Juan Rodriguez");
        user.setEmail("juan@rodriguez.org");
        user.setPassword("hunter2");
    }

    @Test
    void testRegisterUserSuccessfully() {
        when(userMapper.fromRequestDto(any(UserRequestDto.class))).thenReturn(user);
        when(userService.register(any(User.class))).thenReturn(user);
        ResponseEntity<User> response = userController.register(userRequestDto);
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(user.getEmail(), response.getBody().getEmail());
        assertEquals(user.getName(), response.getBody().getName());
        verify(userMapper, times(1)).fromRequestDto(any(UserRequestDto.class));
        verify(userService, times(1)).register(any(User.class));
    }
}
