package com.alan.springboothelloworld.helloworld.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="t_user")
public class User implements Serializable {
    @Id
    private String id;
    @NotNull(message = "用户帐号不能为空")
    private String username;
    @NotNull
    private String age;

    @Column(name = "createdate")
    private Date createDate;

    public User(String id, String username, String age, Date createDate){
        this.id = id;
        this.username = username;
        this.age = age;
        this.createDate = createDate;
    }
    public User(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
