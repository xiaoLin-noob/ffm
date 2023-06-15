package com.lin.ffm.service.Impl;

import com.lin.ffm.dao.AdminDao;
import com.lin.ffm.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;
    @Override
    public List<Integer> findAllHouseId() {
        return adminDao.findAllHouseId();
    }

    @Override
    public List<Integer> findAllUserId() {
        return adminDao.findAllUserId();
    }

    @Override
    public Double billOfHouse(int year, int month, int type, int houseId) {
        return adminDao.billOfHouse(year,month,type,houseId);
    }

    @Override
    public Double loanOfHouse(int year, int month, int houseId) {
        return adminDao.loanOfHouse(year,month,houseId);
    }

    @Override
    public Double investOfHouse(int year, int month, int houseId) {
        return adminDao.investOfHouse(year,month,houseId);
    }
}
