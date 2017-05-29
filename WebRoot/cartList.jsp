<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
      <title>Estore--我的购物车</title>
      <script type="text/javascript">
        function changeNum(id,obj,oldnum)
        {
            
             if(!/^[1-9]\d*$/.test(obj.value))
             {
                alert("必须为正整数！！！");
                obj.value=oldnum;
                return;
             }
             /*改变数量时触发改变购物数量的方法*/
             window.location.href="${pageContext.request.contextPath}/ChangeCartServlet?id="+id+"&buynum="+obj.value;
              
        }
      </script>
  </head>
  <body>
       <div align="center"><h1>Estore-我的购物车</h1><hr></div>
       <div align="right">
          <a href="${pageContext.request.contextPath}/ProdListServlet">继续购物</a>
          <a href="${pageContext.request.contextPath}/ClearCartServlet">清空购物车</a>
          <a href="${pageContext.request.contextPath}/addOrder.jsp"><img src="./img/gotoorder.bmp"/></a>
       </div><br/>
       <c:if test="${empty sessionScope.cartmap}">
           <div align="right"><a href="${pageContext.request.contextPath}/ProdListServlet">您的购物车空空如也,赶紧挑些商品吧！</a></div><br/>
       </c:if>
      <c:if test="${not empty sessionScope.cartmap}">
      <table width="100%" border="1" style="text-align: center">
             <tr>
              <th>图片</th>
              <th>商品名称</th>
              <th>商品类型</th>
              <th>商品价格</th>
              <th>购买数量</th>
              <th>库存状态</th>
              <th>总价</th>
              <th>操作</th>
           </tr>
           <c:set var="totalPrice" value="0"/>
           <c:forEach items="${sessionScope.cartmap}" var="entry">
             <tr>
                <td><img src="${pageContext.request.contextPath}/ImgServlet?imgurl=${entry.key.imgurls}"/></td>
                <td>${entry.key.name}</td>
                <td>${entry.key.category}</td>
                <td>${entry.key.price}元</td>
                <td><input type="text" value="${entry.value}" style="width:30px" onchange="changeNum('${entry.key.id}',this,'${entry.value}')"/>件</td><%--购买数量即是cartmap中的value值 --%>
                <td>
                    <c:if test="${entry.key.pnum>entry.value}">
                       <font color="blue">有货</font>
                    </c:if>
                    <c:if test="${entry.key.pnum<entry.value}">
                       <font color="red">缺货</font>
                    </c:if>
                </td>
                <td>
                      ${entry.key.price*entry.value}元
                      <c:set var="totalPrice" value="${totalPrice+entry.key.price*entry.value}"/>
                 </td>
                <td><a href="${pageContext.request.contextPath}/DelCartServlet?id=${entry.key.id}">删除</a></td>
             </tr>
           </c:forEach>
      </table>
      <div align="right">
         <font color="red" size="5">所有商品总价为:${totalPrice}元</font>
      </div>
      </c:if>
  </body>
</html>
