package com.ly.hotel.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ly.hotel.POextension.OrderDetailExtend;
import com.ly.hotel.POextension.OrderDetailVo;
import com.ly.hotel.mapExtension.OrderDetailMapExtend;
import com.ly.hotel.mapper.OrderdetailMapper;
import com.ly.hotel.po.Orderdetail;
import com.ly.hotel.po.OrderdetailExample;
import com.ly.hotel.service.OrderDetailService;

public class OrderDetailServiceImpl implements OrderDetailService{
	@Autowired
	private OrderdetailMapper orderdetailMapper;
	@Autowired
	private OrderDetailMapExtend orderDetailMapExtend;
	@Override
	public void insertOrderDetail(OrderDetailVo orderDetailVo) {
		orderdetailMapper.insertSelective(orderDetailVo.getOrderDetailExtend());
	}

	@Override
	public List<OrderDetailExtend> selectOrderDetailByTableId(Integer orderId,Integer foodId) {
		if(orderId!=null){
			OrderdetailExample example=new OrderdetailExample();
			OrderdetailExample.Criteria criteria=example.createCriteria();
			criteria.andOrderidEqualTo(orderId);
			criteria.andFoodIdEqualTo(foodId);
			List<Orderdetail> list = orderdetailMapper.selectByExample(example);
			List<OrderDetailExtend> orderDetailExtends=new ArrayList<OrderDetailExtend>();
			for(Orderdetail orderdetail:list){
				OrderDetailExtend detailExtend=new OrderDetailExtend();
				BeanUtils.copyProperties(orderdetail, detailExtend);
				orderDetailExtends.add(detailExtend);
			}
			return orderDetailExtends;
		}
		return null;
	}

	@Override
	public List<OrderDetailExtend> selectOrderDetail(Integer orderId) {
		if(orderId!=null){
			List<OrderDetailExtend> list = orderDetailMapExtend.selectOrderDetailById(orderId);
			return list;
		}
		return null;
	}

	@Override
	public void deleteOrderDetail(Integer id) {
		if(id!=null){
			orderdetailMapper.deleteByPrimaryKey(id);
		}
	}

	@Override
	public void updateOrderDetail(Integer id,OrderDetailVo orderDetailVo) {
		if(id!=null){
			orderDetailVo.getOrderDetailExtend().setId(id);
			orderdetailMapper.updateByPrimaryKeySelective(orderDetailVo.getOrderDetailExtend());
		}
		
	}

}
