package com.ly.hotel.service;

import java.util.List;

import com.ly.hotel.POextension.OrderDetailExtend;
import com.ly.hotel.POextension.OrderDetailVo;

public interface OrderDetailService {
	//添加食物到餐车
	public void insertOrderDetail(OrderDetailVo orderDetailVo);
	//根据id删除餐车信息
	public void deleteOrderDetail(Integer id);
	//根据foodCount修改餐车信息
	public void updateOrderDetail(Integer id,OrderDetailVo orderDetailVo);
	//根据订单id查询餐车
	public List<OrderDetailExtend> selectOrderDetailByTableId(Integer orderId,Integer foodId);
	//根据订单id查询餐车
	public List<OrderDetailExtend> selectOrderDetail(Integer orderId);
}
