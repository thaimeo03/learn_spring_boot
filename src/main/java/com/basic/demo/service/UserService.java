package com.basic.demo.service;

import com.basic.demo.entity.User;

public interface UserService {
  User findUserByUsername(String username);
}
