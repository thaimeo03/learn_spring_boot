package com.basic.demo.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import com.basic.demo.entity.Room;

import jakarta.persistence.LockModeType;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
  // Use for Optimistic Locking
  Optional<Room> findOneByIdAndAvailable(Long id, boolean available);

  // Use for Pessimistic Locking
  @Lock(LockModeType.PESSIMISTIC_WRITE)
  Optional<Room> findByIdAndAvailable(Long id, boolean available);

  @Modifying
  @Query("UPDATE Room SET available = false, version = version + 1 WHERE id = :id AND version = :version")
  int updateRoomAsUnavailableOptimistic(@Param(value = "id") Long id, @Param(value = "version") Long version);

  @Modifying
  @Query("UPDATE Room SET available = false WHERE id = :id AND available = true")
  int updateRoomAsUnavailableWhenPessimisticLocked(@Param(value = "id") Long id);

  @NonNull
  Page<Room> findAll(@NonNull Pageable pageable);
}
