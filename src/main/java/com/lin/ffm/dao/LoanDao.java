package com.lin.ffm.dao;

import com.lin.ffm.pojo.Loan;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface LoanDao {

    List<Loan> findLoans(Loan loan);

    @Select("select * from loan where id=#{id}")
    Loan findLoanById(int id);


    @Delete("delete from loan where id = #{id}")
    int deleteLoan(int id);

    @Insert("INSERT INTO loan (userId,money,loan.where,rates,startDate,endDate,payBack,msg,status) VALUES (#{userId},#{money},#{where},#{rates},#{startDate},#{endDate},#{msg},#{payBack},#{status})")
    int addLoan(Loan loan);

    int editLoan(Loan loan);

    @Select("select sum(case month(startDate) when #{month} then money else 0 end) as #{month}月\n" +
            "from loan where userId = #{id}\n" +
            "and year(startDate)=#{year}")
    Double LoanYear(int id,int year,int month);

    @Select("select sum(l.money) from loan l join user u on l.userId = u.id where u.houseId = #{houseId}")
    Double AllLoan(int houseId);

    @Select("select sum(l.money) from loan l where userId = #{userId}")
    Double AllLoanForId(int userId);

    @Select("select * from loan")
    List<Loan> loans();
}
