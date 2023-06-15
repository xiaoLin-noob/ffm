package com.lin.ffm.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdminDao {

    @Select("select DISTINCT(houseId) from user where houseId != 0 and houseId != -1;")
    List<Integer> findAllHouseId();

    @Select("select id from user where id != 1;")
    List<Integer> findAllUserId();


    @Select("select sum(case month(b.time) when #{month} then b.money else 0 end) as #{month}月 from bill b join user u on b.userId = u.id " +
            "where u.houseId = #{houseId} " +
            "and b.type = #{type} " +
            "and year(b.time) = #{year}\n")
    Double billOfHouse(int year, int month,int type,int houseId);

    @Select("select sum(case month(l.startDate) when #{month} then l.money else 0 end) as #{month}月 from loan l join user u on l.userId = u.id " +
            "where u.houseId = #{houseId} " +
            "and year(l.startDate) = #{year}\n")
    Double loanOfHouse(int year, int month,int houseId);

    @Select("select sum(case month(i.startDate) when #{month} then i.money else 0 end) as #{month}月 from invest i join user u on i.userId = u.id " +
            "where u.houseId = #{houseId} " +
            "and year(i.startDate) = #{year}\n")
    Double investOfHouse(int year, int month,int houseId);

}
