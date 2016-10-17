package com.andrey4623.users.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import java.util.Collection;

public class ExtendedUser extends User {

    private Integer id;
    private String name;

    public ExtendedUser(Integer id, String username, String name, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        setId(id);
        setName(name);
    }

    public ExtendedUser(Integer id, String username, String name, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        setId(id);
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
