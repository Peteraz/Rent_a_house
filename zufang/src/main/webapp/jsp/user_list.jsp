<%@ page language="java" pageEncoding="UTF-8"%>
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
    <div class="row">
    	<div class="col-md-1"></div>
    	<div class="col-md-10">
        <table id="newList" class="table table-bordered table-vertical-middle">
            <thead>
                <tr>
                    <th class="text-center td-narrow">用户名</th>
                    <th class="text-center td-narrow">姓名</th>
                    <th class="text-center">电话</th>
                    <th class="text-center">身份</th>
                    <th class="text-center operation-fixed">操作</th>
                </tr>
            </thead>
            <tbody>
              	<c:forEach var="user" items="${userList}">   
                <tr>
                	<td class="text-center td-narrow"><c:out value="${user.username}" default="wang"/></td>
                	<td class="text-center td-narrow"><c:out value="${user.name}" default="wang"/></td>
                	<td class="text-center td-narrow"><c:out value="${user.phonenumber}" default="wang"/></td>
                	<td class="text-center td-narrow"><c:out value="${user.identity}" default="wang"/></td>
                	<td class="text-center td-narrow">    
                		<a class="btn" href="javascript:House._showDetail(<c:out value='${user.userid}' default='wang'/>)">修改</a>
                		<a class="btn" href="javascript:Order._isOrder(<c:out value='${user.userid}' default='wang'/>)">删除</a> 
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
