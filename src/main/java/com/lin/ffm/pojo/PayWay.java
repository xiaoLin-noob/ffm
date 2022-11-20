package com.lin.ffm.pojo;

public class PayWay {
    private int id;
    private String payWay;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    @Override
    public String toString() {
        return "PayWay{" +
                "id=" + id +
                ", payWay='" + payWay + '\'' +
                '}';
    }
}
