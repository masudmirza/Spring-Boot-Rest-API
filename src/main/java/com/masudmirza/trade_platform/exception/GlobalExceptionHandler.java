package com.masudmirza.trade_platform.exception;

import com.masudmirza.trade_platform.dto.ErrorResponseDTO;
import com.masudmirza.trade_platform.enums.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotAuthorizedException.class)
    public ResponseEntity<ErrorResponseDTO> notAuthorizedException(NotAuthorizedException ex) {
        ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
                .errorCode(ex.getErrorCode())
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .message(ex.getMessage())
                .timestamp(Instant.now())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ErrorResponseDTO> forbiddenException(ForbiddenException ex) {
        ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
                .errorCode(ex.getErrorCode())
                .statusCode(HttpStatus.FORBIDDEN.value())
                .message(ex.getMessage())
                .timestamp(Instant.now())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleResourceAlreadyExistsException(ResourceAlreadyExistsException ex) {
        ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
                .errorCode(ex.getErrorCode())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .errorCode(ex.getMessage())
                .timestamp(Instant.now())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
                .errorCode(ex.getErrorCode())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .errorCode(ex.getMessage())
                .timestamp(Instant.now())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGenericException(Exception ex) {
        ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
                .errorCode(String.valueOf(ErrorCode.INTERNAL_SERVER_ERROR))
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(ex.getMessage())
                .timestamp(Instant.now())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
