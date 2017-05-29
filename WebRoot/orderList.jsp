<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
     <title>Estore—订单列表</title>
  </head>
  <body>
     <div align="center"><h1>Estore—订单列表</h1><hr></div>
     <c:forEach items="${requestScope.list}" var="olf">
      <h3>订单号：${olf.id }<br/></h3>
         用户名：${olf.user_name}<br/>
         订单金额：${olf.money}<br/>
         支付状态：<c:if test="${olf.paystate==0}">
                       <font color="red">未支付</font>
                       <a href="${pageContext.request.contextPath}/DelOrderServlet?id=${olf.id}">删除订单</a>
                       <a href="${pageContext.request.contextPath }/pay.jsp?id=${olf.id}&money=${olf.money}">在线支付</a>
                    </c:if>
                    <c:if test="${olf.paystate!=0}">
                       <font color="blue">已支付</font>
                    </c:if>
                    <br/>
         收货地址：${olf.receiverinfo}<br/>
         下单时间：${olf.ordertime}<br/><br/>
       <table width="100%" border="1">
           <tr>
              <th>商品名称</th>
              <th>购买数量</th>
              <th>单价</th>
              <th>总金额</th>
           </tr>
           <c:forEach items="${olf.prodMap}" var="entry">
           <tr>
              <td>${entry.key.name}</td>
              <td>${entry.value}</td>
              <td>${entry.key.price}</td>
              <td>${entry.key.price*entry.value}</td>
           </tr>
           </c:forEach>
       </table>
       <hr/>
     </c:forEach>
  </body>
</html>
