<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>500错误页面</title>
  </head>
  <body>
     <h3>亲，服务器出错了哦.....</h3><hr>
     ${pageContext.exception.message}
  </body>
</html>
