package com.lin.ffm.service.Impl;

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
    public List<Bill> findAllBill() {
        return billDao.findAllBill();
    }

    @Override
    public int changeBill(Bill bill) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String currentTime = dateFormat.format(new Date());
        bill.setTime(currentTime);
        return billDao.changeBill(bill);
    }
}
