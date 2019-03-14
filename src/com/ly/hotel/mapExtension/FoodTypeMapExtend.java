package com.ly.hotel.mapExtension;

import java.util.List;

import com.ly.hotel.POextension.FoodTypeExtend;

public interface FoodTypeMapExtend {
	//查找食品类型清单
	public List<FoodTypeExtend> selectCuisineList();
}
