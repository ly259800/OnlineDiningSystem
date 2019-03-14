package com.ly.hotel.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ly.hotel.POextension.DinnerTableExtend;
import com.ly.hotel.POextension.DinnerTableVo;
import com.ly.hotel.mapExtension.DinnertableMapExtend;
import com.ly.hotel.mapper.DinnertableMapper;
import com.ly.hotel.po.Dinnertable;
import com.ly.hotel.po.DinnertableExample;
import com.ly.hotel.service.TableService;

public class TableServiceImpl implements TableService{
	@Autowired
	private DinnertableMapExtend tableMapExtend;
	@Autowired
	private DinnertableMapper dinnertableMapper;
	@Override
	public List<DinnerTableExtend> selectAllDinnertable() {
		return tableMapExtend.selectAllDinnertable();
		/*DinnertableExample example=new DinnertableExample();
		DinnertableExample.Criteria criteria=example.createCriteria();
		criteria.getAllCriteria();
		List<Dinnertable> list = dinnertableMapper.selectByExample(example);
		*/
	}
	@Override
	public void updateDinnertable(Integer id, DinnerTableVo dinnerTableVo) {
		dinnerTableVo.getDinnerTableExtend().setId(id);
		String string = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		dinnerTableVo.getDinnerTableExtend().setOrderdate(string);
		dinnertableMapper.updateByPrimaryKeySelective(dinnerTableVo.getDinnerTableExtend());
	}
	@Override
	public DinnerTableExtend selectTableById(Integer id) {
		Dinnertable dinnertable = dinnertableMapper.selectByPrimaryKey(id);
		DinnerTableExtend dinnerTableExtend=new DinnerTableExtend();
		BeanUtils.copyProperties(dinnertable, dinnerTableExtend);
		return dinnerTableExtend;
	}
	@Override
	public void deleteTableById(Integer id) {
		dinnertableMapper.deleteByPrimaryKey(id);
	}
	@Override
	public void insertTable(DinnerTableVo dinnerTableVo) {
		dinnertableMapper.insertSelective(dinnerTableVo.getDinnerTableExtend());
	}
	@Override
	public List<DinnerTableExtend> selectTableByName(String tableName) {
		List<DinnerTableExtend> tableList=new ArrayList<DinnerTableExtend>();
		if(tableName==null||"".equals(tableName)){
			/*DinnerTableExtend dinnerTableExtend=new DinnerTableExtend();
			tableList.add(dinnerTableExtend);*/
			return null;
		}
		DinnertableExample example=new DinnertableExample();
		DinnertableExample.Criteria criteria=example.createCriteria();
		criteria.andTablenameEqualTo(tableName);
		List<Dinnertable> list = dinnertableMapper.selectByExample(example);
		
		for(Dinnertable table:list){
			DinnerTableExtend dinnerTableExtend=new DinnerTableExtend();
			BeanUtils.copyProperties(table, dinnerTableExtend);
			tableList.add(dinnerTableExtend);
		}
		return tableList;
	}
	@Override
	public List<DinnerTableExtend> selectFreeTable() {
		DinnertableExample example=new DinnertableExample();
		DinnertableExample.Criteria criteria=example.createCriteria();
		criteria.andTablestatusEqualTo(0);
		List<Dinnertable> list = dinnertableMapper.selectByExample(example);
		List<DinnerTableExtend> tableList=new ArrayList<>();
		for(Dinnertable dinnertable:list){
			DinnerTableExtend dinnerTableExtend=new DinnerTableExtend();
			BeanUtils.copyProperties(dinnertable, dinnerTableExtend);
			tableList.add(dinnerTableExtend);
		}
		return tableList;
	}
}
