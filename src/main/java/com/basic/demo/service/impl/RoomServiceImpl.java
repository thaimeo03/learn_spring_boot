package com.basic.demo.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.basic.demo.core.dto.response.Pagination;
import com.basic.demo.dto.response.RoomFindAllResponse;
import com.basic.demo.entity.Room;
import com.basic.demo.repository.RoomRepository;
import com.basic.demo.service.RoomService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class RoomServiceImpl implements RoomService {
  private final RoomRepository roomRepository;

  @Override
  public RoomFindAllResponse findAll(Pageable pageable) {
    log.info("Finding all rooms with pagination: page={}, size={}", pageable.getPageNumber(), pageable.getPageSize());
    Page<Room> roomPage = roomRepository.findAll(pageable);

    List<Room> data = roomPage.getContent();
    log.info("Found {} rooms", data.size());

    Pagination pagination = new Pagination(
        roomPage.getTotalElements(),
        roomPage.getTotalPages(),
        roomPage.getNumber(),
        roomPage.getSize());

    RoomFindAllResponse metaData = new RoomFindAllResponse(data, pagination);

    return metaData;
  }

}
