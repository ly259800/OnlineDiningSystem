package com.ly.hotel.POextension;

import java.util.List;

import com.ly.hotel.po.Food;

public class FoodExtend extends Food{
	//扩展食物
	//对应多个订单详细
	List<OrderDetailExtend> list;

	public List<OrderDetailExtend> getList() {
		return list;
	}

	public void setList(List<OrderDetailExtend> list) {
		this.list = list;
	}
	
}
