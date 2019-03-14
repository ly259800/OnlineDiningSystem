package com.ly.hotel.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ly.hotel.POextension.DinnerTableExtend;
import com.ly.hotel.POextension.DinnerTableVo;
import com.ly.hotel.service.TableService;

@Controller
public class DinnerTableController {
	@Autowired
	private TableService tableService;
	@RequestMapping("/selectBoardList")
	public ModelAndView selectBoardList(){
		List<DinnerTableExtend> list = tableService.selectAllDinnertable();
		ModelAndView andView=new ModelAndView();
		andView.addObject("BoardList", list);
		andView.setViewName("admin/detail/boardList");
		return andView;
	}
	@RequestMapping("/selectFreeTable")
	public String selectFreeTable(Model model){
		List<DinnerTableExtend> list = tableService.selectFreeTable();
		model.addAttribute("freeTableList", list);
		return "app/index";
	}
	@RequestMapping("/Cancellation")
	public void Cancellation(HttpServletResponse response,Integer id,DinnerTableVo dinnerTableVo){
		DinnerTableExtend dinnerTableExtend = tableService.selectTableById(id);
		if(dinnerTableExtend.getTablestatus()==0){
			dinnerTableExtend.setTablestatus(1);
		}else {
			dinnerTableExtend.setTablestatus(0);
		}
		dinnerTableVo.setDinnerTableExtend(dinnerTableExtend);
		tableService.updateDinnertable(id, dinnerTableVo);
		try {
			response.sendRedirect("http://localhost:8080/HotelSystem/selectBoardList");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping("/deleteTable")
	public void deleteTable(HttpServletResponse response,Integer id){
		if(id!=null){
			tableService.deleteTableById(id);
		}
		try {
			response.sendRedirect("http://localhost:8080/HotelSystem/selectBoardList");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping("/selectTableByName")
	public ModelAndView selectTableByName(HttpServletResponse response,@RequestParam(value="keyword",defaultValue="") String tableName){
		List<DinnerTableExtend> list = tableService.selectTableByName(tableName);
		ModelAndView andView=new ModelAndView();
		andView.addObject("BoardList", list);
		andView.setViewName("admin/detail/boardList");
		return andView;
	}
	@RequestMapping("/insertTable")
	public String insertTable(@RequestParam(value="bName") String tableName,Model model){
		if(tableName==null||"".equals(tableName)){
			return "admin/detail/saveBoard";
		}
		DinnerTableVo dinnerTableVo=new DinnerTableVo();
		DinnerTableExtend dinnerTableExtend=new DinnerTableExtend();
		dinnerTableExtend.setOrderdate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		dinnerTableExtend.setTablename(tableName);
		dinnerTableExtend.setTablestatus(0);
		dinnerTableVo.setDinnerTableExtend(dinnerTableExtend);
		tableService.insertTable(dinnerTableVo);
		model.addAttribute("success", "添加成功");
		return "admin/detail/saveBoard";
	}
}
