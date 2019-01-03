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
                    <th class="text-center td-narrow">地址</th>
                    <th class="text-center td-narrow">朝向</th>
                    <th class="text-center td-ellipsis">房間大小</th>
                    <th class="text-center">月租</th>
                    <th class="text-center">房間數目</th>
                    <th class="text-center">发布时间</th>
                    <th class="text-center operation-fixed">操作</th>
                </tr>
            </thead>
            <tbody>
              	<c:forEach var="house" items="${houseList}">   
                <tr>
                	<td class="text-center td-narrow"><c:out value="${house.houseaddress}" default="wang"/></td>
                	<td class="text-center td-narrow"><c:out value="${house.orientation}" default="wang"/></td>
                	<td class="text-center td-narrow"><c:out value="${house.housearea}" default="wang"/></td>
                	<td class="text-center td-narrow"><c:out value="${house.price}" default="wang"/></td>
                	<td class="text-center td-narrow"><c:out value="${house.roomnum}" default="wang"/></td>
                	<td class="text-center td-narrow"><c:out value="2013-10-10" default="wang"/></td>
                	<td class="text-center td-narrow">
                	<c:if test="${user['identity']=='howner' or user['identity']=='admin' or user['identity']==null}">
                		<a class="btn" href="javascript:House._showDetail(<c:out value='${house.houseid}' default='wang'/>)">查看</a>
                	</c:if>
                	<c:if test="${user['identity']=='tenant'}">
                		<a class="btn" href="javascript:House._showDetail(<c:out value='${house.houseid}' default='wang'/>)">查看</a>
                		<a class="btn" href="javascript:Order._isOrder(<c:out value='${house.houseid}' default='wang'/>)">下单</a>
                	</c:if>
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

