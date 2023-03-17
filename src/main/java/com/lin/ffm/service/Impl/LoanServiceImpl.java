package com.lin.ffm.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lin.ffm.dao.LoanDao;
import com.lin.ffm.pojo.Loan;
import com.lin.ffm.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanDao loanDao;

    @Override
    public PageInfo<Loan> findLoans(Loan loan, Integer pageNum, Integer pageSize) {
        if (pageNum == null){
            pageNum = 1;
        }
        if (pageSize == null){
            pageSize =5;
        }
        PageHelper.startPage(pageNum,pageSize);
        List<Loan> loans = loanDao.findLoans(loan);
        PageInfo<Loan> page = new PageInfo<>(loans);
        return page;
    }

    @Override
    public Loan findLoanById(int id) {
        return loanDao.findLoanById(id);
    }

    @Override
    public int editLoan(Loan loan) {
        return loanDao.editLoan(loan);
    }

    @Override
    public int addLoan(Loan loan) {
        return loanDao.addLoan(loan);
    }

    @Override
    public int deleteLoan(int id) {
        return loanDao.deleteLoan(id);
    }
}
