package com.ly.hotel.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ly.hotel.POextension.FoodTypeExtend;
import com.ly.hotel.POextension.FoodTypeVo;
import com.ly.hotel.mapExtension.FoodTypeMapExtend;
import com.ly.hotel.mapper.FoodtypeMapper;
import com.ly.hotel.po.Foodtype;
import com.ly.hotel.po.FoodtypeExample;
import com.ly.hotel.service.FoodTypeService;

public class FoodTypeServiceImpl implements FoodTypeService{
	@Autowired
	private FoodTypeMapExtend foodTypeMapExtend;
	@Autowired
	private FoodtypeMapper foodtypeMapper;
	@Override
	public void insertFoodType(FoodTypeVo foodTypeVo) {
		foodtypeMapper.insertSelective(foodTypeVo.getFoodTypeExtend());
	}
	@Override
	public void deleteFoodTypeById(Integer id) {
		if(id!=null){
			foodtypeMapper.deleteByPrimaryKey(id);
		}
	}

	@Override
	public void updateFoodType(Integer id, FoodTypeVo foodTypeVo) {
		foodTypeVo.getFoodTypeExtend().setId(id);
		foodtypeMapper.updateByPrimaryKeySelective(foodTypeVo.getFoodTypeExtend());
	}
	@Override
	public List<FoodTypeExtend> selectCuisineList() {
		List<FoodTypeExtend> list = foodTypeMapExtend.selectCuisineList();
		return list;
	}

	@Override
	public FoodTypeExtend selectFoodTypeById(Integer id) {
		Foodtype foodtype = foodtypeMapper.selectByPrimaryKey(id);
		FoodTypeExtend foodTypeExtend=new FoodTypeExtend();
		BeanUtils.copyProperties(foodtype, foodTypeExtend);
		return foodTypeExtend;
	}

	@Override
	public List<FoodTypeExtend> selectFoodTypeByName(String typeName) {
		if(typeName!=null){
			FoodtypeExample example=new FoodtypeExample();
			FoodtypeExample.Criteria criteria=example.createCriteria();
			criteria.andTypenameEqualTo(typeName);
			List<Foodtype> list = foodtypeMapper.selectByExample(example);
			List<FoodTypeExtend> foodTypeList=new ArrayList<FoodTypeExtend>();
			for(Foodtype foodtype:list){
				FoodTypeExtend foodTypeExtend=new FoodTypeExtend();
				BeanUtils.copyProperties(foodtype, foodTypeExtend);
				foodTypeList.add(foodTypeExtend);
			}
			return foodTypeList;
		}
		return null;
	}
	
}
