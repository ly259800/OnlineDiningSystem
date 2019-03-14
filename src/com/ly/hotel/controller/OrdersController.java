package com.ly.hotel.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ly.hotel.POextension.DinnerTableExtend;
import com.ly.hotel.POextension.OrderDetailExtend;
import com.ly.hotel.POextension.OrdersExtend;
import com.ly.hotel.POextension.OrdersVo;
import com.ly.hotel.service.OrderDetailService;
import com.ly.hotel.service.OrdersService;

@Controller
public class OrdersController {
	@Autowired
	private OrdersService ordersService;
	@Autowired
	private OrderDetailService orderDetailService;
	@RequestMapping("/insertOrders")
	public void insertOrders(HttpSession session,OrdersVo ordersVo){
		Integer tableId=(Integer) session.getAttribute("tableId");
		if(tableId!=null){
			List<DinnerTableExtend> DinnerTableExtends = ordersService.findFoodinTable(tableId);
			if(DinnerTableExtends==null){
				ordersService.insertOrders(ordersVo);
			}
		}
	}
	@RequestMapping("/selectOrdersList")
	public String selectOrdersList(Model model){
		List<OrdersExtend> ordersList = ordersService.findOrders();
		model.addAttribute("ordersList", ordersList);
		return "admin/detail/orderList";
	}
	@RequestMapping("/selectOrderDetails")
	public String selectOrderDetails(Model model,@RequestParam(value="id")Integer orderId){
		if(orderId!=null){
			List<OrderDetailExtend> list = orderDetailService.selectOrderDetail(orderId);
			model.addAttribute("OrderDetails", list);
		}
		return "admin/detail/orderDetail";
	}
	@RequestMapping("/appCheckOut")
	public String appCheckOut(Double countNum,Integer tableId){
		List<OrdersExtend> ordersList = ordersService.selectOrdersById(tableId);
		OrdersExtend ordersExtend = ordersList.get(0);
		ordersExtend.setTotalprice(countNum);
		ordersExtend.setOrderdate(new Date());
		ordersService.updateOrders(ordersExtend.getId(), ordersExtend);
		return "app/detail/jiezhang";
	}
	@RequestMapping("/adminCheckOut")
	public String adminCheckOut(Model model,@RequestParam(value="tableId")Integer tableId){
		if(tableId!=null){
			List<OrdersExtend> ordersList = ordersService.selectOrdersById(tableId);
			OrdersExtend ordersExtend = ordersList.get(0);
			ordersExtend.setOrderstatus(1);
			ordersService.updateOrders(ordersExtend.getId(), ordersExtend);
		}
		List<OrdersExtend> ordersList = ordersService.findOrders();
		model.addAttribute("ordersList", ordersList);
		return "admin/detail/orderList";
	}
}
