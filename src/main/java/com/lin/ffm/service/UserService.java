package com.lin.ffm.service;

import com.lin.ffm.pojo.User;

import java.util.List;

public interface UserService {

    String findUsernameById(int id);

    List<User> findAll();

    User login(User user);

    User findUserById(int id);

    int changePassword(int id);

    int register(User user);
}
