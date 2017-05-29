<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
      <title>Estore---商品列表</title>
  </head>
  <body>
       <div align="center">
       <h1>Estore---商品列表</h1><hr>
       <table width="100%" style="text-align: center">
       <c:forEach items="${requestScope.list}" var="prod">
          <tr>
          <%-- ImgServlet?imgurl=${prod.imgurls}????--%>
          <td width="40%"><a href="${pageContext.request.contextPath}/ProdInfoServlet?id=${prod.id}"><img src=" ${pageContext.request.contextPath}/ImgServlet?imgurl=${prod.imgurls}" style="cursor:pointer"/></a></td>
          <td width="40%">
                    商品名称：${prod.name} <br>
                    商品类型：${prod.category}<br>
                    商品价格：${prod.price}<br>
           </td>
           <td width="20%">
           <c:if test="${prod.pnum>0}">
               <font color="blue">有货</font>
           </c:if>
           <c:if test="${prod.pnum<=0}">
               <font color="red">缺货</font>
           </c:if>
           </td>
         </tr>
         <tr>
			<td colspan="3"><hr></td>
		 </tr>
       </c:forEach>
       </table>
       </div>
  </body>
</html>
