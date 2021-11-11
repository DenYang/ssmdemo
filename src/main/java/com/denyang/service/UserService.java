package com.denyang.service;

import com.denyang.domain.User;

import java.util.List;

public interface UserService {
    int addUser(User user);
    List<User> findUsers();
    int deleteUser(String id);
    User findOne(String id);
    void updateUser(User user);
    List<User> selectName0rEmail(String name,String email);
}
