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
<head>
<!-- Basic page needs 
======================================-->
<title>找房么</title>
<meta charset="utf-8">
<meta name="author" content="">
<meta name="description" content="">
<meta name="keywords" content="">

<!-- Mobile specific meat 
==============================================-->
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<!-- Favicon
============================================ -->
<link rel="shortcut icon" type="image/x-icon" href="img/favicon.ico">		
<!-- Theme main CSS File 
==========================================================-->
<link rel="stylesheet" href="style.css">

<link rel="stylesheet" href="${basePath}/css/common.css">
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

<!-- Mordernizer Js -->
<script src="js/modernizr-2.8.3.min.js"></script>
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
<script src="js/html5shiv.js"></script>
<script src="js/respond.min.js"></script>
<![endif]-->
</head>
<body>
<!-- Start Header Top area -->
<div class="header-top">
	<div class="container">
		<div class="row">
			<div class="col-md-5 col-sm-6">
				<div class="phone-mail-area">
					<ul>
					<c:if test="${user['identity']=='howner'}">
						<li>
							<a href="house/myhouse?id=${user.userid}"><i class="fa fa-home"></i>我的發佈</a>
						</li>
						<li>
							<a href="order/order?id=${user.userid}"><i class="fa fa-envelope-o"></i>與我相關</a>
						</li>
						<li>
							<a href="user/user?id=${user.userid}"><i class="fa fa-envelope-o"></i>修改資料</a>
						</li>
					</c:if>
					<c:if test="${user['identity']=='tenant'}">
						<li>
							<a href="order/order?id=${user.userid}"><i class="fa fa-phone"></i>我的訂單</a>
						</li>
						<li>
							<a href="user/user?id=${user.userid}"><i class="fa fa-envelope-o"></i>修改資料</a>
						</li>
					</c:if>
					<c:if test="${user['identity'] == 'admin'}">
						<li>
							<a href="admin/user?id=${user.userid}"><i class="fa fa-phone"></i>用戶管理</a>
						</li>
						<li>
							<a href="admin/order?id=${user.userid}"><i class="fa fa-envelope-o"></i>訂單管理</a>
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
<!-- End of Header Top area -->

<!-- Start Welcome area -->
<section>
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
<%--                 <tr>
                	<td class="text-center td-narrow">${houseList[0].houseaddress}</td>
                	<td class="text-center td-narrow">${houseList[0].orientation}</td>
                	<td class="text-center td-narrow">${houseList[0].housearea}</td>
                	<td class="text-center td-narrow">${houseList[0].price}</td>
                	<td class="text-center td-narrow">${houseList[0].roomnum}</td>
                	<td class="text-center td-narrow">2013-10-10</td>
                	<td class="text-center td-narrow">caozuo</td>
                </tr> --%>
              	<c:forEach var="house" items="${houseList}">   
                <tr>
                	<td class="text-center td-narrow"><c:out value="${house.houseaddress}" default="wang"/></td>
                	<td class="text-center td-narrow"><c:out value="${house.orientation}" default="wang"/></td>
                	<td class="text-center td-narrow"><c:out value="${house.housearea}" default="wang"/></td>
                	<td class="text-center td-narrow"><c:out value="${house.price}" default="wang"/></td>
                	<td class="text-center td-narrow"><c:out value="${house.roomnum}" default="wang"/></td>
                	<td class="text-center td-narrow"><c:out value="2013-10-10" default="wang"/></td>
                	<td class="text-center td-narrow"><c:out value="caozuo" default="wang"/></td>
                </tr>
            	</c:forEach>  
            </tbody>
        </table>
    	</div>
    	<div class="col-md-1"></div>
    </div>
</section>
<!--Footer Top Area Start-->
<section class="footer-top-area  area-pading">
	<div class="container">
			<!-- Single Footer Widget-->
			<div class="col-md-3 col-sm-4">
				<div class="single-footer-widget fix">
					<div class="widget-title">
						<h3>联络我们</h3>
					</div>
				</div>
				<div class="single-property-footer-post">
					<div class="properties-content-footer pull-left">
						<a href="#"><h3>尼古拉斯·刘贻圣</h3></a>
						<p>找房么 创始人&CEO</p>
					</div>
				</div>
				<br>
				<div class="address-wrap">
					<i class="fa fa-map-marker"></i>
					<div class="address-text">
						<h4>地址:</h4>
						<p>中国暨南大学<br>学生宿舍14栋307室<br></p>
					</div>
				</div>
				<div class="address-wrap">
					<i class="fa fa-phone"></i>
					<div class="address-text">
						<h4>手机</h4>
						<p>+86 15521380978</p>
					</div>
				</div>
				<div class="address-wrap">
					<i class="fa fa-envelope"></i>
					<div class="address-text">
						<h4>E-mail</h4>
						<p>851867807@qq.com</p>
					</div>
				</div>
			</div>
			<!-- Single Footer Widget End-->
		</div>
	</div>
</section>
<!-- Footer Top Area End Hear-->
<!-- Footer Area Start Hear-->
<footer class="footer-area">
	<div class="container">
		<div class="row">
			<div class="col-md-6 col-sm-6">
				<div class="copy-right-text">
					<p>Copyright &copy;找房么</p>
				</div>
			</div>
		</div>
	</div>
</footer>
<!-- Footer Area End Hear-->
<!-- float div start -->
  <div id="login_reg"class="_reg" style="width: 384px; height: 456px; left: 439.5px; top: 250.5px; display: none;padding:2em;">
   <span class="close-buton" onclick="close_reg(this)"></span>
   <iframe frameborder="no" src="${basePath}/user/toLogin"></iframe>
  </div>
  <div id="register_reg" class="_reg" style=" width: 600px; height: 80%;top:10%;left:30%; padding: 2em;display: none;">
   <span class="close-buton" onclick="close_reg(this)"></span>
   <iframe style="width:100%;height:100%" frameborder="no" src="${basePath}/user/toRegister"></iframe>
  </div>
<!-- float div end -->
<!-- Placed js at the end of the document so the pages load faster -->
<!-- Main jQuery file -->
<script src="js/jquery-1.11.3.min.js"></script>
<!-- Bootstrap Js -->
<script src="js/bootstrap.min.js"></script>
<!-- Owl carousel js -->
<script src="js/owl.carousel.min.js"></script>
<!-- scroll up js -->
<script src="js/jquery.scrollUp.min.js"></script>
<!-- BX Slider js -->
<script src="js/jquery.bxslider.min.js"></script>
<!-- wow js -->
<script src="js/wow.min.js"></script>
<!-- meanmenu js -->
<script src="js/jquery.meanmenu.js"></script>
<!-- Initialize WOW js for Animation-->
<script>
	new WOW().init();
</script>
<!-- Theme jQuery Codes goes hear -->
<script src="js/script.js"></script>
<script src="//cdn.bootcss.com/layer/2.4/layer.min.js"></script>
<script src="${basePath}/js/common.js"></script>
</body>
<!-- Body End  -->
</html>
<script>
</script>

