package com.ly.hotel.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ly.hotel.POextension.DinnerTableExtend;
import com.ly.hotel.POextension.OrdersExtend;
import com.ly.hotel.POextension.OrdersVo;
import com.ly.hotel.mapExtension.OrdersMapExtend;
import com.ly.hotel.mapper.OrdersMapper;
import com.ly.hotel.po.Orders;
import com.ly.hotel.po.OrdersExample;
import com.ly.hotel.service.OrdersService;

public class OrdersServiceImpl implements OrdersService{
	@Autowired
	private OrdersMapper ordersMapper;
	@Autowired
	private OrdersMapExtend ordersMapExtend;
	@Override
	public void insertOrders(OrdersVo ordersVo) {
		ordersMapper.insertSelective(ordersVo.getOrdersExtend());
	}

	@Override
	public void deleteOrders(Integer id) {
		if(id!=null){
			ordersMapper.deleteByPrimaryKey(id);
		}
	}

	@Override
	public List<DinnerTableExtend> findFoodinTable(Integer tableId) {
		List<DinnerTableExtend> tableExtends = ordersMapExtend.findFoodinTable(tableId);
		return tableExtends;
	}

	@Override
	public List<OrdersExtend> selectOrdersById(Integer tableId) {
		if(tableId!=null){
			List<OrdersExtend> ordersExtends=new ArrayList<OrdersExtend>();
			OrdersExample example=new OrdersExample();
			OrdersExample.Criteria criteria=example.createCriteria();
			criteria.andTableIdEqualTo(tableId);
			List<Orders> list = ordersMapper.selectByExample(example);
			for(Orders orders:list){
				OrdersExtend ordersExtend=new OrdersExtend();
				BeanUtils.copyProperties(orders, ordersExtend);
				ordersExtends.add(ordersExtend);
			}
			return ordersExtends;
		}
		return null;
	}

	@Override
	public List<OrdersExtend> findOrdersRelateTable(Integer tableId) {
		if(tableId!=null){
			List<OrdersExtend> list = ordersMapExtend.findOrdersRelateTable(tableId);
			return list;
		}
		return null;
	}

	@Override
	public List<OrdersExtend> findOrders() {
		List<OrdersExtend> list = ordersMapExtend.findOrders();
		return list;
	}

	@Override
	public void updateOrders(Integer id, OrdersExtend ordersExtend) {
		if(id!=null){
			ordersExtend.setId(id);
			ordersMapper.updateByPrimaryKeySelective(ordersExtend);
		}
	}
}
