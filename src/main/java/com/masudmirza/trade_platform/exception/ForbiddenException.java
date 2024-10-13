package com.masudmirza.trade_platform.exception;

import com.masudmirza.trade_platform.enums.ErrorCode;
import lombok.Getter;

@Getter
public class ForbiddenException extends RuntimeException {
    private final String errorCode;

    public ForbiddenException(ErrorCode errorCode) {
        this.errorCode = String.valueOf(errorCode);
    }
}
