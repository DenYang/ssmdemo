package com.denyang.service;

import com.denyang.domain.User;

import java.util.List;

public interface UserService {
    int addUser(User user);
    List<User> findUsers();
}
