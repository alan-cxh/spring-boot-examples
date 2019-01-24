package com.alan.springboothelloworld.helloworld.web;

import com.alan.springboothelloworld.helloworld.dao.UserDao;
import com.alan.springboothelloworld.helloworld.entity.User;
import com.alan.springboothelloworld.helloworld.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
@RequestMapping("hello")
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

    /**
     * Jpa 查询，自定义sql
     * @param username
     * @return
     */
    @RequestMapping("{username}")
    public Object getUserByUsername(@PathVariable String username){
        Sort sort = new Sort(Sort.Direction.ASC,"id"); //创建时间降序排序
        Pageable pageable = new PageRequest(0,3,sort);
        return userDao.findAll(pageable);
    }







}
