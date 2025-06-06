package com.basic.demo.dto.request;

import lombok.Data;

@Data
public class RoomFindAllRequest {
  private int page = 0;
  private int limit = 10;
}
