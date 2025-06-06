package com.basic.demo.core.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pagination {
  private long totalElements;
  private int totalPage;
  private int page;
  private int limit;
}
