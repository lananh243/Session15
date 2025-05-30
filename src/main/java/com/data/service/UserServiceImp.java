package com.data.service;

import com.data.model.User;
import com.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements  UserService {
    @Autowired
    private UserRepository userRepo;

    @Override
    public boolean save(User user) {
        return userRepo.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }
}
