<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
      <title>Estore---商品详情</title>
  </head>
  <body>
       <div align="center">
       <h1><font color="red">${prod.name}</font></h1><hr>
       <table width="100%">
         <tr>
          <%-- ImgServlet?imgurl=${prod.imgurls}????--%>
          <td width="40%"><img src=" ${pageContext.request.contextPath}/ImgServlet?imgurl=${prod.imgurl}"/></td>
          <td width="40%">
                    商品名称：${prod.name } <br>
                    商品类型：${prod.category }<br>
                    商品价格：${prod.price }<br>
                    库存数量：${prod.pnum}<br>  
                    商品描述：${prod.description}<br>
               <br>  
               <a href="${pageContext.request.contextPath}/AddCartServlet?id=${prod.id}"><img alt="加入购物车" src="./img/buy.bmp"></a>   
               <img alt="去购物车结算" src="./img/cart.bmp">
               <img alt="去结算中心" src="./img/gotoorder.bmp">         
           </td>
         </tr>
       </table>
       </div>
  </body>
</html>
