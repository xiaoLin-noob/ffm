package com.lin.ffm.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lin.ffm.dao.ItemDao;
import com.lin.ffm.pojo.Item;
import com.lin.ffm.pojo.Loan;
import com.lin.ffm.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    ItemDao itemDao;

    @Override
    public int addItem(Item item) {
        return itemDao.addItem(item);
    }

    @Override
    public int editItem(Item item) {
        return itemDao.editItem(item);
    }

    @Override
    public List<Item> allItem(int id) {
        return itemDao.allItem(id);
    }

    @Override
    public int invited(Item item) {
        return itemDao.invited(item);
    }

    @Override
    public List<Item> readItem(int id) {
        return itemDao.readItem(id);
    }

    @Override
    public int isReadItem(int id) {
        return itemDao.isReadItem(id);
    }

    @Override
    public int findUserId(int id) {
        return itemDao.findUserId(id);
    }

    @Override
    public PageInfo<Item> AllItem(Integer pageNum, Integer pageSize) {
        if (pageNum == null){
            pageNum = 1;
        }
        if (pageSize == null){
            pageSize =5;
        }
        PageHelper.startPage(pageNum,pageSize);
        List<Item> items = itemDao.items();
        PageInfo<Item> page = new PageInfo<>(items);
        return page;
    }

}
