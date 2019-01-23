package com.alan.springboothelloworld.helloworld.service;

import com.alan.springboothelloworld.helloworld.entity.User;

import java.util.List;

public interface UserService {
    void saveUser();

    List<User> list();
}
