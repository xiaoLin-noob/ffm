package com.lin.ffm.dao;

import com.lin.ffm.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface UserDao {

    @Select("select * from user")
    List<User> findAll();

    @Select("select * from user where username=#{username} and password = #{password}")
    User login(User user);


}
