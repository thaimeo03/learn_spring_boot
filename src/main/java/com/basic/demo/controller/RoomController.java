package com.basic.demo.controller;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.basic.demo.core.dto.response.BaseResponse;
import com.basic.demo.dto.request.RoomFindAllRequest;
import com.basic.demo.dto.response.RoomFindAllResponse;
import com.basic.demo.entity.Room;
import com.basic.demo.service.RoomService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/rooms")
@RestController
public class RoomController {
  private final RoomService roomService;

  @GetMapping("")
  public ResponseEntity<BaseResponse<List<Room>>> findAllRooms(@ModelAttribute RoomFindAllRequest findAllRequest) {
    int page = findAllRequest.getPage(),
        limit = findAllRequest.getLimit();

    Pageable pageable = PageRequest.of(page, limit);

    RoomFindAllResponse metaData = roomService.findAll(pageable);
    BaseResponse<List<Room>> res = BaseResponse.success(metaData.getRooms(), metaData.getPagination());

    return ResponseEntity.status(HttpStatus.OK).body(res);
  }

}
