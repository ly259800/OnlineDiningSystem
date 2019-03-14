package com.ly.hotel.mapExtension;

import java.util.List;

import com.ly.hotel.POextension.DinnerTableExtend;
import com.ly.hotel.POextension.OrdersExtend;

public interface OrdersMapExtend {
	//根据餐桌id查找餐桌上的订单
	public List<OrdersExtend> findOrdersRelateTable(Integer id);
	//查找所有餐桌上的订单
	public List<OrdersExtend> findOrders();
	//餐桌上的订单和餐桌和订单详细关联查询
	public List<OrdersExtend> findOrdersRelateTableAndDetail(Integer id);
	//根据餐桌id查询餐桌关联订单,订单详细,和食品的信息
	public List<DinnerTableExtend> findFoodinTable(Integer id);
	//通过餐桌id查询订单信息
	public List<OrdersExtend> selectOrdersById(Integer tableId);
	
}
