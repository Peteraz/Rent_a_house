﻿<%@ page language="java" pageEncoding="UTF-8"%>
 <%@page import="com.entity.domain.User"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% 
String path = "/"+request.getContextPath(); 
//变量 
String basePath = request.getScheme()+"://"+request.getServerName()
+":"+request.getServerPort()+path+"/"; 
// 将 "项目路径basePath" 放入pageContext中，待以后用EL表达式读出。 
pageContext.setAttribute("basePath",basePath); 
%>  
<html lang="en">
<head>
<title>order</title>
<link rel="stylesheet" href="css/animate.css" />
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/common.css" />
<link rel="stylesheet" href="css/font-awesome.min.css" />
<link rel="stylesheet" href="css/jquery.bxslider.css" />
<link rel="stylesheet" href="css/meanmenu.min.css" />
<link rel="stylesheet" href="css/normalize.css" />
<link rel="stylesheet" href="css/owl.carousel.css" />
<link rel="stylesheet" href="css/owl.theme.css" />
<link rel="stylesheet" href="css/owl.transitions.css" />
<link rel="stylesheet" href="css/responsive.css" />
<link rel="stylesheet" href="css/style.css" />
</head>
<body>
    <div class="row">
    	<div class="col-md-1"></div>
    	<div class="col-md-10">
        <table id="newList" class="table table-bordered table-vertical-middle">
            <thead>
                <tr>
                    <th class="text-center td-narrow">地址</th>
                    <th class="text-center td-ellipsis">房間大小</th>
                    <th class="text-center">月租</th>
                    <th class="text-center">訂單狀態</th>
                    <th class="text-center">下單时间</th>
                    <th class="text-center">房東</th>
                    <th class="text-center">房東電話</th>
                    <th class="text-center operation-fixed">操作</th>
                </tr>
            </thead>
            <tbody>
              	<c:forEach var="order" items="${orderList}">   
                <tr>
                	<td class="text-center td-narrow"><c:out value="${order.address}" default="wang"/></td>
                	<td class="text-center td-narrow"><c:out value="${order.hArea}" default="wang"/></td>
                	<td class="text-center td-narrow"><c:out value="${order.hPrice}" default="wang"/></td>
                	<td class="text-center td-narrow"><c:out value="${order.orderStatus}" default="wang"/></td>
                	<td class="text-center td-narrow"><c:out value="${order.orderTime}" default="wang"/></td>
                	<td class="text-center td-narrow"><c:out value="${order.hName}" default="wang"/></td>
                	<td class="text-center td-narrow"><c:out value="${order.hPhonenumber}" default="wang"/></td>
                	<td class="text-center td-narrow">
					<c:if test="${user.identity=='howner'}">
                		<a class="btn" href="javascript:void()">詳情</a>
                		<a class="btn" href="javascript:Order._updateStatus(<c:out value='${order.orderId}' default='wang'/>,'<c:out value='${order.orderStatus}' default='wang'/>','${user.identity}')">更新</a>
					</c:if>
					<c:if test="${user['identity'] == 'tenant'}">
                		<a class="btn" href="javascript:void()">詳情</a>
                		<a class="btn" href="javascript:Order._isDelete(<c:out value='${order.orderId}' default='wang'/>)">刪除</a>
                		<a class="btn" href="javascript:Order._updateStatus(<c:out value='${order.orderId}' default='wang'/>,'<c:out value='${order.orderStatus}' default='wang'/>','${user.identity}')">更新</a>
					</c:if>
					<c:if test="${user['identity'] == 'admin'}">
                		<a class="btn" href="javascript:void()">詳情</a>
                		<a class="btn" href="javascript:Order._isDelete(<c:out value='${order.orderId}' default='wang'/>)">刪除</a>
                		<a class="btn" href="javascript:Order._updateStatus(<c:out value='${order.orderId}' default='wang'/>,'<c:out value='${order.orderStatus}' default='wang'/>','${user.identity}')">更新</a>
					</c:if>
                	
                	
                	

                    <%-- <c:if test="${user['identity']=='howner' or user['identity']=='admin' or user['identity']==null}">
                		<a class="btn" href="javascript:House._showDetail(<c:out value='${house.houseid}' default='wang'/>)">查看</a>
                	</c:if>
                	<c:if test="${user['identity']=='tenant'}">
                		<a class="btn" href="javascript:House._showDetail(<c:out value='${house.houseid}' default='wang'/>)">查看</a>
                		<a class="btn" href="javascript:Order._isOrder(<c:out value='${house.houseid}' default='wang'/>)">下单</a>
                	</c:if> --%>
                	</td>
                </tr>
            	</c:forEach>  
            </tbody>
        </table>
    	</div>
    	<div class="col-md-1"></div>
    </div>
</body>
</html>

