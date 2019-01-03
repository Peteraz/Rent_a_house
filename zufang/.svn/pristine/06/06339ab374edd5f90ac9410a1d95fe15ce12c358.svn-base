<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!--引入struts标签 -->
<%
//获得本项目的地址(例如: http://localhost:8080/MyApp/)赋值给basePath
//变量
String path = request.getContextPath(); 
String basePath = request.getScheme()+"://"+request.getServerName()
+":"+request.getServerPort()+path+"/"; 
//将 "项目路径basePath" 放入pageContext中，待以后用EL表达式读出。 
pageContext.setAttribute("basePath",basePath); 
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <title>用户注册</title>
    <!-- stylesheets css -->
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" />
    <style>body{font-family:微软雅黑!important;background-color: #F1F1F1;} h2{color:black;} .text-center{text-align:center}
    body,html {
	width: 100%;
	overflow: hidden
	}
    </style></head>
  <!-- 导航栏 -->
  
  <body>
    <div class="row">
      <div class="col-md-4"></div>
      <div class="col-md-4">
        
        <section>
          <form class="bs-example bs-example-form" role="form" onsubmit="return false;" style="" id="register_form">
            <h2>用户注册</h2>
            <div>
            <div class="input-group input-group-lg">
              <span class="input-group-addon">
                <img src="${basePath}/img/user/username_icon.png" /></span>
              <input type="text" class="form-control" name="username" id="user_name" value="" placeholder="用户名" maxlength="20" title="用户名可由数字、大小写英文字母、特殊字符组成，20个字符以内" onkeyup="value=value.replace(/[\W]/g,'') "  onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" >
            </div>
            	<label style="font-weight:400;color:red;padding-left:70%" >&nbsp</label>
            </div>
            <div class="input-group input-group-lg">
              <span class="input-group-addon">
                <img src="${basePath}/img/user/nickname_icon.png" /></span>
              <input type="text" class="form-control" name="name" id="user_nickname" value="" maxlength="20" title="姓名由中文组成" placeholder="姓名">
            </div>
            <br>
            <div>
            <div class="input-group input-group-lg">
              <span class="input-group-addon">
                <img src="${basePath}/img/user/pw_icon.png" /></span>
              <input type="password" class="form-control" name="password" id="user_pw" vale="" placeholder="密码" maxlength="20" title="密码可由数字、大小写英文字母、特殊字符组成" onkeyup="value=value.replace(/[\W]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" >
            </div>
            	<label style="font-weight:400;color:red;padding-left:70%" >&nbsp</label>
            </div>
            <div>
            <div class="input-group input-group-lg">
              <span class="input-group-addon">
                <img src="${basePath}/img/user/pw_icon.png" /></span>
              <input type="password" class="form-control" name="rePassword" id="check_pw" vale="" placeholder="再次输入密码" maxlength="20" title="密码可由数字、大小写英文字母、特殊字符组成" onkeyup="value=value.replace(/[\W]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" >
            </div>
            	<label id="pw_prompt" style="font-weight:400;color:red;padding-left:70%" >&nbsp</label>
            </div>
            <div>
            <div class="input-group input-group-lg">
              <span class="input-group-addon">
                <img src="${basePath}/img/user/pw_icon.png" /></span>
              <input type="text" class="form-control" name="phonenumber" id="check_pw" vale="" placeholder="手机号码" maxlength="11" title="输入11位手机号码" onkeyup="value=value.replace(/[\W]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" >
            </div>
            	<label id="pw_prompt" style="font-weight:400;color:red;padding-left:70%" >&nbsp</label>
            </div>
            <div class="input-group input-group-lg">
  				<span class="input-group-addon">
              	<img src="${basePath}/img/user/yanzhengma_icon.png" /></span>
              	<input type="text" class="form-control" name="checkCode" id="user_vcode" vale="" placeholder="验证码" size="8" maxlength="4" title="不区分大小写" onkeyup="value=value.replace(/[\W]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" >
              	<span class="input-group-addon" style="padding: 0px 0px;">
              	<img style="height:43px" src="${basePath}/PictureCheckCode.Servlet" id="CreateCheckCode" onclick="ReloadVcode();" title="看不清？换一个">  </span>
            </div>
            <br>
            <input type="radio"  name="role" value="tenant" onclick="$('#identity').val(this.value)" checked />我是租客
            <input type="radio"  name="role" value="howner" onclick="$('#identity').val(this.value)"/>我是房东
            <input type="hidden" name="identity" id="identity" value="tenant" />
            <br>
            <br>
            <div>
              <button type="submit" class="btn btn-primary btn-lg btn-block" id="submit" style="width:100%">注 册</button></div>
            <div>
              <a href="JavaScript:;" onclick="window.parent.topToLogin()" style="float:right">已有账户，前去登录</a></div>
          </form>
        </section>
            <div style="text-align:center;display: none;padding-top:30%" id="regist_succese">
          	<h3>注册成功，正在更新页面</h3>
          	<a href="javascript:window.parent.window.location.reload();">若页面无响应，请点击此处</a>
          </div>
      </div>
      <div class="col-md-4"></div>
    </div>
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="//cdn.bootcss.com/layer/2.4/layer.min.js"></script>
    <script src="${basePath}/js/common.js"></script>
    <script>$("#submit").on("click",
      function() {
        $("#submit").text("提交中..");
        $.ajax({
          type: 'POST',
          dataType: 'json',
          url: '${basePath}/user/register',
          data: $('#register_form').serialize(),
          success: function(response, status, xhr) {
          response = eval("("+response+")");
          showMsg(response.msg,1000);
          if(response.status==1)
          {
        	  window.parent.location.reload(); 
          }
        },
        });
      })
    function setCookie(name,value,hours,path,domain,secure){
		var cdata = name + "=" + value;
    	if(hours){
    	var d = new Date();
    	d.setHours(d.getHours() + hours);
    	cdata += "; expires=" + d.toGMTString();
    	
	}
    cdata +=path ? ("; path=" + path) : "" ;
    cdata +=domain ? ("; domain=" + domain) : "" ;
    cdata +=secure ? ("; secure=" + secure) : "" ;
    document.cookie = cdata;
	}
	function getCookie(name){
	var reg = eval("/(?:^|;\\s*)" + name + "=([^=]+)(?:;|$)/"); 
    return reg.test(document.cookie) ? RegExp.$1 : "";
	}

      </script>
  </body>
</html>
<script>
//检查email邮箱
function isEmail(str){
var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
return reg.test(str);
}
function ReloadVcode() {  
    document.getElementById("CreateCheckCode").src = document  
            .getElementById("CreateCheckCode").src  
            + "?nocache=" + new Date().getTime(); 
}
</script>