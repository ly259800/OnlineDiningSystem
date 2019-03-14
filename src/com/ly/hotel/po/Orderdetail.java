package com.ly.hotel.po;

public class Orderdetail {
    private Integer id;

    private Integer orderid;

    private Integer foodId;

    private Integer foodcount;
    //private Food food;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public Integer getFoodcount() {
        return foodcount;
    }

    public void setFoodcount(Integer foodcount) {
        this.foodcount = foodcount;
    }
}