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
<!-- header开始 -->
<head>
<title>${title}</title>
<c:import url="/static/header.jsp"></c:import>
<!-- <link rel="stylesheet" href="style.css"> -->
<link rel="stylesheet" href="<%=basePath%>/css/animate.css" type="text/css" />
<link rel="stylesheet" href="<%=basePath%>/css/bootstrap.min.css" type="text/css" />
<link rel="stylesheet" href="<%=basePath%>/css/common.css" />
<link rel="stylesheet" href="<%=basePath%>/css/font-awesome.min.css" />
<link rel="stylesheet" href="<%=basePath%>/css/jquery.bxslider.css" />
<link rel="stylesheet" href="<%=basePath%>/css/meanmenu.min.css" />
<link rel="stylesheet" href="<%=basePath%>/css/normalize.css" />
<link rel="stylesheet" href="<%=basePath%>/css/owl.carousel.css" />
<link rel="stylesheet" href="<%=basePath%>/css/owl.theme.css" />
<link rel="stylesheet" href="<%=basePath%>/css/owl.transitions.css" />
<link rel="stylesheet" href="<%=basePath%>/css/responsive.css" />
<link rel="stylesheet" href="<%=basePath%>/css/style.css" />
</head> 
<!-- header结束 --> 
<!-- body start --> 
<body>
<!-- 导航栏开始 -->
<c:import url="/static/nav.jsp"></c:import>  
<!-- 导航栏结束 -->
<%-- <!-- 搜索栏开始 -->
<c:import url="/jsp/index_search.jsp"></c:import> 
<!--搜索栏结束--> --%>
<!-- 租房列表开始 -->
<c:import url="/jsp/house_list.jsp"></c:import>  
<!-- 租房列表结束 -->
<!-- 底部开始 -->
<c:import url="/static/footer.jsp"></c:import>  
<!-- 底部结束 -->
<!-- 悬浮框开始 -->
  <div id="login_reg"class="_reg" style='width:384px;height:456px;left:439px;top:250px;padding:2em;<c:if test="${login=='1'&&user==null}">display:block</c:if>'>
   <span class="close-buton" onclick="close_reg(this)"></span>
   <iframe frameborder="no" src="${basePath}/user/toLogin"></iframe>
  </div>
  <div id="register_reg" class="_reg" style=" width: 600px; height: 80%;top:10%;left:30%; padding: 2em;display: none;">
   <span class="close-buton" onclick="close_reg(this)"></span>
   <iframe style="width:100%;height:100%" frameborder="no" src="${basePath}/user/toRegister"></iframe>
  </div>
	<div class="cover" id="cover"></div>
<!-- 悬浮框结束 -->
</body>
<!-- body end  -->
</html>
<script>
var basePath = "${basePath}";
Main.init();
</script>

