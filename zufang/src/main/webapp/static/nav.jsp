﻿<%@ page language="java" pageEncoding="UTF-8"%>
 <%@page import="com.entity.domain.User"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% 
String path = request.getContextPath(); 
//变量 
String basePath = request.getScheme()+"://"+request.getServerName()
+":"+request.getServerPort()+path+"/"; 
// 将 "项目路径basePath" 放入pageContext中，待以后用EL表达式读出。 
pageContext.setAttribute("basePath",basePath); 
%>  
<html lang="en">
<body>
<div class="header-top">
	<div class="container">
		<div class="row">
			<div class="col-md-5 col-sm-6">
				<div class="phone-mail-area">
					<ul>
						<li>
							<a href="${basePath}/index" style="font-size:1.2em">找房麽</a>
						</li>
					<c:if test="${user.identity=='howner'}">
						<li>
							<a href="${basePath}/house/myHouse"><i class="fa fa-home"></i>我的發佈</a>
						</li>
						<li>
							<a href="${basePath}/order/myOrder"><i class="fa fa-shopping-cart"></i>與我相關</a>
						</li>
						<li>
							<a href="${basePath}/user/myInfo"><i class="fa fa-user"></i>修改資料</a>
						</li>
					</c:if>
					<c:if test="${user['identity'] == 'tenant'}">
						<li>
							<a href="${basePath}/order/myOrder"><i class="fa fa-shopping-cart"></i>我的訂單</a>
						</li>
						<li>
							<a href="${basePath}/user/myInfo"><i class="fa fa-envelope-o"></i>修改資料</a>
						</li>
					</c:if>
					<c:if test="${user['identity'] == 'admin'}">
						<li>
							<a href="${basePath}/user/userManagement"><i class="fa fa-home"></i>用戶管理</a>
						</li>
						<li>
							<a href="${basePath}/order/orderManagement"><i class="fa fa-shopping-cart"></i>訂單管理</a>
						</li>
						<li>
							<a href="${basePath}/house/houseManagement"><i class="fa fa-home"></i>房源管理</a>
						</li>
					</c:if>
					</ul>
				</div>
			</div>
			<div class="col-md-7 col-sm-6">
				<div class="login-bookmark-area">
				   <div class="register-login">
				   	<c:if test="${empty user.name}"> 
						<a href="javascript:topToLogin();"><i class="fa fa-user"></i>登陆</a>
						<a href="javascript:topToRegister()"><i class="fa fa-sign-in"></i>注册</a>
				   	</c:if>
				   	<c:if test="${not empty user.name}"> 
						<a href="javascript:void(0)"><i class="fa fa-user"></i>欢迎您  ${user.name}</a>
						<a href="javascript:logout()"><i class="fa fa-sign-in"></i>登出</a>
				   	</c:if>
				   </div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>

