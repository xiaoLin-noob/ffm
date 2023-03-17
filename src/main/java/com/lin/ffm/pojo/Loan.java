package com.lin.ffm.pojo;

public class Loan {

    private Integer id;
    private int userId;
    private double money;
    private String where;
    private double rates;
    private int duration;
    private double payBack;
    private String msg;
    private String time;

    private User user;


//    public Loan(Integer id, int userId, double money, String where, double rates, int duration, double payBack, String msg, String time) {
//        this.id = id;
//        this.userId = userId;
//        this.money = money;
//        this.where = where;
//        this.rates = rates;
//        this.duration = duration;
//        this.payBack = payBack;
//        this.msg = msg;
//        this.time = time;
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public double getRates() {
        return rates;
    }

    public void setRates(double rates) {
        this.rates = rates;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getPayBack() {
        return payBack;
    }

    public void setPayBack(double payBack) {
        this.payBack = payBack;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", userId=" + userId +
                ", money=" + money +
                ", where='" + where + '\'' +
                ", rates=" + rates +
                ", duration=" + duration +
                ", payBack=" + payBack +
                ", msg='" + msg + '\'' +
                '}';
    }


}
