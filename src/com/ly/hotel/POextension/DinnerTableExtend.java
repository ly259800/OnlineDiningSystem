package com.ly.hotel.POextension;

import java.util.List;

import com.ly.hotel.po.Dinnertable;

public class DinnerTableExtend extends Dinnertable{
	//扩展属性
	private List<OrdersExtend> ordersExtends;

	public List<OrdersExtend> getOrdersExtends() {
		return ordersExtends;
	}

	public void setOrdersExtends(List<OrdersExtend> ordersExtends) {
		this.ordersExtends = ordersExtends;
	}
	
}
