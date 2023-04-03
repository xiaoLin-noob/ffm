package com.lin.ffm.service;

import com.github.pagehelper.PageInfo;
import com.lin.ffm.pojo.Item;
import com.lin.ffm.pojo.Loan;

import java.util.List;

public interface ItemService {

    int addItem(Item item);

    int editItem(Item item);

    List<Item> allItem(int id);

    int invited(Item item);

    List<Item> readItem(int id);

    int isReadItem(int id);

    int findUserId(int id);


    PageInfo<Item> AllItem(Integer pageNum, Integer pageSize);

}
