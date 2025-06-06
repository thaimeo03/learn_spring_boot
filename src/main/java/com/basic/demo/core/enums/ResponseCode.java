package com.basic.demo.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResponseCode {
  SUCCESS("000", "Thành công"),
  ERROR_SERVER("999", "Lỗi hệ thống");

  private final String code;
  private final String message;
}
