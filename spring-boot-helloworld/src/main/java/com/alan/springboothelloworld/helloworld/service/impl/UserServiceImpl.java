package com.alan.springboothelloworld.helloworld.service.impl;

import com.alan.springboothelloworld.common.util.UUIDGenerator;
import com.alan.springboothelloworld.helloworld.dao.UserDao;
import com.alan.springboothelloworld.helloworld.entity.User;
import com.alan.springboothelloworld.helloworld.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
@CacheConfig(cacheNames = "userService")
public class UserServiceImpl implements UserService {

    Log log = LogFactory.getLog(UserServiceImpl.class);
    @Autowired
    private UserDao userDao;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;



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
        String key = "username";
        log.info("调用数据库数据");
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) { // 从缓存中取
            ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
            System.out.println( valueOperations.get(key));
            log.info("从缓存中获取了用户！");
            return null;
        }
        return userDao.findAll();
    }

    @Cacheable
    @Override
    public void testValueOption(){
        User user=new User("111", "李四2", "28", new Date());
        ValueOperations valueOperations = redisTemplate.opsForValue();
//        valueOperations.set("testValueOption",user);
        System.out.println(valueOperations.get("test"));
    }
}
