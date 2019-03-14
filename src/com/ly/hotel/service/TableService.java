package com.ly.hotel.service;

import java.util.List;

import com.ly.hotel.POextension.DinnerTableExtend;
import com.ly.hotel.POextension.DinnerTableVo;

public interface TableService {
	//查找所有的餐桌
	public List<DinnerTableExtend> selectAllDinnertable();
	//修改餐桌的状态
	public void updateDinnertable(Integer id,DinnerTableVo dinnerTableVo);
	//通过id查找餐桌
	public DinnerTableExtend selectTableById(Integer id);
	//通过id删除餐桌
	public void deleteTableById(Integer id);
	//添加餐桌
	public void insertTable(DinnerTableVo dinnerTableVo);
	//通过餐桌名查找餐桌
	public List<DinnerTableExtend> selectTableByName(String tableName);
	//查找空闲餐桌
	public List<DinnerTableExtend> selectFreeTable();
}
