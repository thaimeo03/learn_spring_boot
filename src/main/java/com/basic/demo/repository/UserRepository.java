package com.basic.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.basic.demo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  User findUserByUsername(String username);
}