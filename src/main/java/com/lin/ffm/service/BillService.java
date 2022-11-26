package com.lin.ffm.service;

import com.github.pagehelper.PageInfo;
import com.lin.ffm.pojo.Bill;


public interface BillService {

    PageInfo<Bill> findBills(Bill bill,Integer pageNum,Integer pageSize);

    int editBill(Bill bill);

    Bill findBillById(int id);

    int deleteBill(int id);

    int addBill(Bill bill);
}
