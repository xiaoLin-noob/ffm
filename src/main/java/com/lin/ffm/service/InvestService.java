package com.lin.ffm.service;

import com.github.pagehelper.PageInfo;
import com.lin.ffm.pojo.Invest;
import com.lin.ffm.pojo.Loan;

import java.util.List;

public interface InvestService {

    PageInfo<Invest> findInvest(Invest invest,Integer pageNum,Integer pageSize);

    int editInvest(Invest invest);

    int deleteInvest(int id);

    int addInvest(Invest invest);

    Invest findInvestById(int id);

    Double InvestYear(int id,int year,int month);
    Double AllInvest(int houseId);

    Double AllInvestForId(int userId);

    PageInfo<Invest> invests(Integer pageNum, Integer pageSize);


}
