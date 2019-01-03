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
<section class="property-query-area">
	<div class="container">
		<h1>找房么</h1>
		<div class="row">
			<form action="index">
				<div class="col-md-4 col-sm-6">
					<div class="single-query">
						<label for="keyword-input">关键字</label>
						<input type="text" id="keyword-input" placeholder="">
						<label>地区</label>
						<select onchange="$('#section').val(this.value)">
							<option value="" selected>不限</option>
							<option value="天河">天河区</option>
							<option value="白云">白云区</option>
							<option value="萝岗">萝岗区</option>
							<option value="荔湾">荔湾区</option>
						</select>
						<input type="hidden" name="section" id="section">
					</div>
					
				</div>
				<div class="col-md-4 col-sm-6">
					<div class="single-query">
						<label>房间数</label>
						<select onchange="$('#roomnum').val(this.value)">
							<option value="0" selected>不限</option>
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
						</select>
						<input type="hidden" name="roomNum" id="roomnum">
					    <label>房间面积</label>
						<select onchange="$('#minHousearea').val(this.value.split('-')[0]);$('#maxHousearea').val(this.value.split('-')[1])">
							<option value="1-10000" selected>不限</option>
							<option value="1-50"><50平方米</option>
							<option value="50-100">50-100平方米</option>
							<option value="101-200">101-200平方米</option>
							<option value="201-300">201-300平方米</option>
							<option value="300-10000">300+平方米</option>
						</select>
						<input type="hidden" name="minHousearea" id="minHousearea">
						<input type="hidden" name="maxHousearea" id="maxHousearea">
					</div>
				</div>
				<div class="col-md-4 col-sm-6">
					<div class="single-query">
						<label>最低价格</label>
						<select onchange="$('#minPrice').val(this.value)">
							<option value="1">不限</option >
							<option value="250">￥250</option>
							<option value="1000">￥1000</option>
							<option value="2000">￥2000</option>
							<option value="4000">￥4000</option>
							<option value="5000">￥5000</option>
						</select>
						<label>最高价格</label>
						<select onchange="$('#maxPrice').val(this.value)">
							<option value="100000" selected>不限</option>
							<option value="500">￥500</option>
							<option value="1000">￥1000</option>
							<option value="2000">￥2000</option>
							<option value="4000">￥4000</option>
							<option value="5000">￥5000</option>
						</select>
						<input type="hidden" name="minPrice" id="minPrice">
						<input type="hidden" name="maxPrice" id="maxPrice">
					</div>
					<div class="query-submit-button pull-right">
						<input type="submit" value="搜索">
					</div>  
				</div>
			</form>
		</div>
	</div>
</section>
</body>
</html>

