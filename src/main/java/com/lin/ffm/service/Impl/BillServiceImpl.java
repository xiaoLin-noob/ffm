package com.lin.ffm.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lin.ffm.dao.BillDao;
import com.lin.ffm.pojo.Bill;
import com.lin.ffm.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillDao billDao;

    @Override
    public PageInfo<Bill> findBills(Bill bill,Integer pageNum,Integer pageSize) {
        if (pageNum == null){
            pageNum = 1;
        }
        if (pageSize == null){
            pageSize =5;
        }
        PageHelper.startPage(pageNum,pageSize);
        List<Bill> bills = billDao.findBills(bill);
        PageInfo<Bill> page = new PageInfo<>(bills);
        return page;
    }

    @Override
    public int editBill(Bill bill) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String currentTime = dateFormat.format(new Date());
        bill.setTime(currentTime);
        return billDao.editBill(bill);
    }

    @Override
    public Bill findBillById(int id) {
        return billDao.findBillById(id);
    }

    @Override
    public int deleteBill(int id) {
        return billDao.deleteBill(id);
    }

    @Override
    public int addBill(Bill bill) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String currentTime = dateFormat.format(new Date());
        bill.setTime(currentTime);
        return billDao.addBill(bill);
    }


}
