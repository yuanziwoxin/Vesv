<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

  <!--  <script type="text/javascript" src="./js/jquery-1.4.2.js"></script> -->
   <script type="text/javascript">
   /*点击图片改变图片*/
   function changeImg(img)
   {
        img.src=img.src+"?time="+new Date().getTime();
   }
   function formCheck()
   {    
        var isForword=true;
        isForword=isForword && checkNull("username","用户名不能为空");
        isForword=isForword && checkNull("password","密码不能为空");
        isForword=isForword && checkNull("passwordAgain","确认密码不能为空");
        isForword=isForword && checkNull("nickname","昵称不能为空");
        isForword=isForword && checkNull("email","邮件不能为空");
        isForword=isForword && checkNull("valistr","验证码不能为空");
        
        //验证两次密码是否一致
        var pwd1=document.getElementsByName("password")[0].value;
        var pwd2=document.getElementsByName("passwordAgain")[0].value;
        if(pwd1!=pwd2)
        {
              document.getElementById("passwordAgain_msg").innerHTML="<font color='red'>两次密码不一致！</font>";
              isForword=false;
        }
        //验证邮箱格式是否正确(xxx@xxx.xxxx.xxx...)即（^\w+@\w+(\.\w+)+$）
        var eml=document.getElementsByName("email")[0].value;
        if(eml!=null && eml!="" && !/^\w+@\w+(\.\w+)+$/.test(eml))
        {
              document.getElementById("email_msg").innerHTML="<font color='red'>邮箱格式不能正确！请按照以下格式书写（如：xx@xx.xx.xx.....）</font>";
              isForword=false;
        }
        
        return isForword;
   }
   function checkNull(name,msg)
   { 
        document.getElementById(name+"_msg").innerHTML="";/*初始情况为空*/
        var objValue=document.getElementsByName(name)[0].value;
         if(objValue==null || objValue=="")
         {
             document.getElementById(name+"_msg").innerHTML="<font color='red'>"+msg+"</font>";
              return false;
         }else
         {
              return true;
         }
        
   }
    //利用Ajax方式实现前端不刷新验证用户名是否存在
     window.onload=function(){
       // alert(110);
       $("input[type='text'][name='username']").change(function(){
              // alert(110);
               var username=$(this).val();
               //以Ajax方式在页面没有刷新的情况下将输入的用户名提交到后台进行验证是否存在
              $.post("${pageContext.request.contextPath}/ValiNameServlet",{username:username},function(data){
                 // eval方法要写成这样的形式是因为（原因在于：eval本身的问题。 由于json是以”{}”的方式来开始以及结束的，在JS中，它会被当成一个语句块来处理，所以必须强制性的将它转换成一种表达式。）
                   
                  var json=eval("("+data+")");//将后台传过来的json格式字符串解析成json格式数据
                   if(json.state==1)
				  {
				    $("#username_msg").html("<font color='red'>"+json.msg+"</font>");
				  }
				   if(json.state==0)
				  {
				     $("#username_msg").html("<font color='green'>"+json.msg+"</font>");
				  }
              });
       });
   }
   </script>
  	
    <style>
	.passport_container{
		margin:10% auto 0 auto;
		min-height: 600px;
		width: 300px;
		text-align: center;
	}
	#registInput{
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
  <div class="container">
  <div class="passport_container">
   <form action="${pageContext.request.contextPath}/RegistServlet" method="POST" onsubmit="return formCheck()">
     <table>
     <tr>
     <td colspan="2">
     	<div class="row top15">
			<h1>用户注册</h1>
    	</div>
     </td>
     </tr>
     <tr>
     <td>
     <div class="input-group top15">
		  <span class="input-group-addon"><i class="icon icon-user"></i></span>
		  <input type="text"  name="username" class="form-control" placeholder="用户名"  value="${param.username}" width="200"/>
	 </div>
	 </td>
	 <td id="username_msg"></td>
     </tr>
     <tr>
     <td>
    <div class="input-group top15">
		  <span class="input-group-addon"><i class="icon icon-key"></i></span>
		  <input type="password"  name="password" class="form-control" placeholder="密码" width="200">
	</div>
	</td>
	<td id="password_msg"></td>
	</tr>
	<tr>
	<td>
	<div class="input-group top15">
		  <span class="input-group-addon"><i class="icon icon-key"></i></span>
		  <input type="password" name="passwordAgain"  class="form-control" placeholder="确认密码" width="200">
	</div>
	</td>
	<td id="passwordAgain_msg"></td>
    </tr>
    <tr>
	<td>
    <div class="input-group top15">
		  <span class="input-group-addon"><i class="icon icon-user"></i></span>
		  <input type="text"  name="nickname" class="form-control" placeholder="昵称"  value="${param.nickname}" width="200"/>
	 </div>
    </td>
    <td id="nickname_msg"></td>
    </tr>
    <tr>
	<td>
    <div class="input-group top15">
		  <span class="input-group-addon"><i class="icon icon-envelope"></i></span>
		  <input type="text"  name="email" class="form-control" placeholder="邮箱"  value="${param.email}" width="200"/>
	</div>
    </td>
    <td id="email_msg"></td>
    </tr>
    <tr>
	<td>
    <div class="input-group top15">
				<span class="input-group-addon"><i class="icon icon-lock"></i></span>
				<input type="text" id="pic_captcha" name="valistr"  class="form-control" placeholder="验证码" value="${param.valistr}" width="180">
	</div>
	</td>
	<td id="valistr_msg">${msg}</td>
    </tr>
	<tr>
	<td colspan="2">
	<div class="input-group top15">
				<span class="input-group-addon" style="width:225px;padding:0;border:0">
				    <img src="${pageContext.request.contextPath}/ValiImg" onclick="changeImg(this)" alt="点击图片，切换验证码" style="width:100%;height:55px;cursor:pointer"/>
				</span>
	</div>
	</td>
    </tr>
    <tr>
	<td>
	<br/>
    <input type="submit" id="registInput" name="registSub" class="form-control" value="注册"/>
    </td>
    </tr>
   <tr>
	<td>
    <div class="row top15">
			<div class="col-md-8 col-md-offset-2">
				<strong><a href="${pageContext.request.contextPath}/login.jsp">已有用户名！请登录！</a></strong>
			</div>
    </div>
     </td>
    </tr>
    </table>
   </form>
   </div>
   </div>
  </body>
</html>
