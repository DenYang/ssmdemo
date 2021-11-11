package com.denyang.service.impl;

import com.denyang.dao.UserDao;
import com.denyang.domain.User;
import com.denyang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
    @Override
    public int addUser(User user) {
       int nums = userDao.insertUser(user);
       return nums;
    }

    @Override
    public List<User> findUsers() {
        List<User> userList = userDao.selectUsers();
        return userList;
    }

    @Override
    public int deleteUser(String id) {
        int nums = userDao.delUser(id);
        return nums;
    }

    @Override
    public User findOne(String id) {
        User user = userDao.findOne(id);
        return user;
    }

    @Override
    public void updateUser(User user) {
        userDao.update(user);
    }

    @Override
    public List<User> selectName0rEmail(String name, String email) {
       List<User> userList = userDao.selectNameOrEmail(name,email);
        return userList;
    }
}
