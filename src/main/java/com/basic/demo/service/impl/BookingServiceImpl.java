package com.basic.demo.service.impl;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.basic.demo.entity.Booking;
import com.basic.demo.entity.Room;
import com.basic.demo.entity.User;
import com.basic.demo.repository.BookingRepository;
import com.basic.demo.repository.RoomRepository;
import com.basic.demo.repository.UserRepository;
import com.basic.demo.service.BookingService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookingServiceImpl implements BookingService {
  private final UserRepository userRepository;
  private final BookingRepository bookingRepository;
  private final RoomRepository roomRepository;

  @Transactional
  @Override
  public Booking bookOptimistic(Long userId, Long roomId) {
    // Find user
    Optional<User> userOpt = userRepository.findById(userId);
    if (userOpt.isEmpty()) {
      throw new RuntimeException("Cannot find user");
    }
    log.info("userId: {} start booking", userId);

    // Find room
    Optional<Room> roomOpt = roomRepository.findOneByIdAndAvailable(roomId, true);
    if (roomOpt.isEmpty()) {
      throw new RuntimeException("Cannot find room or room is already booked");
    }
    log.info("userId: {} selected the roomId: {}, start saving data", userId, roomId);

    User user = userOpt.get();
    Room room = roomOpt.get();

    // Create a new booking
    Booking booking = Booking.builder().user(user).room(room).build();
    bookingRepository.save(booking);
    log.info("userId: {} have a booking id={}", userId, booking.getId());

    // Delay transaction
    log.info("start delay transaction");
    try {
      TimeUnit.SECONDS.sleep(3);
    } catch (InterruptedException e) {
      log.error("Error during transaction delay", e);
    }
    log.info("end delay transaction");

    // Update room as unavailable
    // This will use Optimistic Locking, so it will check the version of the room
    int updatedRows = roomRepository.updateRoomAsUnavailableOptimistic(roomId, room.getVersion());
    if (updatedRows == 0) {
      log.error("userId {} failed to book room, rollback", userId);
      throw new RuntimeException(String.format("user=%d cannot update room, need rollback", userId));
    }

    log.info("userId {} successfully booked room {}", userId, roomId);

    return booking;
  }

  @Transactional
  @Override
  public Booking bookPessimistic(Long userId, Long roomId) {
    // Find user
    Optional<User> userOpt = userRepository.findById(userId);
    if (userOpt.isEmpty()) {
      throw new RuntimeException("Cannot find user");
    }
    log.info("userId: {} start booking", userId);

    // Find room
    Optional<Room> roomOpt = roomRepository.findByIdAndAvailable(roomId, true);
    if (roomOpt.isEmpty()) {
      throw new RuntimeException("Cannot find room or room is already booked");
    }
    log.info("userId: {} selected the roomId: {}, start saving data", userId, roomId);

    User user = userOpt.get();
    Room room = roomOpt.get();

    // Create a new booking
    Booking booking = Booking.builder().user(user).room(room).build();
    bookingRepository.save(booking);
    log.info("userId: {} have a booking id={}", userId, booking.getId());

    // Delay transaction
    log.info("start delay transaction");
    try {
      TimeUnit.SECONDS.sleep(3);
    } catch (InterruptedException e) {
      log.error("Error during transaction delay", e);
    }
    log.info("end delay transaction");

    // Update room as unavailable
    int updatedRows = roomRepository.updateRoomAsUnavailableWhenPessimisticLocked(roomId);
    log.info("userId:{}, updatedRows: {}", userId, updatedRows);

    if (updatedRows == 0) {
      log.error("userId {} failed to book room, rollback", userId);
      throw new RuntimeException(String.format("user=%d cannot update room, need rollback", userId));
    }

    log.info("userId {} successfully booked room {}", userId, roomId);

    return booking;
  }
}
