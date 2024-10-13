package com.masudmirza.trade_platform.service;

import com.masudmirza.trade_platform.dto.auth.LoginRequestDTO;
import com.masudmirza.trade_platform.dto.auth.LoginResponseDTO;
import com.masudmirza.trade_platform.dto.auth.SignupRequestDTO;
import com.masudmirza.trade_platform.dto.auth.SignupResponseDTO;
import com.masudmirza.trade_platform.enums.ErrorCode;
import com.masudmirza.trade_platform.exception.ResourceAlreadyExistsException;
import com.masudmirza.trade_platform.exception.ResourceNotFoundException;
import com.masudmirza.trade_platform.mapper.UserMapper;
import com.masudmirza.trade_platform.models.User;
import com.masudmirza.trade_platform.repository.UserRepository;
import com.masudmirza.trade_platform.security.JwtHelper;
import com.masudmirza.trade_platform.service.interfaces.IAuthService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final JwtHelper jwtHelper;

    @Transactional
    public SignupResponseDTO signup(SignupRequestDTO signupRequestDTO) {
        if (userRepository.findByEmail(signupRequestDTO.getEmail()).isPresent()) {
            throw new ResourceAlreadyExistsException(ErrorCode.USER_ALREADY_EXISTS);
        }

        User user = userMapper.signupRequestToUser(signupRequestDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);

        SignupResponseDTO response = new SignupResponseDTO();
        response.setMessage("Success");
        return response;
    }

    @Transactional
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDTO.getEmail(),
                        loginRequestDTO.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        System.out.println(userDetails);
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException(ErrorCode.USER_NOT_FOUND));


        String token = jwtHelper.generateToken(user);

        LoginResponseDTO response = new LoginResponseDTO();
        response.setToken(token);
        return response;
    }
}
