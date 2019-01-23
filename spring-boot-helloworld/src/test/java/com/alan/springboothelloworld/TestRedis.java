package com.alan.springboothelloworld;

import com.alan.springboothelloworld.helloworld.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestRedis {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ValueOperations valueOperations;
    @Autowired
    private SetOperations setOperations;

    @Test
    public void test() throws Exception {
        stringRedisTemplate.opsForValue().set("aaa", "王五");
        User user=new User("111", "李四12", "28", new Date());
        redisTemplate.opsForValue().set("user", user);
        User user1 = (User) redisTemplate.opsForValue().get("user");
        System.out.println(user1.getUsername());
        try {
            Assert.assertEquals("王五", stringRedisTemplate.opsForValue().get("aaa"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testObj() throws Exception {
        User user=new User("111", "李四2", "28", new Date());
        ValueOperations<String, User> operations=redisTemplate.opsForValue();
        operations.set("com.neox", user);
        operations.set("com.neo.f", user,20, TimeUnit.SECONDS); // 存值并设置超时时间，若超时，则删除
//        Thread.sleep(5000);
        //redisTemplate.delete("com.neo.f");
        operations.get("com.neox");
        boolean exists=redisTemplate.hasKey("com.neo.f");
        if(exists){
            System.out.println("exists is true");
        }else{
            System.out.println("exists is false");
        }
        // Assert.assertEquals("aa", operations.get("com.neo.f").getUserName());
    }

    @Test
    public void testValueOption( )throws  Exception{
        User user=new User("111", "李四2", "28", new Date());
        valueOperations.set("testValueOption",user);
        System.out.println(valueOperations.get("test"));
    }

    @Test
    public void testSetOperation() throws Exception{
        User user=new User("user", "李四2user", "28user", new Date());
        User test=new User("test", "李四2test", "28test", new Date());
        SetOperations setOperations1 = redisTemplate.opsForSet();
        setOperations1.add("user:test",user,test);
        Set<Object> result = setOperations1.members("user:test");

        System.out.println(result);
    }

}
