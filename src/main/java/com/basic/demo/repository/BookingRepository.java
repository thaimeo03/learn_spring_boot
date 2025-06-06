package com.basic.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.basic.demo.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

}
