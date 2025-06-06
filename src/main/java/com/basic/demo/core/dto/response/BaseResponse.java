package com.basic.demo.core.dto.response;

import java.time.ZonedDateTime;
import java.util.UUID;

import com.basic.demo.core.enums.ResponseCode;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse<T> {
  private String code;
  private String message;
  private String traceId;
  private ZonedDateTime responseTime;
  private T data;
  private Pagination pagination;

  public static <T> BaseResponse<T> success(T data) {
    return BaseResponse.<T>builder()
        .code(ResponseCode.SUCCESS.getCode())
        .message(ResponseCode.SUCCESS.getMessage())
        .traceId(generateTraceId())
        .responseTime(ZonedDateTime.now())
        .data(data)
        .build();
  }

  public static <T> BaseResponse<T> success(T data, Pagination pagination) {
    return BaseResponse.<T>builder()
        .code(ResponseCode.SUCCESS.getCode())
        .message(ResponseCode.SUCCESS.getMessage())
        .traceId(generateTraceId())
        .responseTime(ZonedDateTime.now())
        .data(data)
        .pagination(pagination)
        .build();
  }

  public static <T> BaseResponse<T> error(String code, String message) {
    return BaseResponse.<T>builder()
        .code(code)
        .message(message)
        .traceId(generateTraceId())
        .responseTime(ZonedDateTime.now())
        .build();
  }

  private static String generateTraceId() {
    return System.currentTimeMillis() + "-" + UUID.randomUUID();
  }
}
