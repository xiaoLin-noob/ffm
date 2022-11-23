package com.lin.ffm.dao;

import com.lin.ffm.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface UserDao {

    @Select("select * from user")
    List<User> findAllUser();

    @Select("select username from user where id")
    String findUsernameById(int id);

    @Select("select * from user where username=#{username}")
    User login(User user);

    @Select("select * from user where id=#{id}")
    User findUserById(int id);

    @Update("update user set password = #{password} where id=#{id}")
    int changePassword(int id);

    @Insert("Insert into user (username,password,role,houseId) values (#{username},#{password},#{role},#{houseId})")
    int register(User user);


}
