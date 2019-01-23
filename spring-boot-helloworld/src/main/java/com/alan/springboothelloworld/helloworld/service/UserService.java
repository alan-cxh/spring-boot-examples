package com.alan.springboothelloworld.helloworld.service;

import com.alan.springboothelloworld.helloworld.entity.User;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface UserService {
    void saveUser();

    List<User> list();

    @Cacheable
    void testValueOption();
}
