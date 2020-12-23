<%--
  Created by IntelliJ IDEA.
  User: 胡琪
  Date: 2020-12-20
  Time: 23:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
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
<form action="ItemServlet?param=editItem" method="post">
    <input type="hidden" name="id" value="${item.id}"/>
    商品名:<input name="name" value="${item.name}"/><br>
    商品价格:<input name="price" value="${item.price}"/><br>
    商品描述:<input name="detail" value="${item.detail}"/><br>
    <input type="submit" value="修改"/>
</form>
</body>
</html>
