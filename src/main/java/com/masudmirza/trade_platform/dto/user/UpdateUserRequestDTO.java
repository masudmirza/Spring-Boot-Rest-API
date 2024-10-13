package com.masudmirza.trade_platform.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateUserRequestDTO {
    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    private String patronymic;

    @Email
    @NotBlank
    private String email;

    private String phoneNumber;

    private String role;
}
