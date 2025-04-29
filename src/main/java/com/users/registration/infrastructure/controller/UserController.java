package com.users.registration.infrastructure.controller;

import com.users.registration.application.service.UserService;
import com.users.registration.domain.model.User;
import com.users.registration.infrastructure.dto.UserRequestDto;
import com.users.registration.infrastructure.mapper.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Operation(
            summary = "Registro de usuario",
            description = "Registra un nuevo usuario en el sistema."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Usuario registrado exitosamente",
            content = @Content(schema = @Schema(implementation = User.class))
    )
    @ApiResponse(
            responseCode = "400",
            description = "Error de validación o email ya registrado"
    )
    @PostMapping
    public ResponseEntity<User> register(@Valid @RequestBody UserRequestDto userRequest) {
        User user = userMapper.fromRequestDto(userRequest);
        User registeredUser = userService.register(user);
        return ResponseEntity.ok(registeredUser);
    }

}
