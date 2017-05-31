<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html lang="zh-ch" class="screen-desktop-wide device-desktop">
 <head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<meta name="renderer" content="webkit">
	<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1">
	
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
	<title>用户登录</title>
	<meta name="description" content="游客虚拟体验系统">
	
	<link rel="stylesheet" href="./vesv_files/zui.min.css">
	<link rel="stylesheet" href="./vesv_files/zui-theme.css">
	<link rel="stylesheet" href="./vesv_files/redefine.css">
	
	
	<script src="./vesv_files/hm.js"></script>
	<script src="./vesv_files/hm(1).js"></script>
	<script language="JavaScript" type="text/javascript" src="./vesv_files/jquery-1.9.1.js"></script>
	<script language="JavaScript" type="text/javascript" src="./vesv_files/datetimepicker.js"></script>
    <script type="text/javascript">
       window.onload=function(){//window.onload表示在页面加载完毕后再执行以下内容
          var str=decodeURI("${cookie.remName.value}"); //先进行解码操作；
          document.getElementsByName("username")[0].value=str; //然后将解码的值赋给用户名，相当于回写；
       }
  	</script>
    <style>
	.passport_container{
		margin:10% auto 0 auto;
		min-height: 600px;
		width: 300px;
		text-align: center;
	}
	#loginInput{
	  background-color:green;
	  color: #ffffff;
	  font-weight: bloder;
	} 
	</style>
    
    <style id="style-1-cropbar-clipper">
		.en-markup-crop-options {
		    top: 18px !important;
		    left: 50% !important;
		    margin-left: -100px !important;
		    width: 200px !important;
		    border: 2px rgba(255,255,255,.38) solid !important;
		    border-radius: 4px !important;
		}
		/* !important 用于在IE浏览器和IE以外的浏览器设置不同的样式，因为IE浏览器不能理解!important，而IE以外的浏览器可以，所以只要在后面加!important的样式在IE浏览器中不会起作用 */
		.en-markup-crop-options div div:first-of-type {
		    margin-left: 0px !important;
		}
	</style>
   
   
 </head>
 <body>
  <!--  <div align="center"> -->
 <div class="container">
  <div class="passport_container">
   <div class="row top15">
			<h1>用户登录</h1>
   </div>
   
   <!-- <h1>用户登录</h1><hr> -->
	   <div class="row">
		   <div class="col-md-12 top15">
		   <font color="red">${msg}</font>
				   <form id="login_form" action="${pageContext.request.contextPath}/LoginServlet" method="POST" ">
					   		<div class="input-group top15">
								  <span class="input-group-addon"><i class="icon icon-user"></i></span>
								  <input type="text"  name="username" class="form-control" placeholder="用户名"  value="${str}" width="200"/>
							</div>
							<div class="input-group top15">
							  <span class="input-group-addon"><i class="icon icon-key"></i></span>
							  <input type="password"  name="password" class="form-control" placeholder="密码" width="200">
							</div>
								
							<div class="checkbox row top15">
								 <div class="col-md-6">
									  <label>
									    <input type="checkbox" name="remname" value="true" 
									    	 <c:if test="${cookie.remName!=null}">checked="checked"</c:if>
									    /> 记住用户名
									  </label>
							     </div>
								 <div class="col-md-6">
									  <label>
									   <input type="checkbox" name="autologin" value="true" 
								            <c:if test="${cookie.autoLogin!=null}">checked="checked"</c:if>
								       />30天自动登录
									  </label>
								 </div>
						 </div>
						        <input id="loginInput" type="submit" name="loginSub" class="form-control"  value="登录"/>
								<!-- <button class="btn btn-block btn-primary top15" type="button" id="login_btn" onclick="ajaxFormSubmit(&#39;login_form&#39;,&#39;login_btn&#39;);">立即登录</button> -->
				   </form>
		   </div>
		  <div class="row top15">
			<div class="col-md-8 col-md-offset-2">
				<br/>
				<strong><a href="${pageContext.request.contextPath}/regist.jsp">没有用户名？请点击注册！</a></strong>
			</div>
  		  </div>
	   </div>
   </div>
   </div>
  </body>
</html>
