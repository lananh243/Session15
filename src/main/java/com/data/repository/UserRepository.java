package com.data.repository;

import com.data.model.User;

import java.util.List;

public interface UserRepository {
    boolean save(User user);
    List<User> findAll();
}
