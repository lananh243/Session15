package com.data.service;

import com.data.model.User;

import java.util.List;

public interface UserService {
    boolean save(User user);
    List<User> findAll();
}
