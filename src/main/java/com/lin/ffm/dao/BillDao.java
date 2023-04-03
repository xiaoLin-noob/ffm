package com.lin.ffm.dao;

import com.lin.ffm.pojo.Bill;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BillDao {


//    @Select("select * from bill")
    List<Bill> findBills(Bill bill);

    //@Update("update Bill set title=#{title},money=#{money},type=#{type},payWayId=#{payWayId} where id= #{id}")
    int editBill(Bill bill);

    @Select("select * from bill")
    List<Bill> ALlBill();


    @Select("select * from bill where id=#{id}")
    Bill findBillById(int id);


    @Delete("delete from bill where id = #{id}")
    int deleteBill(int id);

    @Insert("insert into Bill (title,userId,money,type,payWayId,time) values(#{title},#{userId},#{money},#{type},#{payWayId},#{time})")
    int addBill(Bill bill);


    @Select("select sum(case month(time) when #{month} then money else 0 end) as #{month}月\n" +
            "from bill  where userId = #{id}\n" +
            "and year(time)=#{year}\n" +
            "and type = #{type};")
    Double outYear(int id,int year,int month,int type);

    @Select("select sum(b.money) from bill b join user u on b.userId = u.id where type = 0 and houseId = #{houseId}")
    Double AllInBill(int houseId);

    @Select("select sum(b.money) from bill b join user u on b.userId = u.id where type = 1 and houseId = #{houseId}")
    Double AllOutBill(int houseId);

    @Select("select sum(b.money) from bill b where type = 1 and userId = #{userId}")
    Double AllOutBillForId(int userId);

    @Select("select sum(b.money) from bill b where type = 0 and userId = #{userId}")
    Double AllinBillForId(int userId);

}
