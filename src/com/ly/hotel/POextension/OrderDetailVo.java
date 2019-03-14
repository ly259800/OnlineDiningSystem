package com.ly.hotel.POextension;

import com.ly.hotel.po.Orderdetail;

public class OrderDetailVo {
	private Orderdetail orderdetail;
	private OrderDetailExtend orderDetailExtend;
	public Orderdetail getOrderdetail() {
		return orderdetail;
	}
	public void setOrderdetail(Orderdetail orderdetail) {
		this.orderdetail = orderdetail;
	}
	public OrderDetailExtend getOrderDetailExtend() {
		return orderDetailExtend;
	}
	public void setOrderDetailExtend(OrderDetailExtend orderDetailExtend) {
		this.orderDetailExtend = orderDetailExtend;
	}
}
