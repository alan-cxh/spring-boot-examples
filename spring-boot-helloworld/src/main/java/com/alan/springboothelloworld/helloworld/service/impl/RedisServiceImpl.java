package com.alan.springboothelloworld.helloworld.service.impl;

import com.alan.springboothelloworld.common.util.UUIDGenerator;
import com.alan.springboothelloworld.helloworld.dao.UserDao;
import com.alan.springboothelloworld.helloworld.entity.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *  使用方式1： @CacheConfig结合@CachePut   @Cacheable @CacheEvict
 */
@Service
@Transactional
@CacheConfig(cacheNames = "redis")
public class RedisServiceImpl{

    Log log = LogFactory.getLog(RedisServiceImpl.class);
    @Autowired
    private UserDao userDao;
    @Autowired
    private RedisTemplate redisTemplate;

    @Cacheable(key = "'list'")
    public List<User> list() {
        log.info("调用数据库数据");
        return userDao.findAll();
    }

    /**
     * 此方法实现数据同步。当新增一条数据时，入库mysql同事，更新redis缓存
     * @return
     */
    @CachePut(key = "'list'")
    public List<User> updateList() {
        log.info("调用数据库数据");
        User user=new User(UUIDGenerator.getUUID(), "jinshiyi", "28", new Date());
        userDao.save(user);
        return userDao.findAll();
    }


    @Cacheable(key = "'list'")
    public User getbyId(String id) {
        log.info("调用数据库数据getbyId");
        return userDao.findOne(id);
    }


    @Cacheable(value = "redis", key = "'str'")
    public String delete() {
        log.info("delete");
        return "喝了咯";
    }

    // 设置缓存user
    // 测试操作字符串
    @Cacheable(key = "'user'") //
    public User testValueOption() {
        User user=new User("111", "李四2", "28", new Date());
        System.out.println("testValueOption");
        return user;
    }

    // 设置缓存user1
    @Cacheable(key = "#user.id")
    public User testValueOption1(User user) {
//        user=new User("user", "王五", "28", new Date());
        System.out.println("testValueOption1");
        return user;
    }



    //    更新字符串缓存str
    @Cacheable(key="'str'")
    public String addString() {
        System.out.println("addString");
        return "hello world";
    }

//    通过redisTemplate获取缓存
    public User getByRedisTemplate() {
        System.out.println("getByRedisTemplate");
        String key = "user";
        User user = null;
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            ValueOperations<String, User> valueOperations = redisTemplate.opsForValue();
            valueOperations.get("redis~keys");
            user = valueOperations.get("user");
        }
        return user;
    }

//    更新缓存user
    @CachePut(key="'user'")
    public User updateValueOption() {
        User user=new User("111", "李四333333", "28", new Date());
        System.out.println("updateValueOption");
        return user;
    }


//      删除缓存user
    @CacheEvict(key = "'user'")
    public User deleteValueOption() {
        User user=new User("111", "李四222222", "28", new Date());
        System.out.println("deleteValueOption");
        return user;
    }
}
