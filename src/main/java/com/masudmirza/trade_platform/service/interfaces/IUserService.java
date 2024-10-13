package com.masudmirza.trade_platform.service.interfaces;

import com.masudmirza.trade_platform.dto.PaginatedResponseDTO;
import com.masudmirza.trade_platform.dto.user.CreateUserRequestDTO;
import com.masudmirza.trade_platform.dto.user.UpdateUserRequestDTO;
import com.masudmirza.trade_platform.dto.user.UserResponseDTO;

import java.util.UUID;

public interface IUserService {
    UserResponseDTO createUser(CreateUserRequestDTO dto);

    PaginatedResponseDTO<UserResponseDTO> getAllUsers(
            int page,
            int size,
            String sortBy,
            String name,
            String email,
            String surname,
            String phoneNumber);

    UserResponseDTO getUserById(UUID id);

    UserResponseDTO updateProfile(UpdateUserRequestDTO dto);

    void deleteUser(UUID id);
}
