package com.lin.ffm.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lin.ffm.dao.UserDao;
import com.lin.ffm.pojo.Bill;
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
    public PageInfo<User> findAllUser(User user,Integer pageNum, Integer pageSize) {
        if (pageNum == null){
            pageNum = 1;
        }
        if (pageSize == null){
            pageSize =5;
        }
        PageHelper.startPage(pageNum,pageSize);
        List<User> users = userDao.findAllUser(user);
        PageInfo<User> page = new PageInfo<>(users);
        return page;
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
        return userDao.register(user);
    }

    @Override
    public int deleteUser(int id) {
        return userDao.deleteUser(id);
    }

    @Override
    public List<User> findUsers(User user) {
        return userDao.findAllUser(user);
    }

    @Override
    public List<User> findFamily(User user) {
        return userDao.findAllUser(user);
    }

    @Override
    public int changeHouseId(User user) {
        return userDao.changeHouseId(user);
    }
}
