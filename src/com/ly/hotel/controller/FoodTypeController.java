package com.ly.hotel.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ly.hotel.POextension.FoodTypeExtend;
import com.ly.hotel.POextension.FoodTypeVo;
import com.ly.hotel.service.FoodTypeService;

@Controller
public class FoodTypeController {
	@Autowired
	private FoodTypeService foodTypeService;
	@RequestMapping("/selectCuisineList")
	public String selectCuisineList(Model model){
		List<FoodTypeExtend> list = foodTypeService.selectCuisineList();
		model.addAttribute("CuisineList", list);
		return "admin/detail/cuisineList";
	}
	@RequestMapping("/insertFoodType")
	public String insertFoodType(Model model,FoodTypeVo foodTypeVo){
		foodTypeService.insertFoodType(foodTypeVo);
		model.addAttribute("success", "添加成功");
		return "admin/detail/saveCuisine";
	}
	@RequestMapping("/updateFoodType")
	public String updateFoodType(Model model,Integer id,FoodTypeVo foodTypeVo){
		foodTypeService.updateFoodType(id, foodTypeVo);
		model.addAttribute("success","修改成功");
		return "admin/detail/updateCuisine";
	}
	@RequestMapping("/deleteFoodTypeById")
	public void deleteFoodTypeById(Integer id,HttpServletResponse response){
		if(id!=null){
			foodTypeService.deleteFoodTypeById(id);
		}
		try {
			response.sendRedirect("http://localhost:8080/HotelSystem/selectCuisineList");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping("/selectFoodTypeByName")
	public String selectFoodTypeByName(String typename,Model model){
		List<FoodTypeExtend> list = foodTypeService.selectFoodTypeByName(typename);
		model.addAttribute("CuisineList", list);
		return "admin/detail/cuisineList";
	}
	@RequestMapping("/selectFoodTypeById")
	public String selectFoodTypeById(Model model,Integer id){
		if(id!=null){
			FoodTypeExtend foodTypeExtend = foodTypeService.selectFoodTypeById(id);
			model.addAttribute("FoodTypeExtend", foodTypeExtend);
		}
		return "admin/detail/updateCuisine";
	}
}
