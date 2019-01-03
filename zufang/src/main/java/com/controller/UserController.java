package com.controller;  
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
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
import com.common.TokenUtil;
import com.entity.domain.User;
import com.entity.service.IUserService;
import com.google.common.base.Preconditions;
@Controller  
@RequestMapping("/user")  
public class UserController {  
    @Resource  
    private IUserService userService;  
       
    @RequestMapping("/toLogin")  
    public String toLogin(HttpServletRequest request,Model model){  
    	return "WEB-INF/login";
    }  
    @RequestMapping("/toRegister")  
    public String toRegister(HttpServletRequest request,Model model){  
    	return "WEB-INF/register";
    }  
	/**
	 * 登录验证
	 */
	@RequestMapping(value = "/login",method = RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String login(HttpServletRequest request,ModelMap modelMap,User user,String checkCode,HttpSession httpSession){
		try {
        	request.setCharacterEncoding("UTF-8");
            HttpSession session = request.getSession(true);
			Preconditions.checkArgument(StringUtils.isNotBlank(user.getUsername()), "用户名不能为空");           //checkArgument()用来校验表达式是否为真,isNotBlank()判断某字符串是否为空或长度为0或由空白符(whitespace) 构成
			Preconditions.checkArgument(StringUtils.isNotBlank(user.getPassword()), "密码不能为空");
			Preconditions.checkArgument(StringUtils.isNotBlank(checkCode), "验证码不能为空");
			Preconditions.checkArgument(checkCode.equalsIgnoreCase((String)session.getAttribute("checkcode")), "验证码输入错误");  //equalsIgnoreCase()忽略大小写 
			user.setPassword(TokenUtil.md5(user.getPassword()));
			User findUser = userService.getUserByNameAndPw(user);
			if(findUser!=null)
			{
				session.setAttribute("user", findUser);
				//httpSession.setAttribute("user", findUser);
				return this.jsonPrint(1, "登录成功", findUser);
			}
			else 
			{
				return this.jsonPrint(0, "账号或密码错误", "");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return this.jsonPrint(-1,e.getMessage(), null);
		}
	
	}
	/**
	 * 登出验证
	 */
	@RequestMapping(value = "/logout",method = RequestMethod.GET,produces="application/json;charset=utf-8")
	@ResponseBody
	public String logout(HttpServletRequest request,ModelMap modelMap){
		try {
			HttpSession session = request.getSession(true);
			session.setAttribute("user", null);
			return this.jsonPrint(1, "已安全登出", null);
		} catch (Exception e) {
			e.printStackTrace();
			return this.jsonPrint(-1,e.getMessage(), null);
		}
	
	}
	/**
	 * 注册
	 */
	@RequestMapping(value = "/register",method = RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String register(HttpServletRequest request,ModelMap modelMap,User user,String checkCode,String rePassword ){
		try {
        	request.setCharacterEncoding("UTF-8");
            HttpSession session = request.getSession(true);
			Preconditions.checkArgument(StringUtils.isNotBlank(user.getUsername()), "用户名不能为空");
			User userTemp = new User();
			userTemp.setUsername(user.getUsername());
			Preconditions.checkArgument(userService.getUserByNameAndPw(userTemp)==null, "用户名已存在");
			Preconditions.checkArgument(StringUtils.isNotBlank(user.getName()), "姓名不能为空");
			Preconditions.checkArgument(StringUtils.isNotBlank(user.getPassword()), "密码不能为空");
			Preconditions.checkArgument(StringUtils.isNotBlank(rePassword), "请再次输入密码");
			Preconditions.checkArgument(rePassword.equals(user.getPassword()), "两次密码不一致");
			Preconditions.checkArgument(StringUtils.isNotBlank(user.getPhonenumber()), "电话号码不能为空");
			Preconditions.checkArgument(StringUtils.isNotBlank(checkCode), "验证码不能为空");
			Preconditions.checkArgument(checkCode.equalsIgnoreCase((String)session.getAttribute("checkcode")), "验证码输入错误");
			user.setPassword(TokenUtil.md5(user.getPassword()));
			userService.save(user);
			return this.jsonPrint(1, "注册成功", user);
		} catch (Exception e) {
			e.printStackTrace();
			return this.jsonPrint(-1,e.getMessage(), null);
		}
	
	}
	
	/**
	 * 我的信息
	 */
	@RequestMapping(value = "/myInfo")
	public String myInfo(HttpServletRequest request,ModelMap modelMap,HttpServletResponse response){  //ModelMap对象主要用于传递控制方法处理数据到结果页面，它的作用类似于request对象的setAttribute方法的作用
	    String basePath = request.getScheme()+"://"+request.getServerName()                            //返回当前链接使用的协议；比如，一般应用返回http;SSL返回https。getServerName()获取得域名。
	    		+":"+request.getServerPort()+request.getContextPath()+"/";                             //getServerPort()应用服务器端口。getContextPath()web项目的根路径。
		try {
			HttpSession session = request.getSession(true);                                           //若存在会话则返回该会话，否则新建一个会话。                                  
			Object object = session.getAttribute("user");                                              
			if(object==null){                                                                          //判断是否登录了
				response.sendRedirect(basePath+"index?login=1");
			}
			else {
				modelMap.put("user", object);
				modelMap.put("title", "我的信息");				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "myinfo";
	}
	
	/**
	 * 修改信息
	 */
	@RequestMapping(value = "/userChangeinfo",method = RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String userchangeInfo(HttpServletRequest request,ModelMap modelMap,Long userId,String username,String name,String phonenumber ){
		try {
        	request.setCharacterEncoding("UTF-8");
            /*HttpSession session = request.getSession(true);*/
            User user=userService.getUserById((int)(long)userId);     //强制转时还要写上原本的类型
            user.setUsername(username);
            user.setName(name);
            user.setPhonenumber(phonenumber);
            userService.save(user);                                    //保存用户
			/*Preconditions.checkArgument(rePassword.equals(user.getPassword()), "两次密码不一致");
			Preconditions.checkArgument(StringUtils.isNotBlank(checkCode), "验证码不能为空");
			Preconditions.checkArgument(checkCode.equalsIgnoreCase((String)session.getAttribute("checkcode")), "验证码输入错误");
			user.setPassword(TokenUtil.md5(user.getPassword()));*/
			return this.jsonPrint(1, "修改成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			return this.jsonPrint(-1,e.getMessage(), null);
		}	
	}
	
	/**
	 * 用户管理
	 */
	@RequestMapping(value = "/userManagement")
	public String usermanagement(HttpServletRequest request,ModelMap modelMap,HttpServletResponse response){
		String basePath = request.getScheme()+"://"+request.getServerName()                            //返回当前链接使用的协议；比如，一般应用返回http;SSL返回https。getServerName()获取得域名。
		+":"+request.getServerPort()+request.getContextPath()+"/";                             //getServerPort()应用服务器端口。getContextPath()web项目的根路径。
		try {
        	HttpSession session=request.getSession(true);                           //若存在会话则返回该会话，否则新建一个会话。
        	Object object=session.getAttribute("user");
        	if(object==null)
        	{
        		response.sendRedirect(basePath+"index?login=1");
        	}else{
        		modelMap.put("userList",userService.getUserList());
        		modelMap.put("title","用户管理");
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "usermanagement";
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
