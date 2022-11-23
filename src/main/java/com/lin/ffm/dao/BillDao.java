package com.lin.ffm.dao;

import com.lin.ffm.pojo.Bill;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BillDao {

//    @Select("Select b.*,u.id as uid,u.username,p.id as pid,p.payway from bill b,user u,payway p where b.userId = u.id and b.paywayid = p.id")
//    @Results({@Result(column = "uid",property = "user.id"),
//              @Result(column = "pid",property = "payWay.id"),
//              @Result(column = "username",property = "user.username"),
//              @Result(column = "payway",property = "payWay.payWay")})
    List<Bill> findBills(Bill bill);

    //@Update("update Bill set title=#{title},money=#{money},type=#{type},payWayId=#{payWayId} where id= #{id}")
    int editBill(Bill bill);



}
