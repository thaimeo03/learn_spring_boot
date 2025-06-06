package com.basic.demo.dto.response;

import java.util.List;

import com.basic.demo.core.dto.response.Pagination;
import com.basic.demo.entity.Room;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoomFindAllResponse {
  private List<Room> rooms;
  private Pagination pagination;
}
