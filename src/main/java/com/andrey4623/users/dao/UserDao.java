package com.andrey4623.users.dao;

import com.andrey4623.users.model.User;

public interface UserDao {

    User findByUserName(String username);

}