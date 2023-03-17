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

    @Insert("INSERT INTO loan (userId,money,loan.where,rates,duration,payBack,msg,time) VALUES (#{userId},#{money},#{where},#{rates},#{duration},#{payBack},#{msg},#{time})")
    int addLoan(Loan loan);

    int editLoan(Loan loan);
}
