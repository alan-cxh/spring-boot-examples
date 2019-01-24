package com.alan.springboothelloworld.helloworld.dao;

import com.alan.springboothelloworld.helloworld.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, String> {


    // 根据名字查询 ok
    public List<User> findByUsername(String username);
    // 效果同上，精确查询
    public List<User> findByUsernameIs(String username);

//    依据姓名模糊查询  // 一定要加 "%"+参数名+"%"
    @Query(value = "select t from User t where t.username like %?1% ")
    public List<User> findByUsernameLike( String username);

    // 在age大的数据 即大于age
    public List<User> findAllByAgeAfter(String age);
//    age1 < age < age2
    public List<User> findAllByAgeAfterAndAgeBefore(String age1, String age2);
    //    age1 <= age <= age2   .注意：如果有注解，则优先执行注解中的sql语句
    @Query(value = "select u from User u where u.age>?1 and u.age <?2 ")
    public List<User> findAllByAgeGreaterThanEqualAndAgeLessThanEqual(String age1, String age2);

    // 分页
    public Page<User> findAll(Pageable pageable);

}
