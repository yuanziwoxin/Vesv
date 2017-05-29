<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@  taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
        <title>Estore--生成订单</title>
  </head>
  <body>
      <div align="center">
      <h1>Estore--生成订单</h1><hr/>
          <table width="100%" border="1" style="text-align: center">
             <tr>
              <th>商品名称</th>
              <th>商品类型</th>
              <th>商品价格</th>
              <th>购买数量</th>
              <th>总价</th>
            </tr>
           <c:set var="totalPrice" value="0"/>
           <c:forEach items="${sessionScope.cartmap}" var="entry">
             <tr>
                <td>${entry.key.name}</td>
                <td>${entry.key.category}</td>
                <td>${entry.key.price}元</td>
                <td>${entry.value}件</td><%--购买数量即是cartmap中的value值 --%>
                <td>
                      ${entry.key.price*entry.value}元
                      <c:set var="totalPrice" value="${totalPrice+entry.key.price*entry.value}"/>
                 </td>
             </tr>
           </c:forEach>
      </table>
      <div align="right">
         <font color="red" size="5">所有商品总价为:${totalPrice}元</font>
      </div>
      
      <form action="${pageContext.request.contextPath}/AddOrderServlet" method="post">
               收货地址:<textarea name="receiverinfo" rows="5" cols="35" ></textarea><br/>
               支付方式: <input type="radio" name="type" checked="checked"/>在线支付<br>
            <input type="submit" name="addorderSub" value="生成订单"/>   
      </form>
        
        
      </div>
  </body>
</html>
