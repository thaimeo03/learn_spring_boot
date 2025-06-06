package com.basic.demo.service;

import org.springframework.data.domain.Pageable;

import com.basic.demo.dto.response.RoomFindAllResponse;

public interface RoomService {
  RoomFindAllResponse findAll(Pageable pageable);
}
