<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/12/21
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<html>
<head>
    <title></title>
</head>
<body>
    <form method="post" action="ItemServlet?param=listByDto">
        商品名：<input type="text" name="name"/><br/>
        <br/>
        <input type="submit" value="查询"/>
    </form>
    <h3>满足条件的记录数：${total}</h3>

    <form method="post" action="ItemServlet?param=deleteByBatch">

    <table border="1">
        <tr>
            <th>编号</th>
            <th>商品名</th>
            <th>商品价格</th>
            <th>商品描述</th>
            <th>图片</th>
            <th>创建时间</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${itemsList}" var="item">
            <tr>
                <td>
                    <input type="checkbox" name="id" value="${item.id}"/>
                </td>
                <td>
                        ${item.id}
                </td>
                <td>
                    <a href="ItemServlet?param=edit&id=${item.id}">${item.name}</a>
                </td>
                <td>
                    ${item.price}
                </td>
                <td>
                        ${item.detail}
                </td>
                <td>
                        ${item.pic}
                </td>
                <td>
                        ${item.createtime}
                </td>
            </tr>
        </c:forEach>
        <input type="submit" value="批量删除"/>
        <input type="button" value="添加" id="btn1">
   </table>
    </form>
  <a href="ItemServlet?param=listByDto&pageNo=${dto.up}&pageSize=${dto.pageSize}">上一页</a>
  <a href="ItemServlet?param=listByDto&pageNo=${dto.down}&pageSize=${dto.pageSize}">下一页</a>
    <a href="ItemServlet?param=listByDto&pageNo=${dto.first}&pageSize=${dto.pageSize}">首页</a>
    <a href="ItemServlet?param=listByDto&pageNo=${dto.last}&pageSize=${dto.pageSize}">末页</a>
</body>
<script src="webjars/jquery/1.8.2/jquery.js"></script>
<script>
    $(function () {
        $("#btn1").click(function () {
            location.href="add.jsp"
        })
    })

</script>
</html>
