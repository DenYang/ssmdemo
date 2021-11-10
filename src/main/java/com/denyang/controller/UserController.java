package com.denyang.controller;


import com.denyang.domain.User;

import com.denyang.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Resource
    private UserService userService;
    @RequestMapping(value = "/adduser.do",method = RequestMethod.POST)
    @ResponseBody
    public String addUser(@RequestBody  User user) {

        int nums = userService.addUser(user);
        if (nums>0){
           return "successes";
        }
        else
            return "failed";
    }

    @RequestMapping(value = "/finduser.do")
    @ResponseBody

    public List<User> findUser() {
        List<User> userList = userService.findUsers();
        return userList;
    }
}
