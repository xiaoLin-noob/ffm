package com.lin.ffm.pojo;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "payway")
public class PayWay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
