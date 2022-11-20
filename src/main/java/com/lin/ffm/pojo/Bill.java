package com.lin.ffm.pojo;

import javax.xml.crypto.Data;

public class Bill {

    private int id;
    private String title;
    private int userId;
    private double money;
    private String type;
    private int payWayId;
    private String time;

    private User user;
    private PayWay payWay;

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

    public int getPayWayId() {
        return payWayId;
    }

    public void setPayWayId(int payWayId) {
        this.payWayId = payWayId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PayWay getPayWay() {
        return payWay;
    }

    public void setPayWay(PayWay payWay) {
        this.payWay = payWay;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", userId=" + userId +
                ", money=" + money +
                ", type='" + type + '\'' +
                ", payWayId=" + payWayId +
                ", time='" + time + '\'' +
                ", user=" + user +
                ", payWay=" + payWay +
                '}';
    }
}
