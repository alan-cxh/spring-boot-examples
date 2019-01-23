package com.alan.springboothelloworld.helloworld.service.impl;

import com.alan.springboothelloworld.common.util.UUIDGenerator;
import com.alan.springboothelloworld.helloworld.dao.UserDao;
import com.alan.springboothelloworld.helloworld.entity.User;
import com.alan.springboothelloworld.helloworld.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
@CacheConfig(cacheNames = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;



    // 添加事务注解之后，需要将业务逻辑添加到该方法下，使得入库时数据是同步的，否则遇到异常，则回滚数据
    @Transactional
    @Override
    public void saveUser() {
        User user = new User();
        user.setId(UUIDGenerator.getUUID());
        user.setUsername("alan_chen");
        user.setAge("27");
        user.setCreateDate(new Date());
        userDao.save(user);
//        int i = 1/0;
    }

    @Cacheable
    @Override
    public List<User> list() {
        return userDao.findAll();
    }
}
