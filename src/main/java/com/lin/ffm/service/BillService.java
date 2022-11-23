package com.lin.ffm.service;

import com.github.pagehelper.PageInfo;
import com.lin.ffm.pojo.Bill;

import java.util.List;

public interface BillService {

    PageInfo<Bill> findBills(Bill bill,Integer pageNum,Integer pageSize);

    int editBill(Bill bill);
}
