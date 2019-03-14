package com.ly.hotel.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ly.hotel.POextension.OrderDetailExtend;
import com.ly.hotel.POextension.OrderDetailVo;
import com.ly.hotel.POextension.OrdersExtend;
import com.ly.hotel.POextension.OrdersVo;
import com.ly.hotel.service.OrderDetailService;
import com.ly.hotel.service.OrdersService;

@Controller
public class OrderDetailController {
	@Autowired
	private OrderDetailService orderDetailService;
	@Autowired
	private OrdersService ordersService;
	@RequestMapping("/insertOrderDetail")
	public String insertOrderDetail(Model model,HttpSession session,OrderDetailVo orderDetailVo){
		Integer tableId=(Integer) session.getAttribute("tableId");
		if(tableId!=null){
			//查询该餐桌上的所有订单
			List<OrdersExtend> ordersList = ordersService.findOrdersRelateTable(tableId);
			//该餐桌上有订单
			if(ordersList.size()>0){
				//根据该订单查找订单详细
				List<OrderDetailExtend> orderDetailList = orderDetailService.selectOrderDetail(ordersList.get(0).getId());
				//该订单有订单详细
				if(orderDetailList.size()>0){
					//该订单详细上有食品,查询该订单详细上是否有要添加的食品
					List<OrderDetailExtend> orderDetailExtend = orderDetailService.selectOrderDetailByTableId(ordersList.get(0).getId(), orderDetailVo.getOrderDetailExtend().getFoodId());
					if(orderDetailExtend.size()>0){
						//有该指定的食品
						Integer id=orderDetailExtend.get(0).getFoodcount();
						id++;
						orderDetailExtend.get(0).setFoodcount(id);
						//将食品数量修改之后新的订单详细进行更新
						OrderDetailVo vo=new OrderDetailVo();
						vo.setOrderDetailExtend(orderDetailExtend.get(0));
						orderDetailService.updateOrderDetail(orderDetailExtend.get(0).getId(), vo);
						List<OrderDetailExtend> orderDetails = orderDetailService.selectOrderDetail(ordersList.get(0).getId());
						model.addAttribute("OrderDetails", orderDetails);
						return "app/detail/clientCart";
					}
					//该订单详细上没有该食品，添加该食品到订单详细
					orderDetailVo.getOrderDetailExtend().setOrderid(ordersList.get(0).getId());
					orderDetailVo.getOrderDetailExtend().setFoodcount(1);
					orderDetailService.insertOrderDetail(orderDetailVo);
					List<OrderDetailExtend> orderDetails = orderDetailService.selectOrderDetail(ordersList.get(0).getId());
					model.addAttribute("OrderDetails", orderDetails);
					return "app/detail/clientCart";
				}
				//该订单没有订单详细,添加一个订单详细
				orderDetailVo.getOrderDetailExtend().setOrderid(ordersList.get(0).getId());
				orderDetailVo.getOrderDetailExtend().setFoodcount(1);
				orderDetailService.insertOrderDetail(orderDetailVo);
				List<OrderDetailExtend> orderDetails = orderDetailService.selectOrderDetail(ordersList.get(0).getId());
				model.addAttribute("OrderDetails", orderDetails);
				return "app/detail/clientCart";
			}
			//该餐桌上没有订单,添加一个订单
			OrdersVo ordersVo=new OrdersVo();
			OrdersExtend ordersExtend=new OrdersExtend();
			ordersExtend.setTableId(tableId);
			ordersVo.setOrdersExtend(ordersExtend);
			ordersService.insertOrders(ordersVo);
			//添加订单之后查询该订单的id
			List<OrdersExtend> list = ordersService.selectOrdersById(tableId);
			if(list.size()>0){
				//在该订单找到后,添加一个订单详细
				orderDetailVo.getOrderDetailExtend().setOrderid(list.get(0).getId());
				orderDetailVo.getOrderDetailExtend().setFoodcount(1);
				orderDetailService.insertOrderDetail(orderDetailVo); 
				List<OrderDetailExtend> orderDetails = orderDetailService.selectOrderDetail(list.get(0).getId());
				model.addAttribute("OrderDetails", orderDetails);
			}
		}
		return "app/detail/clientCart";
	}
	//根据id删除订单详情
	@RequestMapping("/deleteOrderDetail")
	public String deleteOrderDetail(Model model,Integer id,Integer orderId){
		if(id!=null){
			orderDetailService.deleteOrderDetail(id);
		}
		if(orderId!=null){
			List<OrderDetailExtend> orderDetails = orderDetailService.selectOrderDetail(orderId);
			model.addAttribute("OrderDetails", orderDetails);
		}
		return "app/detail/clientCart";
	}
	//根据id修改订单详情
	@RequestMapping("/updateOrderDetail")
	public String updateOrderDetail(Model model,Integer id,Integer orderId,@RequestParam(value="foodcount") String foodcount){
		OrderDetailVo orderDetailVo=new OrderDetailVo();
		OrderDetailExtend detailExtend=new OrderDetailExtend();
		detailExtend.setFoodcount(Integer.parseInt(foodcount));
		orderDetailVo.setOrderDetailExtend(detailExtend);
		orderDetailService.updateOrderDetail(id, orderDetailVo);
		if(orderId!=null){
			List<OrderDetailExtend> orderDetails = orderDetailService.selectOrderDetail(orderId);
			model.addAttribute("OrderDetails", orderDetails);
		}
		return "app/detail/clientCart";
	}
	//查询所有订单详细
	@RequestMapping("/selectAllOrderDetail")
	public String selectAllOrderDetail(Integer tableId,Model model){
		List<OrderDetailExtend> orderDetailList=new ArrayList<OrderDetailExtend>();
		if(tableId!=null){
			List<OrdersExtend> ordersList = ordersService.findOrdersRelateTable(tableId);
			//该餐桌上有订单
			if(ordersList.size()>0){
				List<OrderDetailExtend> orderDetails = orderDetailService.selectOrderDetail(ordersList.get(0).getId());
				//该订单有订单详细
				if(orderDetails.size()>0){
					model.addAttribute("OrderDetails", orderDetails);
					return "app/detail/clientOrderList";
				}
			}
		}
		model.addAttribute("OrderDetails", orderDetailList);
		return "app/detail/clientOrderList";
	}
}
