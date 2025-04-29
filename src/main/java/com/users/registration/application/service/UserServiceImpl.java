package com.users.registration.application.service;

import com.users.registration.domain.model.User;
import com.users.registration.domain.repository.UserRepository;
import com.users.registration.infrastructure.security.JwtTokenGenerator;
import com.users.registration.shared.exception.EmailAlreadyExistsException;
import com.users.registration.shared.util.ValidationUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtTokenGenerator tokenGenerator;
    private final ValidationUtils validationUtils;

    public UserServiceImpl(UserRepository userRepository, JwtTokenGenerator tokenGenerator, ValidationUtils validationUtils) {
        this.userRepository = userRepository;
        this.tokenGenerator = tokenGenerator;
        this.validationUtils = validationUtils;
    }

    @Override
    public User register(User user) {
        if (!validationUtils.isValidEmail(user.getEmail())) {
            throw new IllegalArgumentException("Formato de correo no válido");
        }

        if (!validationUtils.isValidPassword(user.getPassword())) {
            throw new IllegalArgumentException("Formato de contraseña no válido");
        }

        userRepository.findByEmail(user.getEmail()).ifPresent(u -> {
            throw new EmailAlreadyExistsException("El correo ya esta registrado");
        });

        user.setId(UUID.randomUUID());
        user.setCreated(LocalDateTime.now());
        user.setModified(LocalDateTime.now());
        user.setLastLogin(LocalDateTime.now());
        user.setIsActive(true);
        user.setToken(tokenGenerator.generateToken(user.getEmail()));

        return userRepository.save(user);
    }
}
