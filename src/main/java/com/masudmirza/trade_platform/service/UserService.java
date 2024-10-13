package com.masudmirza.trade_platform.service;

import com.masudmirza.trade_platform.dto.PaginatedResponseDTO;
import com.masudmirza.trade_platform.dto.user.CreateUserRequestDTO;
import com.masudmirza.trade_platform.dto.user.UpdateUserRequestDTO;
import com.masudmirza.trade_platform.dto.user.UserResponseDTO;
import com.masudmirza.trade_platform.enums.ErrorCode;
import com.masudmirza.trade_platform.exception.ResourceNotFoundException;
import com.masudmirza.trade_platform.mapper.UserMapper;
import com.masudmirza.trade_platform.models.User;
import com.masudmirza.trade_platform.repository.UserRepository;
import com.masudmirza.trade_platform.service.interfaces.IUserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Transactional
    public UserResponseDTO createUser(CreateUserRequestDTO dto) {
        User user = userMapper.toEntity(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        return userMapper.toDTO(savedUser);
    }

    public PaginatedResponseDTO<UserResponseDTO> getAllUsers(
            int page,
            int size,
            String sortBy,
            String name,
            String email,
            String surname,
            String phoneNumber) {

        String nameParam = Optional.ofNullable(name).map(String::toLowerCase).orElse(null);
        String surnameParam = Optional.ofNullable(surname).map(String::toLowerCase).orElse(null);
        String emailParam = Optional.ofNullable(email).map(String::toLowerCase).orElse(null);
        String phoneNumberParam = Optional.ofNullable(phoneNumber).map(String::toLowerCase).orElse(null);

        Pageable pageable = PageRequest.of(page, size);

        Page<User> userPage = userRepository.findAllUsers(
                nameParam, emailParam, surnameParam, phoneNumberParam, pageable);

        long totalUsers = userRepository.countAllUsers(
                nameParam,
                emailParam,
                surnameParam,
                phoneNumberParam);

        int totalPages = (int) Math.ceil((double) totalUsers / size);

        List<UserResponseDTO> userResponses = userPage.stream()
                .map(userMapper::toDTO)
                .toList();

        return new PaginatedResponseDTO<>(userResponses, totalUsers, totalPages, page, size);
    }

    public UserResponseDTO getUserById(UUID id) {
        User user = userRepository.findNonDeletedById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorCode.USER_NOT_FOUND));
        return userMapper.toDTO(user);
    }

    @Transactional
    public UserResponseDTO updateProfile(UpdateUserRequestDTO dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorCode.USER_NOT_FOUND));

        user.setName(dto.getName() != null ? dto.getName() : user.getName());
        user.setSurname(dto.getSurname() != null ? dto.getSurname() : user.getSurname());
        user.setPatronymic(dto.getPatronymic() != null ? dto.getPatronymic() : user.getPatronymic());
        user.setPhoneNumber(dto.getPhoneNumber() != null ? dto.getPhoneNumber() : user.getPhoneNumber());

        User updatedUser = userRepository.save(user);
        return userMapper.toDTO(updatedUser);
    }

    @Transactional
    public void deleteUser(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorCode.USER_NOT_FOUND));

        user.setDeletedAt(Instant.now());
        userRepository.save(user);
    }
}
