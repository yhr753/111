<%--
  Created by IntelliJ IDEA.
  User: 胡琪
  Date: 2020-12-21
  Time: 13:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title></title>
</head>
<body>
<h3>添加页面</h3>
<form action="ItemServlet?param=add" method="post">
    商品名:<input name="name"/><br>
    商品价格:<input name="price"/><br>
    商品描述:<input name="detail"/><br>
    <input type="submit" value="添加"/>
</form>
</body>
</html>
