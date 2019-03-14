package com.ly.hotel.service;

import java.util.List;

import com.ly.hotel.POextension.FoodTypeExtend;
import com.ly.hotel.POextension.FoodTypeVo;

public interface FoodTypeService {
	//增加食品类型
	public void insertFoodType(FoodTypeVo foodTypeVo);
	//删除食物类型
	public void deleteFoodTypeById(Integer id);
	//更新食物类型
	public void updateFoodType(Integer id,FoodTypeVo foodTypeVo);
	//查找食品类型清单
	public List<FoodTypeExtend> selectCuisineList();
	//通过id查找食物类型
	public FoodTypeExtend selectFoodTypeById(Integer id);
	//通过菜系名查找食物类型
	public List<FoodTypeExtend> selectFoodTypeByName(String typeName);
}
