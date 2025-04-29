package com.users.registration.infrastructure.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneRequestDto {

    @NotBlank(message = "El número de teléfono es obligatorio")
    private String number;

    @NotBlank(message = "El código de ciudad es obligatorio")
    private String cityCode;

    @NotBlank(message = "El código de país es obligatorio")
    private String countryCode;
}

