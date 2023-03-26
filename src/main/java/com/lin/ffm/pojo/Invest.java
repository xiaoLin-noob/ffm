package com.lin.ffm.pojo;

import java.util.Date;

public class Invest {

    private int id;
    private int userId;
    private String name;
    private Double money;
    private Double rate;
    private String startDate;
    private String endDate;
    private Double income;
    private String msg;

    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Invest{" +
                "id=" + id +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", money=" + money +
                ", rate=" + rate +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", income=" + income +
                ", msg='" + msg + '\'' +
                '}';
    }
}
