package com.ly.hotel.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ly.hotel.POextension.FoodExtend;
import com.ly.hotel.POextension.FoodTypeExtend;
import com.ly.hotel.POextension.FoodVo;
import com.ly.hotel.service.FoodService;
import com.ly.hotel.service.FoodTypeService;

@Controller
public class FoodController {
	@Autowired
	private FoodService foodService;
	@Autowired 
	private FoodTypeService foodTypeService;
	@RequestMapping("/selectFoodList")
	public String selectFoodList(Model model,HttpServletRequest request){
		List<FoodExtend> list = foodService.selectFoodList();
		model.addAttribute("FoodList", list);
		HttpSession session=request.getSession();
		Object object = session.getAttribute("FoodTypeList");
		if(object==null){
			List<FoodTypeExtend> cuisineList = foodTypeService.selectCuisineList();
			session.setAttribute("FoodTypeList", cuisineList);
		}
		return "admin/detail/foodList";
	}
	//在需要校验的pojo前边添加@Validated,在需要添加pojo的后边添加BindingResult result接收校验出错的信息
	//注意:@Validated和BindingResult result是配对出现,并且形参顺序是固定的(一前一后).
	@RequestMapping("/insertFood")
	public String insertFood(@Validated FoodVo foodVo,BindingResult result,MultipartFile img,Model model,HttpServletRequest request){
		//获取校验错误信息
		if(result.hasErrors()){
			//获取错误信息
			List<ObjectError> allErrors = result.getAllErrors();
			for(ObjectError error:allErrors){
				//输出错误信息
				System.out.println(error.getDefaultMessage());
			}
			//将错误信息传到页面
			model.addAttribute("allErrors", allErrors);
			//出错,重新到食品的添加页面
			return "admin/detail/saveFood";
		}
		//根据菜系名查找食品类型
		List<FoodTypeExtend> list = foodTypeService.selectFoodTypeByName(foodVo.getFoodExtend().getFoodtype());
		//将食品类型id存入食物类中
		if(list.size()>0){
			foodVo.getFoodExtend().setFoodtypeId(list.get(0).getId());
		}
		//上传图片
		String originalFileName=img.getOriginalFilename();
		if(img!=null&&!("".equals(originalFileName))&&originalFileName.length()>0){
			//存储图片的物理路径
			String property = request.getSession().getServletContext().getRealPath("");
			String img_path=property+"admin\\detail\\style\\images\\";
			//新的图片名称
			String newFileName=UUID.randomUUID()+originalFileName.substring(originalFileName.lastIndexOf("."));
			//新图片
			File newFile=new File(img_path+newFileName);
			//将内存中的数据写入磁盘
			try {
				img.transferTo(newFile);
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//将新图片名称写入扩展类中
			foodVo.getFoodExtend().setImg(newFileName);
		}
		foodService.insertFood(foodVo);
		model.addAttribute("success", "添加成功");
		return "admin/detail/saveFood";
	}
	@RequestMapping("/deleteFood")
	public void deleteFood(Integer id,HttpServletResponse response){
		if(id!=null){
			foodService.deleteFoodById(id);
		}
		try {
			response.sendRedirect("http://localhost:8080/HotelSystem/selectFoodList");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping("/updateFood")
	public void updateFood(Integer id,FoodVo foodVo,MultipartFile img,Model model,HttpServletResponse response,HttpServletRequest request){
		//根据菜系名查找食品类型
		List<FoodTypeExtend> list = foodTypeService.selectFoodTypeByName(foodVo.getFoodExtend().getFoodtype());
		//将食品类型id存入食物类中
		if(list.size()>0){
			foodVo.getFoodExtend().setFoodtypeId(list.get(0).getId());
		}
		//得到修改图片时上传的图片
		String originalFileName=img.getOriginalFilename();
		if(img!=null&&!("".equals(originalFileName))&&originalFileName.length()>0){
			//存储图片的物理路径
			String property = request.getSession().getServletContext().getRealPath("");
			String pathName=property+"admin\\detail\\style\\images\\";
			//设置新文件名
			String newFileName=UUID.randomUUID()+originalFileName.substring(originalFileName.lastIndexOf("."));
			//创建新图片
			File newFile=new File(pathName+newFileName);
			//将内存中的数据写入磁盘
			try {
				img.transferTo(newFile);
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//将新图片存入扩展类中
			foodVo.getFoodExtend().setImg(newFileName);
		}
		if(id!=null){
			foodService.updateFood(id, foodVo);
		}
		model.addAttribute("success", "修改成功");
		try {
			response.sendRedirect("http://localhost:8080/HotelSystem/selectFoodById?id="+id);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping("/selectFoodByName")
	public String selectFoodByName(Model model,@RequestParam(value="foodname") String foodname){
		List<FoodExtend> list = foodService.selectFoodByName(foodname);
		model.addAttribute("FoodList", list);
		return "admin/detail/foodList";
	}
	@RequestMapping("/userSelectFoodByName")
	public String userSelectFoodByName(Model model,@RequestParam(value="foodName") String foodname){
		List<FoodExtend> list=new ArrayList<FoodExtend>();
		list = foodService.selectFoodByName(foodname);
		model.addAttribute("foodList", list);
		model.addAttribute("FoodList", list);
		return "app/detail/caidan";
	}
	@RequestMapping("/selectFoodById")
	public String selectFoodById(Integer id,Model model){
		FoodExtend foodExtend = foodService.selectFoodById(id);
		model.addAttribute("FoodExtend", foodExtend);
		return "admin/detail/updateFood";
	}
	@RequestMapping("/selectFoodDetailById")
	public String selectFoodDetailById(Integer id,Model model){
		FoodExtend foodExtend = foodService.selectFoodById(id);
		model.addAttribute("FoodExtend", foodExtend);
		return "app/detail/caixiangxi";
	} 
	@RequestMapping("/selectFoodByType")
	public String selectFoodByType(HttpSession session,Integer tableId,@RequestParam(value="typename",defaultValue="赣菜") String name,@RequestParam(value="index",defaultValue="1") Integer index,Model model){
		Object object = session.getAttribute("cuisineList");
		if(tableId!=null){
			session.setAttribute("tableId", tableId);
		}
		if(object==null){
			List<FoodTypeExtend> cuisineList = foodTypeService.selectCuisineList();
			session.setAttribute("cuisineList", cuisineList);
		}
		List<FoodExtend> list = foodService.selectFoodByType(name);
		List<FoodExtend> foodlist=new ArrayList<FoodExtend>();
		for(int i=(index-1)*6;i<index*6;i++){
			if(i<list.size()){
				foodlist.add(list.get(i));
			}else {
				break;
			}
		}
		model.addAttribute("FoodList", list);
		model.addAttribute("foodList", foodlist);
		return "app/detail/caidan";
	}
	
}
