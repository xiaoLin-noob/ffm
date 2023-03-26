package com.lin.ffm.dao;

import com.lin.ffm.pojo.Item;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ItemDao {

    @Select("select COUNT(*) from item where userId = #{userId} and addressId = #{addressId} and time > DATE_SUB(now(),INTERVAL 3 DAY)")
    int invited (Item item);

//    @Select("select * from item where addressId = #{id}")
    List<Item> allItem(int id);

//    @Select("select * from item where id = #{id}")
    List<Item> readItem(int id);

    @Select("select userId from item where id = #{id}")
    int findUserId(int id);

    @Insert("insert into item(userId, addressId, msg, isRead,time) values(#{userId},#{addressId},#{msg},#{isRead},#{time})")
    int addItem(Item item);

    @Update("update item set userId=#{userId},addressId=#{addressId},msg=#{msg},isRead=#{isRead},time=#{time} where id = #{id}")
    int editItem(Item item);

    @Update("update item set isRead=1 where id =#{id};")
    int isReadItem(int id);
}
