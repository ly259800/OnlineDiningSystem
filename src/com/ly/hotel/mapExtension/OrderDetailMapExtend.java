package com.ly.hotel.mapExtension;

import java.util.List;

import com.ly.hotel.POextension.OrderDetailExtend;

public interface OrderDetailMapExtend {
	//通过餐桌id查询订单详细信息
	public List<OrderDetailExtend> selectOrderDetailById(Integer orderId);
	
}
