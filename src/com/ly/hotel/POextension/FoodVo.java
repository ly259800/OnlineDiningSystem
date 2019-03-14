package com.ly.hotel.POextension;
//food包装类

import com.ly.hotel.po.Food;

public class FoodVo {
	//食物信息
	private Food food;
	//为了系统的可扩展性,对原始的po进行扩展
	private FoodExtend foodExtend;
	public Food getFood() {
		return food;
	}
	public void setFood(Food food) {
		this.food = food;
	}
	public FoodExtend getFoodExtend() {
		return foodExtend;
	}
	public void setFoodExtend(FoodExtend foodExtend) {
		this.foodExtend = foodExtend;
	}
}
