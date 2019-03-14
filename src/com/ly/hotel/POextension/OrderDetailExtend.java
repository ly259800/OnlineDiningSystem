package com.ly.hotel.POextension;


import com.ly.hotel.po.Orderdetail;

public class OrderDetailExtend extends Orderdetail{
	//对应一个订单
	private  OrdersExtend ordersExtend;
	//食品
	private FoodExtend foodExtend;
	public OrdersExtend getOrdersExtend() {
		return ordersExtend;
	}

	public void setOrdersExtend(OrdersExtend ordersExtend) {
		this.ordersExtend = ordersExtend;
	}

	public FoodExtend getFoodExtend() {
		return foodExtend;
	}

	public void setFoodExtend(FoodExtend foodExtend) {
		this.foodExtend = foodExtend;
	}
	
}
