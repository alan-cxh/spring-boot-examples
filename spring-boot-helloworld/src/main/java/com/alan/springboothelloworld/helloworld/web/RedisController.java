package com.alan.springboothelloworld.helloworld.web;


import com.alan.springboothelloworld.helloworld.entity.User;
import com.alan.springboothelloworld.helloworld.service.impl.RedisServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping(value = "/redis")
public class RedisController {

    @Autowired
    private RedisServiceImpl redisService;
    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(value = "/list")
    public List<User> list() {
        return redisService.list("11111");
    }

    @RequestMapping(value = "/updateList")
    public List<User> updateList() {
        return redisService.updateList();
    }

    @RequestMapping(value = "/getbyId")
    public User getbyId() {
        return redisService.getbyId("111");
    }

    @RequestMapping(value = "/delete")
    public void delete() {
        redisService.delete();
    }

//    添加缓存user
    @RequestMapping(value = "/testValueOption")
    public void testValueOption() {
        User user = redisService.testValueOption();
        if (user != null) {
            System.out.println(user.getUsername());
        }
    }

    //    添加缓存user1
    @RequestMapping(value = "/testValueOption1")
    public void testValueOption1() {
        User user=new User("user1", "王五", "28", new Date());
        user = redisService.testValueOption1(user);
        if (user != null) {
            System.out.println(user.getUsername());
        }
    }

    //    添加缓存user1
    @RequestMapping(value = "/addString")
    public void addString() {
        System.out.println(redisService.addString());
    }

    @RequestMapping(value = "/getByRedisTemplate")
    public void getByRedisTemplate() {
        User user = redisService.getByRedisTemplate();
        if (user != null) {
            System.out.println(user.getUsername());
        }
    }

//    更新缓存
    @RequestMapping(value = "/updateValueOption")
    public void updateValueOption() {
        User user = redisService.updateValueOption();
        if (user != null) {
            System.out.println(user.getUsername());
        }
    }
// 删除缓存
    @RequestMapping(value = "/deleteValueOption")
    public void deleteValueOption() {
        User user = redisService.deleteValueOption();
        if (user != null) {
            System.out.println(user.getUsername());
        }

    }


    @RequestMapping(value = "/set")
    public void setOperation() {
        Set<String> set1 = new HashSet<String>();
        set1.add("set1");
        set1.add("set2");
        set1.add("set3");
        // 插入
        redisTemplate.opsForSet().add("set1", set1);
        // 获取
        Set<String> resultSet = redisTemplate.opsForSet().members("set1");
        System.out.println("resultSet:" + resultSet);
    }

    /**
     * Map
     */
    @RequestMapping(value = "/map")
    public void mapOperation() {
        Map<String, String> map = new HashMap();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        map.put("key4", "value4");
        map.put("key5", "value5");
        // 插入
        redisTemplate.opsForHash().putAll("map1", map);

        Map<String, String> resultMap = redisTemplate.opsForHash().entries("map1");
        System.out.println("resultMap:" + resultMap);

        List<String> reslutMapList = redisTemplate.opsForHash().values("map1");
        System.out.println("reslutMapList:" + reslutMapList);

        Set<String> resultMapSet = redisTemplate.opsForHash().keys("map1");
        System.out.println("resultMapSet:" + resultMapSet);

        String value = (String) redisTemplate.opsForHash().get("map1", "key1");
        System.out.println("value:" + value);
    }


    /**
     * List
     */
    @RequestMapping(value = "/listOperation")
    public void listOperation() {
        List<String> list1 = new ArrayList<String>();
        list1.add("a1");
        list1.add("a2");
        list1.add("a3");

        List<String> list2 = new ArrayList<String>();
        list2.add("b1");
        list2.add("b2");
        list2.add("b3");
        // 插入
        redisTemplate.opsForList().leftPush("listkey1", list1);
        redisTemplate.opsForList().rightPush("listkey2", list2);
//        redisTemplate.opsForList().
        // 取数据
        List<String> resultList1 = (List<String>) redisTemplate.opsForList().leftPop("listkey1");
        List<String> resultList2 = (List<String>) redisTemplate.opsForList().rightPop("listkey2");
        System.out.println("resultList1:" + resultList1);
        System.out.println("resultList2:" + resultList2);
    }

    @RequestMapping(value = "/testOperation")
    public void testOperation() {
        Map<String,String> maps = new HashMap<String, String>();
        maps.put("multi1","multi1");
        maps.put("multi2","multi2");
        maps.put("multi3","multi3");
        redisTemplate.opsForValue().multiSet(maps);
        List<String> keys = new ArrayList<String>();
        keys.add("multi1q");
        keys.add("multi2");
        keys.add("multi3");
        System.out.println(redisTemplate.opsForValue().multiGet(keys));
    }


    /**
     * 位操作
     */
    @RequestMapping(value = "/bitOperation")
    public void BitOperation() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.setBit("bit", 100,true);

        valueOperations.getBit("bit", 1000);
    }
























}
