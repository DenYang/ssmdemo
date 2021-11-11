package com.denyang.controller;


import com.denyang.domain.User;

import com.denyang.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping(value = "/adduser.do", method = RequestMethod.POST)
    @ResponseBody
    public String addUser(@RequestBody User user) {
        int nums = 0;
        if (StringUtils.isEmpty(user.getId())){
            user.setId();
            nums = userService.addUser(user);
        }else{
           userService.updateUser(user);
            nums = 1;
        }
        if (nums > 0) {
            return "successes";
        } else
            return "failed";
    }

    @RequestMapping(value = "/finduser.do")
    @ResponseBody
    public List<User> findUser() {
        List<User> userList = userService.findUsers();
        return userList;
    }

    @RequestMapping(value = "/deluser.do")
    @ResponseBody
    public String deleteUser(String id) {
        int nums = userService.deleteUser(id);
        if (nums > 0) {
            return "successes";
        } else
            return "failed";
    }
    @RequestMapping(value = "/findone.do")
    @ResponseBody
    public User findOne(String id) {
        User user =  userService.findOne(id);
        return user;
    }
    @RequestMapping(value = "/findlike.do")
    @ResponseBody
    public List<User> selectNameOrCode(String name,String email){
        List<User> userList = userService.selectName0rEmail(name,email);
        return userList;
    }
}
