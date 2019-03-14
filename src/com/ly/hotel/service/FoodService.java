package com.ly.hotel.service;

import java.util.List;

import com.ly.hotel.POextension.FoodExtend;
import com.ly.hotel.POextension.FoodVo;

public interface FoodService {
	//增加食物
	public void insertFood(FoodVo foodVo);
	//删除食物
	public void deleteFoodById(Integer id);
	//更新食物
	public void updateFood(Integer id,FoodVo foodVo);
	//查找食品清单
	public List<FoodExtend> selectFoodList();
	//通过id查找食物
	public FoodExtend selectFoodById(Integer id);
	//通过食物名查找食物
	public List<FoodExtend> selectFoodByName(String foodName);
	//通过食物类型查找食物
	public List<FoodExtend> selectFoodByType(String foodType);
}
