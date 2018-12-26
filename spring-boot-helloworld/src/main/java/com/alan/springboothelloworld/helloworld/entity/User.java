package com.alan.springboothelloworld.helloworld.entity;


import javax.persistence.*;

@Entity
@Table(name="t_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String username;
    private String age;

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
}
