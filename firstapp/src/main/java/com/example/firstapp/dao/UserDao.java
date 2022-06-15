package com.example.firstapp.dao;

import com.example.firstapp.models.User;


import java.util.List;

public interface UserDao {
    //list users
    List<User> getUsers();

    // delete user
    void delUser(Long id);

    // register user
    void regUser(User user);

    User getUserWithCredentials(User user);

}