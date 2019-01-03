function setFloatDivCenter(e){
	_this = e
	_this.css('left',$(window).width()/2-_this.width()/2);
	_this.css('top',$(window).height()/2-_this.width()/2);
}

function showMsg(msg, time) {
	layer.msg(msg, {
		time: time
	})
}
/*切换至登陆框*/
function topToLogin()
{
	$('#register_reg').fadeOut(200);   //淡出效果
	$('#login_reg').fadeIn(200);       //淡入效果
}
/*切换至注册框*/
function topToRegister()
{
	$('#login_reg').fadeOut(200);   
	$('#register_reg').fadeIn(200);
}
function close_reg(_this)
{
	_this = $(_this);
	_this.parent().fadeOut(500);         //父页淡出
	$("#mask").fadeOut(500);
	$('html').css("overflow-y","scroll");
}
function logout(){
	$.getJSON(basePath + "user/logout", {}, function(result) {
		showMsg(eval("("+result+")").msg);
		window.location.reload();
	});
}
var Main = {
		init:function(){
			var Order;
			var House;
			var User;
		}
}
var Order = {
	_isOrder:function(id){
		$("#cover").show();
		showMsg("获取中..");
		$.getJSON("order/createOrder", {houseId:id}, function(result) {
			result = eval("("+result+")");
			showMsg(result.msg);
			if(result.status==1)
			{
				house = result.data.house;
				tenant = result.data.tenant;
				howner = result.data.howner;
				html = "<div style='text-align:center'><h2>訂單確認</h2></div><div class='row'><div class='col-md-2'></div><div class='col-md-8'>";
				html +="<h3>房源信息</h3>";
				html +="<span>房屋地址："+house.houseaddress+"</span><br>"
					 + "<span>房屋面積："+house.housearea+"平方</span><br>"
					 + "<span>房屋朝向："+house.orientation+"面</span><br>"
					 + "<span>房間數目："+house.roomnum+"</span><br>"
					 + "<span>房屋月租："+house.price+"元</span><br>";
				html +="<h3>房東信息</h3>";
				html +="<span>房東姓名："+howner.name+"</span><br>"
					 + "<span>聯係電話："+howner.phonenumber+"</span><br>"
					 + "<span>網站ID："+howner.username+"</span><br>"
				html +="<h3>租客信息</h3>";
				html +="<span>租客姓名："+tenant.name+"</span><br>"
					 + "<span>聯係電話："+tenant.phonenumber+"</span><br>"
					 + "<span>網站ID："+tenant.username+"</span>"
				html +="</div><div class='col-md-2'></div></div>"
				html +="<div style='text-align:center'>"
					 +"<form action='order/order' method='post'>" 
					 +"<input type='hidden' name='opid' value='"+tenant.userid+"'>"
					 +"<input type='hidden' name='houseid' value='"+house.houseid+"'>"
					 +"<input type='hidden' name='cpersonname' value='"+tenant.name+"'>"
					 +"<input type='hidden' name='cnumber' value='"+tenant.phonenumber+"'>"
					 +"<input type='hidden' name='hoid' value='"+howner.userid+"'>"
					 +"<input class='btn btn-lg' type='submit' value='提交訂單'/></div></form>"
				var active_box = document.createElement("div");      //创建div元素
				active_box.id = "order_reg";
				active_box.className = "_reg";
				active_box.style.width = "500px"; 
				active_box.style.height = "500px";
				active_box.style.left = "500px";
				active_box.style.top = "10%";
				active_box.style.display = "block";
				active_box.innerHTML = '<span class="close-buton" onclick="this.parentNode.parentNode.removeChild(this.parentNode);'+"$('#cover').hide()"+'"></span><label class="label-left"/>';
				active_box.innerHTML += html; 
				document.body.appendChild(active_box);    //在body尾部里面添加active_box元素
				setFloatDivCenter($("#order_reg"));       //讲id为order_reg的元素,浮动到div的中间位置                     
				active_box = null;                        //最后将active_box设置为null(空)。
					 
			}
			else if(result.status==401)
			{
				topToLogin();
				$("#cover").hide();
			}
			else
			{
				$("#cover").hide();
			}
			//window.location.reload();
		});
	},
	_updateStatus:function(_id,status,role)
	{
		if("下單"==status)
		{
			if("howner"==role)
			{
				status = "房東已確認";
			}
			else if("tenant"==role)
			{
				alert("請等待房東確認！");
				return;
			}			
		}
		else if("房東已確認"==status)
		{
			if("howner"==role)
			{
				alert("請等待租客確認！");
				return;
			}
			else if("tenant"==role)
			{
				status = "已完成";
			}	
		}
		
		if(window.confirm('你确定要更新訂單狀態為"'+status+'"吗？')){
			
			$.ajax({
				url: basePath + "order/updateOrderStatus",
				//保存数据
				dataType: "json",
				type: "POST",
				data: {
					id:_id,
					status:status
				},
				success: function(result) { //保存成功后返回的数据
					//成功后执行
					location.href=basePath + "order/myOrder";
					result = eval("("+result+")");
					alert(result.data.msg);
					
				},
				error: function(data) {
				}
			})
			}	
	},
	_isDelete:function(_id){
		if(window.confirm('你确定要删除吗？')){
			
			$.ajax({
				url: basePath + "order/deleteOrder",
				//保存数据
				dataType: "json",
				type: "POST",
				data: {
					id:_id
				},
				success: function(result) { //保存成功后返回的数据
					//成功后执行
					location.href=basePath + "order/myOrder";
					result = eval("("+result+")");
					alert(result.data.msg);
					
				},
				error: function(data) {
				}
			})
			}
         },
         _isUpdate:function(){
        	 
         },
         _isDetail:function(){
        	 $("#cover").show();
 			showMsg("获取中..",500);
 			$.getJSON("order/orderDetail", {orderId:id}, function(result) {      //$.getJSON( url [, data ] [, success(data, textStatus, jqXHR) ] )
 				result = eval("("+result+")");
 				showMsg(result.msg,500);
 				house = result.data.house; 
 				howner = result.data.howner;
 				/*tenant = result.data.tenant;*/ 
 				html = "<div style='text-align:center'><h2>房屋信息</h2></div><div class='row'><div class='col-md-2'></div><div class='col-md-8'>";
 				html +="<h3>房源信息</h3>";
 				html +="<span>房屋主人："+howner.name+"</span><br>"
 				     +"<span>房間數目："+house.roomnum+"</span><br>"
 					 + "<span>房屋朝向："+house.orientation+"面</span><br>"
 					 + "<span>房屋面積："+house.housearea+"平方</span><br>"
 					 + "<span>房屋月租："+house.price+"元</span><br>"
 					 +"<span>房屋地址："+house.houseaddress+"</span><br></div></form>"; 
 				var active_box = document.createElement("div");
 				active_box.id = "order_reg";
 				active_box.className = "_reg";
 				active_box.style.width = "500px";
 				active_box.style.height = "500px";
 				active_box.style.left = "500px";
 				active_box.style.top = "10%";
 				active_box.style.display = "block";
 				active_box.innerHTML = '<span class="close-buton" onclick="this.parentNode.parentNode.removeChild(this.parentNode);'+"$('#cover').hide()"+'"></span><label class="label-left"/>';
 				active_box.innerHTML += html; 
 				document.body.appendChild(active_box);
 				setFloatDivCenter($("#order_reg"));
 				active_box = null;

 			});
         }
         

		
	}
