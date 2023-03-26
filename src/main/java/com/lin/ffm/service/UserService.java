package com.lin.ffm.service;

import com.github.pagehelper.PageInfo;
import com.lin.ffm.pojo.User;

import java.util.List;

public interface UserService {

    String findUsernameById(int id);

    PageInfo<User> findAllUser(User user, Integer pageNum, Integer pageSize);

    List<User> findFamily(User user);

    int changeHouseId(User user);

    User login(User user);

    User findUserById(int id);

    int editUser(User user);

    int register(User user);

    int deleteUser(int id);

    List<User> findUsers(User user);
}
