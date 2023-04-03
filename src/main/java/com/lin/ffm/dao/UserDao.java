package com.lin.ffm.dao;

import com.lin.ffm.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface UserDao {

//    @Select("select * from user")
    List<User> findAllUser(User user);

    @Select("select username from user where id")
    String findUsernameById(int id);

    @Select("select * from user where username=#{username}")
    User login(User user);

    @Select("select * from user where id=#{id}")
    User findUserById(int id);

    int editUser(User user);

    @Insert("Insert into user (username,password,role,msgId,houseId) values (#{username},#{password},#{role},#{msgId},#{houseId})")
    int register(User user);

    @Delete("delete from user where id = #{id}")
    int deleteUser(int id);

    @Update("update user set houseId = #{houseId} where id = #{id}")
    int changeHouseId(User user);

    @Select("select * from user")
    List<User> AllUser();
}
