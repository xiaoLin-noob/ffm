package com.lin.ffm.dao;


import com.lin.ffm.pojo.Invest;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface InvestDao {


    @Select("select * from invest where id = #{id}")
    Invest findInvestById(int id);

    List<Invest> findInvests(Invest invest);

    int editInvest(Invest invest);

    @Delete("delete from invest where id = #{id}")
    int deleteInvest(int id);

    @Insert("INSERT INTO invest ( userId, name, money, rate, startDate, endDate, income, msg) VALUES ( #{userId}, #{name}, #{money}, #{rate}, #{startDate}, #{endDate}, #{income}, #{msg});")
    int addInvest(Invest invest);


    @Select("select sum(case month(startDate) when #{month} then money else 0 end) as #{month}月\n" +
            "from invest where userId = #{id}\n" +
            "and year(startDate)=#{year}")
    Double InvestYear(int id,int year,int month);
}
