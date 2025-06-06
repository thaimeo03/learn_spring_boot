package com.basic.demo.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "BOOKINGS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Booking implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", nullable = false)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "USER_ID", nullable = false)
  @JsonIgnore
  private User user;

  @ManyToOne
  @JoinColumn(name = "ROOM_ID", nullable = false)
  @JsonIgnore
  private Room room;
}
