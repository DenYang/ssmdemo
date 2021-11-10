package com.denyang.dao;

import com.denyang.domain.User;

import java.util.List;

public interface UserDao {
    int insertUser(User user);
    List<User> selectUsers();
}
