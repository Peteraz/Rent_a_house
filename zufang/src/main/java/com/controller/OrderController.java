package com.controller;  
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;  
import javax.persistence.criteria.Order;
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;  
import org.springframework.ui.Model;  
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.common.TokenUtil;
import com.entity.domain.House;
import com.entity.domain.Orders;
import com.entity.domain.User;
import com.entity.service.IHouseService;
import com.entity.service.IOrdersService;
import com.entity.service.IUserService;
import com.entity.service.impl.HouseServiceImpl;
import com.entity.service.impl.OrdersServiceImpl;
import com.google.common.base.Preconditions;
@Controller  
@RequestMapping("/order")  
public class OrderController {  
    @Resource  
    private IUserService userService;  
    @Resource
    private IHouseService houseService;
    @Resource
    private IOrdersService ordersService;
	/**
	 * 生成订单
	 */
	@RequestMapping(value = "/createOrder",method = RequestMethod.GET,produces="application/json;charset=utf-8")
	@ResponseBody
	public String register(HttpServletRequest request,ModelMap modelMap,Long houseId){ //ModelMap对象主要用于传递控制方法处理数据到结果页面，它的作用类似于request对象的setAttribute方法的作用
		try {
			if(houseId==null){
				return this.jsonPrint(204,"房源id有误，请刷新后重试", null);
			}
			House house = houseService.getHouseById((int)(long)houseId);
			if(house==null){
				return this.jsonPrint(204,"房源id有误，请刷新后重试", null);
			}
			HttpSession session = request.getSession(true);
			Object object = session.getAttribute("user");
			if(object==null){
				return this.jsonPrint(401,"您已掉线，请重新登录", null);
			}
			//验证通过
			User howner = userService.getUserById(house.getHownerid());
			howner.setPassword("");
			User tenant = (User)object;
			tenant.setPassword("");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("howner", howner);
			map.put("tenant", tenant);
			map.put("house", house);
			return this.jsonPrint(1, "获取成功", map);
		} catch (Exception e) {
			e.printStackTrace();
			return this.jsonPrint(-1,e.getMessage(), null);
		}
	}
	/**
	 * 提交訂單
	 */
	@RequestMapping(value = "/order")
	public String order(HttpServletRequest request,ModelMap modelMap,Orders orders,HttpServletResponse response){
		String basePath = request.getScheme()+"://"+request.getServerName()
				+":"+request.getServerPort()+request.getContextPath()+"/"; 			
		try {
		    orders.setOrderstatus("下單");
			orders.setOrdertime(new Date());
			ordersService.save(orders);
			HttpSession session = request.getSession(true);
			Object object = session.getAttribute("user");
			modelMap.put("orderList", ordersService.getOrdersByUId((long)((User)object).getUserid()));
			modelMap.put("user", object);
			response.sendRedirect(basePath+"order/myOrder");      //sendRedirect重定向,basePath获取路径
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "orderlist";
	}
	/**
	 * 我的订单
	 */
	@RequestMapping(value = "/myOrder")
	public String myOrder(HttpServletRequest request,ModelMap modelMap,HttpServletResponse response){
	    String basePath = request.getScheme()+"://"+request.getServerName()
	    		+":"+request.getServerPort()+request.getContextPath()+"/"; 
		try {
			HttpSession session = request.getSession(true);
			Object object = session.getAttribute("user");
			if(object==null){
				response.sendRedirect(basePath+"index?login=1");
			}
			else {
				User user = (User)object;
				if(user.getIdentity().equals("tenant")){
					System.out.println(JSONArray.toJSONString(ordersService.getOrdersByUId((long)((User)object).getUserid())));
					modelMap.put("orderList", ordersService.getOrdersByUId((long)((User)object).getUserid()));
					modelMap.put("user", object);
					modelMap.put("title", "我的訂單");
				}
				else if(user.getIdentity().equals("howner")){
					System.out.println(JSONArray.toJSONString(ordersService.getOrdersByHoId((long)((User)object).getUserid())));
					modelMap.put("orderList", ordersService.getOrdersByHoId((long)((User)object).getUserid()));
					modelMap.put("user", object);
					modelMap.put("title", "與我相關訂單");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "orderlist";
	}	
	/**
	 * 删除订单
	 */
	@RequestMapping(value = "/deleteOrder")
	@ResponseBody
	public String delete(HttpServletRequest request,ModelMap modelMap,Long id){
		try {
			if(id==null){
				return this.jsonPrint(204,"id有误，请刷新后重试", null);
			}
			HttpSession session = request.getSession(true);
			Object object = session.getAttribute("user");
			if(object==null){
				return this.jsonPrint(401,"您已掉线，请重新登录", null);
			}
			//验证通过
			ordersService.delete((int)(long)id);
			return this.jsonPrint(1, "删除成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			return this.jsonPrint(-1,e.getMessage(), null);
		}
	}
	/**
	 * 更新订单狀態
	 */
	@RequestMapping(value = "/updateOrderStatus")
	@ResponseBody
	public String updateOrderStatus(HttpServletRequest request,ModelMap modelMap,String status,Long id){
		try {
			if(StringUtils.isBlank(status)){                                //判断某字符串是否为空或长度为0或由空白符(whitespace) 构成
				return this.jsonPrint(204,"參數有误，请刷新后重试", null);  
			}
			if(id==null){
				return this.jsonPrint(204,"參數有误，请刷新后重试", null);
			}		
			HttpSession session = request.getSession(true);
			Object object = session.getAttribute("user");
			if(object==null){
				return this.jsonPrint(401,"您已掉线，请重新登录", null);
			}
			//验证通过
			Orders orders = ordersService.getOrdersById((int)(long)id);
			orders.setOrderstatus(status);
			ordersService.updateOrderStatus(orders);
			return this.jsonPrint(1, "更新成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			return this.jsonPrint(-1,e.getMessage(), null);
		}
	}
	
	/**
	 * 订单管理
	 */
	@RequestMapping(value = "/orderManagement")
	public String ordermanagement(HttpServletRequest request,ModelMap modelMap,HttpServletResponse response){
	    String basePath = request.getScheme()+"://"+request.getServerName()
	    		+":"+request.getServerPort()+request.getContextPath()+"/"; 
		try {
			HttpSession session = request.getSession(true);
			Object object = session.getAttribute("user");
			if(object==null){
				response.sendRedirect(basePath+"index?login=1");
			}
			else {			
				modelMap.put("orderList", ordersService.getOrderList());
				modelMap.put("title", "订单管理");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ordermanagement";
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
