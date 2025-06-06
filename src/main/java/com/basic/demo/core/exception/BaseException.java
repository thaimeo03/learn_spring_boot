package com.basic.demo.core.exception;

import org.springframework.http.HttpStatus;

public abstract class BaseException extends RuntimeException {
  protected HttpStatus code;

  public BaseException(HttpStatus code, String msg) {
    super(msg);
    this.code = code;
  }

  public HttpStatus getCode() {
    return code;
  }
}
