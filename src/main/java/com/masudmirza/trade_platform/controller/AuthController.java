package com.masudmirza.trade_platform.controller;

import com.masudmirza.trade_platform.dto.auth.LoginRequestDTO;
import com.masudmirza.trade_platform.dto.auth.LoginResponseDTO;
import com.masudmirza.trade_platform.dto.auth.SignupRequestDTO;
import com.masudmirza.trade_platform.dto.auth.SignupResponseDTO;
import com.masudmirza.trade_platform.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDTO> signup(@RequestBody SignupRequestDTO signupRequestDTO) {
        SignupResponseDTO response = authService.signup(signupRequestDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        LoginResponseDTO response = authService.login(loginRequestDTO);
        return ResponseEntity.ok(response);
    }
}