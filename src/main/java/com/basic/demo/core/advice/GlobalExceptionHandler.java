package com.basic.demo.core.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.basic.demo.core.dto.response.BaseResponse;
import com.basic.demo.core.enums.ResponseCode;
import com.basic.demo.core.exception.BaseException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(BaseException.class)
  public ResponseEntity<BaseResponse<?>> handleBaseException(BaseException ex) {
    log.error(String.valueOf(ex.getCode()), ex.getMessage(), ex);
    BaseResponse<?> error = BaseResponse.error(String.valueOf(ex.getCode().value()), ex.getMessage());

    return ResponseEntity.status(ex.getCode()).body(error);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<BaseResponse<?>> handleBaseException(Exception ex) {
    log.error(ResponseCode.ERROR_SERVER.getCode(), ex.getMessage(), ex);
    BaseResponse<?> error = BaseResponse.error(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
        ResponseCode.ERROR_SERVER.getMessage());

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
  }
}
