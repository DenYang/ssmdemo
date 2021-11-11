package com.denyang.dao;

import com.denyang.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    int insertUser(User user);
    List<User> selectUsers();
    int delUser(String id);
    User findOne(String id);
    void update(User user);
    List<User> selectNameOrEmail(@Param("name") String name, @Param("email") String email);
}
