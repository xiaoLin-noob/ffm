package com.lin.ffm.service;

import java.util.List;

public interface AdminService {

    List<Integer> findAllHouseId();

    List<Integer> findAllUserId();

    Double billOfHouse(int year, int month,int type,int houseId);

    Double loanOfHouse(int year, int month,int houseId);

    Double investOfHouse(int year, int month,int houseId);
}
