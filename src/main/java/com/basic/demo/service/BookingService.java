package com.basic.demo.service;

import com.basic.demo.entity.Booking;

public interface BookingService {
  Booking bookOptimistic(Long userId, Long roomId);

  Booking bookPessimistic(Long userId, Long roomId);
}
