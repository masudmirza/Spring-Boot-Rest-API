package com.masudmirza.trade_platform.mapper;

import com.masudmirza.trade_platform.dto.auth.SignupRequestDTO;
import com.masudmirza.trade_platform.dto.user.CreateUserRequestDTO;
import com.masudmirza.trade_platform.dto.user.UserResponseDTO;
import com.masudmirza.trade_platform.enums.RoleName;
import com.masudmirza.trade_platform.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User signupRequestToUser(SignupRequestDTO signupRequestDTO) {
        return User.builder()
                .email(signupRequestDTO.getEmail())
                .password(signupRequestDTO.getPassword())
                .role(RoleName.valueOf(signupRequestDTO.getRole()))
                .build();
    }

    public User toEntity(CreateUserRequestDTO dto) {
        return User.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .patronymic(dto.getPatronymic())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .password(dto.getPassword())
                .role(RoleName.valueOf(dto.getRole()))
                .build();
    }

    public UserResponseDTO toDTO(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .patronymic(user.getPatronymic())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole().name())
                .build();
    }
}
