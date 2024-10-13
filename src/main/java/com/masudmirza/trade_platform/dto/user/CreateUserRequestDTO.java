package com.masudmirza.trade_platform.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateUserRequestDTO {
    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    private String patronymic;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    private String phoneNumber;

    private String role;
}
