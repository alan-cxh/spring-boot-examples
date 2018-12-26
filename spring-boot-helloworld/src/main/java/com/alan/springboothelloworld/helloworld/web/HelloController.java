package com.alan.springboothelloworld.helloworld.web;

import com.alan.springboothelloworld.helloworld.dao.UserDao;
import com.alan.springboothelloworld.helloworld.entity.User;
import com.alan.springboothelloworld.helloworld.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;

    @RequestMapping("test")
    public String test(){
        return "springboot基础技术";
    }

    @RequestMapping("saveUser")
    public String saveUser(){
        try {
            userService.saveUser();
            return "保存User";
        } catch (Exception e) {
            e.printStackTrace();
            return "保存失败";
        }
    }

    @RequestMapping("{username}")
    public Object getUserByUsername(@PathVariable String username){
        return userDao.findAllByAgeGreaterThanEqualAndAgeLessThanEqual(username, "35");
    }




}
