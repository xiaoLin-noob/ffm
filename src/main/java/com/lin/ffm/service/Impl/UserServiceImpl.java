package com.lin.ffm.service.Impl;

import com.lin.ffm.dao.UserDao;
import com.lin.ffm.pojo.User;
import com.lin.ffm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public String findUsernameById(int id) {
        return userDao.findUsernameById(id);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAllUser();
    }

    @Override
    public User login(User user) {
        return userDao.login(user);
    }

    @Override
    public User findUserById(int id) {
        return userDao.findUserById(id);
    }

    @Override
    public int editUser(User user) {
        return userDao.editUser(user);
    }

    @Override
    public int register(User user) {
        user.setRole("user");
        return userDao.register(user);
    }
}
