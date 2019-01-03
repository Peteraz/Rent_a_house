<%@ page language="java" pageEncoding="UTF-8"%>
 <%@page import="com.entity.domain.User"%>   
 <%@ page isELIgnored="false" %> 
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
                    <th class="text-center td-narrow" >个人资料</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                	<td class="text-center td-narrow">用户名：<input type="text" id="txt_username" placeholder="<c:out value="${user.username}" default='wang'/>"></td>         	                	                	                	
                </tr>
                <tr>
                    <td class="text-center td-narrow">姓名：<input type="text" id="txt_name" placeholder="<c:out value="${user.name}" default='wang'/>"></td>
                </tr>
                <tr>
                    <td class="text-center td-narrow" >电话：<input type="text" id="txt_phonenumber"placeholder="<c:out value="${user.phonenumber}" default='wang'/>"></td>
                </tr>
                <tr>
                	<td class="text-center td-narrow" >身份：<c:out value="${user.identity}" default="wang"/></td>
                </tr>
                <tr>
                	<td class="text-center td-narrow"  >
                	<a class="btn"  onclick='User._changeInfo(&quot;"+$("txt_username").val()+"&quot; )' >修改</a>
                	
                	</td>
                </tr> 
            </tbody>
        </table>
        <table id="newList2" class="table table-bordered table-vertical-middle">  
            <thead> 
                <tr>
                    <th class="text-center td-narrow" >密码</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                	<td class="text-center td-narrow">新密码：<input type="text"/></td>    	               	                	                	
                </tr>
                <tr>
                    <td class="text-center td-narrow">再次输入：<input type="text"/></td>
                </tr>
                <tr>
                	<td class="text-center td-narrow">
                        <input type="text" name="checkCode" id="checkCode" vale="" placeholder="验证码" size="16" maxlength="4" title="不区分大小写" onkeyup="value=value.replace(/[\W]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" onblur="">   
              		  	<img style="height:43px" src="${basePath}/PictureCheckCode.Servlet" id="CreateCheckCode" onclick="ReloadVcode();" title="看不清？换一个"> 
              	     </td> 
                </tr>                
                <tr>
                	<td class="text-center td-narrow"  >
                	<a class="btn" href="javascript:User._changePwd(<c:out value='${user.userid}' default='wang'/>)" >修改</a>
                	</td>
                </tr>
            </tbody>
        </table>
    	</div>
    	<div class="col-md-1"></div>
    </div>
</body>
</html>