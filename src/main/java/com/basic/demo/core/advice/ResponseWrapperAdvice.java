package com.basic.demo.core.advice;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.basic.demo.core.dto.response.BaseResponse;

@ControllerAdvice
public class ResponseWrapperAdvice implements ResponseBodyAdvice<Object> {
  @Override
  @Nullable
  public Object beforeBodyWrite(@Nullable Object body, @NonNull MethodParameter returnType,
      @NonNull MediaType selectedContentType,
      @NonNull Class<? extends HttpMessageConverter<?>> selectedConverterType, @NonNull ServerHttpRequest request,
      @NonNull ServerHttpResponse response) {
    if (body instanceof BaseResponse) {
      return body;
    }

    return BaseResponse.success(body);
  }

  @Override
  public boolean supports(@NonNull MethodParameter returnType,
      @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
    return true;
  }
}