var House = {
		_showDetail:function(id){
			$("#cover").show();
			showMsg("获取中..",500);
			$.getJSON("house/houseDetail", {houseId:id}, function(result) {      //$.getJSON( url [, data ] [, success(data, textStatus, jqXHR) ] )
				result = eval("("+result+")");
				showMsg(result.msg,500);
				house = result.data.house; 
				howner = result.data.howner;
				/*tenant = result.data.tenant;*/ 
				html = "<div style='text-align:center'><h2>房屋信息</h2></div><div class='row'><div class='col-md-2'></div><div class='col-md-8'>";
				html +="<h3>房源信息</h3>";
				html +="<span>房屋主人："+howner.name+"</span><br>"
				     +"<span>房間數目："+house.roomnum+"</span><br>"
					 + "<span>房屋朝向："+house.orientation+"面</span><br>"
					 + "<span>房屋面積："+house.housearea+"平方</span><br>"
					 + "<span>房屋月租："+house.price+"元</span><br>"
					 +"<span>房屋地址："+house.houseaddress+"</span><br></div></form>"; 
				var active_box = document.createElement("div");
				active_box.id = "order_reg";
				active_box.className = "_reg";
				active_box.style.width = "500px";
				active_box.style.height = "500px";
				active_box.style.left = "500px";
				active_box.style.top = "10%";
				active_box.style.display = "block";
				active_box.innerHTML = '<span class="close-buton" onclick="this.parentNode.parentNode.removeChild(this.parentNode);'+"$('#cover').hide()"+'"></span><label class="label-left"/>';
				active_box.innerHTML += html; 
				document.body.appendChild(active_box);
				setFloatDivCenter($("#order_reg"));
				active_box = null;

			});
		}
}

var User={
	 _showInfo:function(id){
		 $("#cover").show();
		 showMsg("获取中..",500);
		 $.getJSON("user/userDetail", {userId:id}, function(result) {
				result = eval("("+result+")");                           //将result连接起来或加起来
				showMsg(result.msg,500);
				user = result.data.user;
				html = "<div style='text-align:center'><h2>个人信息</h2></div><div class='row'><div class='col-md-2'></div><div class='col-md-8'>";
				html +="<h3>信息</h3>";
				html +="<span>姓名："+user.name+"</span><br>"
				     + "<span>用户名："+user.username+"</span><br>"
					 + "<span>电话码："+house.phonenumber+"</span><br>"
					 + "<span>身份："+house.identity+"</span><br></div></form>";
				var active_box = document.createElement("div");
				active_box.id = "user_reg";
				active_box.className = "_reg";
				active_box.style.width = "500px";
				active_box.style.height = "500px";
				active_box.style.left = "500px";
				active_box.style.top = "10%";
				active_box.style.display = "block";
				active_box.innerHTML = '<span class="close-buton" onclick="this.parentNode.parentNode.removeChild(this.parentNode);'+"$('#cover').hide()"+'"></span><label class="label-left"/>';
				active_box.innerHTML += html; 
				document.body.appendChild(active_box);
				setFloatDivCenter($("#user_reg"));
				active_box = null;

			});
	 },
	 _changeInfo:function(id,username,name,phonenumber){
		 $.getJSON("user/userChangeinfo",{userId:id,username:username,name:name,phonenumber:phonenumber},function(result){
			 showMsg(result.msg,500);
			 location.href=basePath+"user/myInfo";
		 });
	 },	
	 _changePwd:function(id){
		 $.getJSON("user/userChangepwd",{userId:id},function(result){
			 
		 });
	 }	
}