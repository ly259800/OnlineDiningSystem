package com.ly.hotel.POextension;

import com.ly.hotel.po.Dinnertable;
//Dinnertable的包装类
public class DinnerTableVo {
	private Dinnertable dinnertable;
	private DinnerTableExtend dinnerTableExtend;
	public Dinnertable getDinnertable() {
		return dinnertable;
	}
	public void setDinnertable(Dinnertable dinnertable) {
		this.dinnertable = dinnertable;
	}
	public DinnerTableExtend getDinnerTableExtend() {
		return dinnerTableExtend;
	}
	public void setDinnerTableExtend(DinnerTableExtend dinnerTableExtend) {
		this.dinnerTableExtend = dinnerTableExtend;
	}
	
}
