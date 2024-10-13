package com.masudmirza.trade_platform.exception;

import com.masudmirza.trade_platform.enums.ErrorCode;
import lombok.Getter;

@Getter
public class NotAuthorizedException extends RuntimeException {
    private final String errorCode;

    public NotAuthorizedException(ErrorCode errorCode) {
        this.errorCode = String.valueOf(errorCode);
    }
}
