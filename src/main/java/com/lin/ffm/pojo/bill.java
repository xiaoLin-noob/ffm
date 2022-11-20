package com.lin.ffm.pojo;

import javax.xml.crypto.Data;

public class bill {
    private int id;
    private String title;
    private int userId;
    private double money;
    private String type;
    private int paywayId;
    private Data time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPaywayId() {
        return paywayId;
    }

    public void setPaywayId(int paywayId) {
        this.paywayId = paywayId;
    }

    public Data getTime() {
        return time;
    }

    public void setTime(Data time) {
        this.time = time;
    }
}
