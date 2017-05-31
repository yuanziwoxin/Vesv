<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加景点</title>
<script type="text/javascript" src="./js/jquery-1.4.2.js"></script>
      <script type="text/javascript">
         function subFunc(){
             $("#bar1").css("display","block");
             window.setInterval(ref,100);//表示每隔100毫秒执行一次ref函数(即从后台获取最新的数据);
         }
         function ref(){
             $.post("${pageContext.request.contextPath}/UploadMsgServlet",function(data){
                //alert("post");
                if(data!=null)
                {
                   //alert(data);
                   var json=eval("("+data+")");
                    $("#bar2").css("width",json.per+"%");
                    $("#bar3").html("<font size='1' color='red'> 上传速度为: "+json.speed+" KB/S,剩余时间为:"+json.remainTime+" S </font>");
	             }
               });
         }
      </script>
  </head>
  <body>
       <div align="center">
       <h1>添加景点</h1><hr>
       <form action="${pageContext.request.contextPath}/AddSceneServlet" method="POST" enctype="multipart/form-data" onsubmit="return checkData()">
         <table border="1">
            <tr>
                <td>景点名称</td>
                 <td><input type="text" name="name" /></td>
            </tr>
             <tr>
                <td>景点图片</td>
                 <td><input type="file" name="file1" />
                 <%--上传进度条 --%>
                    <div id="bar1"  style="width:200px;height:20px;border:1px solid green;display:none;">
                        <div id="bar2"  style="width:0%;height:20px;background-color:green;"></div>
                    </div>
                    <div id="bar3"></div>
                 </td>
                 
             </tr>
             <tr>
                <td>景点描述</td>
                 <td><textarea name="description" rows="6" cols="35"></textarea></td>
            </tr>
              <tr>
                <td><input type="submit" name="addSceneSub"  value="添加景点" onclick="subFunc()"/></td>
            </tr>
         </table>
       </form>
       </div>
  </body>
</html>
