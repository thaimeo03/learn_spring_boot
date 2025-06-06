package com.basic.demo.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends BaseException {
  public NotFoundException(String msg) {
    super(HttpStatus.NOT_FOUND, msg);
  }
}
