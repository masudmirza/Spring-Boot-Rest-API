package com.masudmirza.trade_platform.controller;

import com.masudmirza.trade_platform.dto.PaginatedResponseDTO;
import com.masudmirza.trade_platform.dto.user.CreateUserRequestDTO;
import com.masudmirza.trade_platform.dto.user.UpdateUserRequestDTO;
import com.masudmirza.trade_platform.dto.user.UserResponseDTO;
import com.masudmirza.trade_platform.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // Admin: Create User
    @PostMapping
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody CreateUserRequestDTO dto) {
        System.out.println(dto);
        return ResponseEntity.ok(userService.createUser(dto));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    public ResponseEntity<PaginatedResponseDTO<UserResponseDTO>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") Instant sortBy,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String surname,
            @RequestParam(required = false) String phoneNumber) {

        PaginatedResponseDTO<UserResponseDTO> users = userService
                .getAllUsers(page, size, String.valueOf(sortBy), name, email, surname, phoneNumber);
        return ResponseEntity.ok(users);
    }

    // Admin/Manager/User: Get User by ID (Admin and Manager can get any, User can only get own)
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    // Admin/Manager/User: Update User (Admin and Manager can update any, User can update own)
    @PutMapping("/profile")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'MANAGER', 'ADMIN')")
    public ResponseEntity<UserResponseDTO> updateProfile(@RequestBody UpdateUserRequestDTO dto) {
        System.out.println("entered controller");
        return ResponseEntity.ok(userService.updateProfile(dto));
    }

    // Admin: Delete User
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
