package com.lin.ffm.service;

import com.lin.ffm.pojo.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User login(User user);
}
