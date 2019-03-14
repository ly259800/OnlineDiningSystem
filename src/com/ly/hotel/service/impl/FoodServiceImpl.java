package com.ly.hotel.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.ly.hotel.POextension.FoodExtend;
import com.ly.hotel.POextension.FoodVo;
import com.ly.hotel.mapExtension.FoodMapExtend;
import com.ly.hotel.mapper.FoodMapper;
import com.ly.hotel.po.Food;
import com.ly.hotel.po.FoodExample;
import com.ly.hotel.service.FoodService;

public class FoodServiceImpl implements FoodService{
	@Autowired
	private FoodMapper foodMapper;
	@Autowired
	private FoodMapExtend foodMapExtend;
	@Override
	public void insertFood(FoodVo foodVo) {
		foodMapper.insertSelective(foodVo.getFoodExtend());
	}
	@Override
	public void deleteFoodById(Integer id) {
		foodMapper.deleteByPrimaryKey(id);
	}
	@Override
	public void updateFood(Integer id, FoodVo foodVo) {
		foodVo.getFoodExtend().setId(id);
		foodMapper.updateByPrimaryKeySelective(foodVo.getFoodExtend());
	}
	@Override
	public List<FoodExtend> selectFoodList() {
		List<FoodExtend> list = foodMapExtend.selectFoodList();
		return list;
	}
	@Override
	public FoodExtend selectFoodById(Integer id) {
		Food food = foodMapper.selectByPrimaryKey(id);
		FoodExtend foodExtend=new FoodExtend();
		BeanUtils.copyProperties(food, foodExtend);
		return foodExtend;
	}

	@Override
	public List<FoodExtend> selectFoodByName(String foodName) {
		FoodExample example=new FoodExample();
		FoodExample.Criteria criteria=example.createCriteria();
		criteria.andFoodnameEqualTo(foodName);
		List<Food> list = foodMapper.selectByExample(example);
		List<FoodExtend> foodList=new ArrayList<FoodExtend>();
		for(Food food:list){
			FoodExtend foodExtend=new FoodExtend();
			BeanUtils.copyProperties(food, foodExtend);
			foodList.add(foodExtend);
		}
		return foodList;
	}

	@Override
	public List<FoodExtend> selectFoodByType(String foodType) {
		FoodExample example=new FoodExample();
		FoodExample.Criteria criteria=example.createCriteria();
		criteria.andFoodtypeEqualTo(foodType);
		List<Food> list = foodMapper.selectByExample(example);
		List<FoodExtend> foodList=new ArrayList<FoodExtend>();
		for(Food food:list){
			FoodExtend foodExtend=new FoodExtend();
			BeanUtils.copyProperties(food, foodExtend);
			foodList.add(foodExtend);
		}
		return foodList;
	}
	
}
