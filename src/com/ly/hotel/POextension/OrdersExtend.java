package com.ly.hotel.POextension;

import java.util.List;

import com.ly.hotel.po.Orders;

public class OrdersExtend extends Orders{
	//餐桌信息
	private DinnerTableExtend dinnerTableExtend;
	//订单明细
	private List<OrderDetailExtend> orderDetailExtends;

	public DinnerTableExtend getDinnerTableExtend() {
		return dinnerTableExtend;
	}

	public void setDinnerTableExtend(DinnerTableExtend dinnerTableExtend) {
		this.dinnerTableExtend = dinnerTableExtend;
	}

	public List<OrderDetailExtend> getOrderDetailExtends() {
		return orderDetailExtends;
	}

	public void setOrderDetailExtends(List<OrderDetailExtend> orderDetailExtends) {
		this.orderDetailExtends = orderDetailExtends;
	}
	
}
