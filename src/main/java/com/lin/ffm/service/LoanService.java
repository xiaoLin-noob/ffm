package com.lin.ffm.service;


import com.github.pagehelper.PageInfo;
import com.lin.ffm.pojo.Loan;

public interface LoanService {

    PageInfo<Loan> findLoans(Loan loan, Integer pageNum, Integer pageSize);

    Loan findLoanById(int id);

    int editLoan(Loan loan);

    int addLoan(Loan loan);

    int deleteLoan(int id);

    Double LoanYear(int id,int year,int month);
}
