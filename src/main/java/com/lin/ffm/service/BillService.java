package com.lin.ffm.service;

import com.lin.ffm.pojo.Bill;

import java.util.List;

public interface BillService {

    List<Bill> findAllBill();

    int changeBill(Bill bill);
}
