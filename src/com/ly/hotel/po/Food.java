package com.ly.hotel.po;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Food {
    private Integer id;
    @Size(max=30,min=1,message="{food.name.length.error}")
    private String foodname;

    private Integer foodtypeId;
    @NotNull(message="{food.price.notNull.error}")
    private Double price;

    private Double mprice;

    private String remark;

    private String img;

    private String foodtype;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname == null ? null : foodname.trim();
    }

    public Integer getFoodtypeId() {
        return foodtypeId;
    }

    public void setFoodtypeId(Integer foodtypeId) {
        this.foodtypeId = foodtypeId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getMprice() {
        return mprice;
    }

    public void setMprice(Double mprice) {
        this.mprice = mprice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public String getFoodtype() {
        return foodtype;
    }

    public void setFoodtype(String foodtype) {
        this.foodtype = foodtype == null ? null : foodtype.trim();
    }
}