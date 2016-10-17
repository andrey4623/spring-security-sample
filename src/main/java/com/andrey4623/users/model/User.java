package com.andrey4623.users.model;

import java.util.HashSet;
import java.util.Set;

public class User{

    private Integer id;
    private String username;
    private String password;
    private String name;
    private Set<UserRole> userRole = new HashSet<UserRole>(0);

    public User() {
    }

    public User(Integer id, String username, String password, String name) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public User(Integer id, String username, String password, String name, Set<UserRole> userRole) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.userRole = userRole;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<UserRole> getUserRole() {
        return this.userRole;
    }

    public void setUserRole(Set<UserRole> userRole) {
        this.userRole = userRole;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
