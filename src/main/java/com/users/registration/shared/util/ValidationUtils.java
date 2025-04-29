package com.users.registration.shared.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class ValidationUtils {

    @Value("${validation.password-regex}")
    private String passwordRegex;

    public boolean isValidPassword(String password) {
        return Pattern.matches(passwordRegex, password);
    }

    public boolean isValidEmail(String email) {
        String emailRegex = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return Pattern.matches(emailRegex, email);
    }
}
