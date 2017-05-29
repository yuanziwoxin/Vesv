<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
      <title>Estore---添加商品</title>
      <script type="text/javascript" src="./js/jquery-1.4.2.js"></script>
      <script type="text/javascript">
         function checkData(){//防止价格出现非数字或非正数；
            var price=document.getElementsByName("price")[0].value;
            if(isNaN(price)){
               alert("必须为数字！");
               document.getElementsByName("price")[0].value=null;
               return false;
            }
             else if(price<=0){
               alert("必须大于0！");
               document.getElementsByName("price")[0].value=null;
               return false;
            }else{
               return true;
            }
            
         }
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
       <h1>Estore---添加商品</h1><hr>
       <form action="${pageContext.request.contextPath}/AddProdServlet" method="POST" enctype="multipart/form-data" onsubmit="return checkData()">
         <table border="1">
            <tr>
                <td>商品名称</td>
                 <td><input type="text" name="name" /></td>
            </tr>
             <tr>
                <td>商品价格</td>
                 <td><input type="text" name="price" /></td>
            </tr>
             <tr>
                <td>商品类型</td>
                 <td>
                 <select name="category" >
                     <option value="电子数码">电子数码</option>
                     <option value="书刊杂志">书刊杂志</option>
                     <option value="衣饰服装">衣饰服装</option>
                     <option value="日常百货">日常百货</option>
                     <option value="美容养颜">美容养颜</option>
                     <option value="实用小商品">实用小商品</option>
                     <option value="大型家具">大型家具</option>
                     <option value="鲜活农产品">鲜活农产品</option>
                 </select>
                 </td>
            </tr>
             <tr>
                <td>库存数量</td>
                 <td><input type="text" name="pnum" /></td>
            </tr>
             <tr>
                <td>商品图片</td>
                 <td><input type="file" name="file1" />
                 <%--上传进度条 --%>
                    <div id="bar1"  style="width:200px;height:20px;border:1px solid green;display:none;">
                        <div id="bar2"  style="width:0%;height:20px;background-color:green;"></div>
                    </div>
                    <div id="bar3"></div>
                 </td>
                 
            </tr>
             <tr>
                <td>商品描述</td>
                 <td><textarea name="description" rows="6" cols="35"></textarea></td>
            </tr>
              <tr>
                <td><input type="submit" name="addProdSub"  value="添加商品" onclick="subFunc()"/></td>
            </tr>
         </table>
       </form>
       </div>
  </body>
</html>
