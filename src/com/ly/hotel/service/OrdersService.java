package com.ly.hotel.service;

import java.util.List;

import com.ly.hotel.POextension.DinnerTableExtend;
import com.ly.hotel.POextension.OrdersExtend;
import com.ly.hotel.POextension.OrdersVo;

public interface OrdersService {
	//添加订单
	public void insertOrders(OrdersVo ordersVo);
	//根据餐桌id删除订单
	public void deleteOrders(Integer id);
	//通过订单id修改订单
	public void updateOrders(Integer id,OrdersExtend ordersExtend);
	//根据餐桌id查询餐桌关联订单,订单详细,和食品的信息
	public List<DinnerTableExtend> findFoodinTable(Integer tableId);
	//根据餐桌id查找餐桌上的订单
	public List<OrdersExtend> findOrdersRelateTable(Integer tableId);
	//查找所有餐桌上的订单
	public List<OrdersExtend> findOrders();
	//通过餐桌id查询订单信息
	public List<OrdersExtend> selectOrdersById(Integer tableId);
	
}
