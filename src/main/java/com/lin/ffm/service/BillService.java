package com.lin.ffm.service;

import com.github.pagehelper.PageInfo;
import com.lin.ffm.pojo.Bill;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface BillService {

    PageInfo<Bill> findBills(Bill bill,Integer pageNum,Integer pageSize);

    int editBill(Bill bill);

    Bill findBillById(int id);

    int deleteBill(int id);

    int addBill(Bill bill);

    Double AllInBill(int houseId);

    Double AllOutBill(int houseId);

    Double outYear(int id,int year,int month,int type);

    Double AllOutBillForId(int userId);

    Double AllinBillForId(int userId);

    PageInfo<Bill> ALlBill(Integer pageNum,Integer pageSize);
}
