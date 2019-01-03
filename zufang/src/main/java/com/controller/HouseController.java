package com.controller;  
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;  
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.entity.domain.House;
import com.entity.domain.User;
import com.entity.domain.dto.HouseDto;
import com.entity.service.IHouseService;
import com.entity.service.IUserService;
@Controller  
@RequestMapping("/house")  
public class HouseController {  
    @Resource
    private IUserService userService;
    @Resource
    private IHouseService houseService;
	/**
	 * 获取房源详情
	 */
	@RequestMapping(value = "/houseDetail",method = RequestMethod.GET,produces="application/json;charset=utf-8")  //映射请求,是一个用来处理请求地址映射
	@ResponseBody   //注解表示该方法的返回的结果直接写入 HTTP 响应正文，不解析，直接作为页面body的内容
	public String register(HttpServletRequest request,ModelMap modelMap,Long houseId){
		try {
			if(houseId==null){
				return this.jsonPrint(204,"房源id有误，请刷新后重试", null);
			}
			House house = houseService.getHouseById((int)(long)houseId);
			if(house==null){
				return this.jsonPrint(204,"房源id有误，请刷新后重试", null);
			}
			//验证通过
			User howner=userService.getUserById(house.getHownerid());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("house", house);
			map.put("howner",howner);
			return this.jsonPrint(1, "获取成功", map);
		} catch (Exception e) {
			e.printStackTrace();
			return this.jsonPrint(-1,e.getMessage(), null);
		}
	}
	/**
	 * 我的發佈
	 */
	@RequestMapping(value = "/myHouse")
	public String myHouse(HttpServletRequest request,ModelMap modelMap,HttpServletResponse response){  //ModelMap对象主要用于传递控制方法处理数据到结果页面，它的作用类似于request对象的setAttribute方法的作用
	    String basePath = request.getScheme()+"://"+request.getServerName()                            //返回当前链接使用的协议；比如，一般应用返回http;SSL返回https。getServerName()获取得域名。
	    		+":"+request.getServerPort()+request.getContextPath()+"/";                             //getServerPort()应用服务器端口。getContextPath()web项目的根路径。
		try {
			HttpSession session = request.getSession(true);                                           //若存在会话则返回该会话，否则新建一个会话。                                  
			Object object = session.getAttribute("user");
			if(object==null){
				response.sendRedirect(basePath+"index?login=1");                                      //重定向到login页面
			}
			else {
				User user = (User)object;
				modelMap.put("houseList", houseService.getHouseByHoId(user.getUserid()));
				modelMap.put("user", object);
				modelMap.put("title", "我的發佈");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "houselist";
	}	
	
	/**
	 * 房源管理
	 */
	@RequestMapping(value = "/houseManagement")
	public String housemanagement(HttpServletRequest request,ModelMap modelMap,HttpServletResponse response){  //ModelMap对象主要用于传递控制方法处理数据到结果页面，它的作用类似于request对象的setAttribute方法的作用
	    String basePath = request.getScheme()+"://"+request.getServerName()                            //返回当前链接使用的协议；比如，一般应用返回http;SSL返回https。getServerName()获取得域名。
	    		+":"+request.getServerPort()+request.getContextPath()+"/";                             //getServerPort()应用服务器端口。getContextPath()web项目的根路径。
		try {
			HttpSession session = request.getSession(true);                                           //若存在会话则返回该会话，否则新建一个会话。                                  
			Object object = session.getAttribute("user");
			if(object==null){
				response.sendRedirect(basePath+"index?login=1");                                      //重定向到login页面
			}
			else {
				modelMap.put("houseList", houseService.getAll());
				modelMap.put("title", "房源管理");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "housemanagement";
	}
	
	protected String jsonPrint(Integer status,String msg,Object data){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", status);
		map.put("msg", msg);
		map.put("data", data);
		System.out.println(JSONArray.toJSONString(map));
		return JSONArray.toJSONString(map);
	}
}  
