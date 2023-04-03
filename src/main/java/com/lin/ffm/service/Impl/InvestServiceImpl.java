package com.lin.ffm.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lin.ffm.dao.InvestDao;
import com.lin.ffm.pojo.Bill;
import com.lin.ffm.pojo.Invest;
import com.lin.ffm.pojo.Loan;
import com.lin.ffm.service.InvestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvestServiceImpl implements InvestService {

    @Autowired
    private InvestDao investDao;

    @Override
    public PageInfo<Invest> findInvest(Invest invest, Integer pageNum, Integer pageSize) {
        if (pageNum == null){
            pageNum = 1;
        }
        if (pageSize == null){
            pageSize =5;
        }
        PageHelper.startPage(pageNum,pageSize);
        List<Invest> invests = investDao.findInvests(invest);
        PageInfo<Invest> page = new PageInfo<>(invests);
        return page;
    }

    @Override
    public int editInvest(Invest invest) {
        return investDao.editInvest(invest);
    }

    @Override
    public int deleteInvest(int id) {
        return investDao.deleteInvest(id);
    }

    @Override
    public int addInvest(Invest invest) {
        return investDao.addInvest(invest);
    }

    @Override
    public Invest findInvestById(int id) {
        return investDao.findInvestById(id);
    }

    @Override
    public Double InvestYear(int id, int year, int month) {
        return investDao.InvestYear(id,year,month);
    }

    @Override
    public Double AllInvest(int houseId) {
        return investDao.AllInvest(houseId);
    }

    @Override
    public Double AllInvestForId(int userId) {
        return investDao.AllInvestForId(userId);
    }

    @Override
    public PageInfo<Invest> invests(Integer pageNum, Integer pageSize) {
        if (pageNum == null){
            pageNum = 1;
        }
        if (pageSize == null){
            pageSize =5;
        }
        PageHelper.startPage(pageNum,pageSize);
        List<Invest> invests = investDao.invests();
        PageInfo<Invest> page = new PageInfo<>(invests);
        return page;
    }


}
