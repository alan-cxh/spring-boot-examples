package com.alan.springboothelloworld.helloworld.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="t_user")
public class User {
    @Id
    private String id;
    @NotNull
    private String username;
    @NotNull
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
