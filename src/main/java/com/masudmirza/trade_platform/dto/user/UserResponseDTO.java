package com.masudmirza.trade_platform.dto.user;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserResponseDTO {
    private UUID id;
    private String name;
    private String surname;
    private String patronymic;
    private String email;
    private String phoneNumber;
    private String role;
}
