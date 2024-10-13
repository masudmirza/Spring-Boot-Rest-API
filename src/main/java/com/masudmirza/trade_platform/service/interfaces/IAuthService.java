package com.masudmirza.trade_platform.service.interfaces;

import com.masudmirza.trade_platform.dto.auth.LoginRequestDTO;
import com.masudmirza.trade_platform.dto.auth.LoginResponseDTO;
import com.masudmirza.trade_platform.dto.auth.SignupRequestDTO;
import com.masudmirza.trade_platform.dto.auth.SignupResponseDTO;

public interface IAuthService {
    SignupResponseDTO signup(SignupRequestDTO signupRequestDTO);

    LoginResponseDTO login(LoginRequestDTO loginRequestDTO);
}
