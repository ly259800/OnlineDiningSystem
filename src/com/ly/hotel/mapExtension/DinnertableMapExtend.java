package com.ly.hotel.mapExtension;

import java.util.List;

import com.ly.hotel.POextension.DinnerTableExtend;
import com.ly.hotel.POextension.DinnerTableVo;

public interface DinnertableMapExtend {
	//查找所有的餐桌
	public List<DinnerTableExtend> selectAllDinnertable();
	//修改餐桌的状态
	public void updateDinnertable(Integer id,DinnerTableVo dinnerTableVo);
	//根据餐桌id删除餐桌
	
}
