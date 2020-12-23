<%--
  Created by IntelliJ IDEA.
  User: 胡琪
  Date: 2020-12-20
  Time: 23:13
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
<h3>商品列表</h3>
<form action="ItemServlet?param=deleteByBatch" method="post">
<table border="1" id="item_table">
    <tr>
        <th>编号</th>
        <th>商品名</th>
        <th>商品价格</th>
        <th>商品描述</th>
        <th>图片</th>
        <th>创建时间</th>
        <th>操作</th>
    </tr>
</table>
<input type="button" value="修改" id="btn">
<input type="submit" value="删除">
<input type="button" value="添加" id="btn1">
</form>
</body>
<script src="webjars/jquery/1.8.2/jquery.js"></script>
<script>
    $(function () {
        // 异步请求查询首页的员工信息
        $.post("ItemServlet",{"param":"page"},function (data) {
            bindTable(data);
        },"json");
        // 点击查询按钮
        $("#but").click(function () {
            var id ="1";
            var ckb = $("input[type='checkbox']");
            for(var i=0;i<ckb.length;i++){
                if(ckb[i].checked){
                    id=ckb[i].value;
                }
            }
            // 异步请求根据条件分页查询员工信息
            $.post("ItemServlet",{"param":"delete","id":id},function (data) {
                bindTable(data);
            },"json");
        });

        $("#btn").click(function () {
            var id ="1";
            var ckb = $("input[type='checkbox']");
            for(var i=0;i<ckb.length;i++){
                if(ckb[i].checked){
                    id=ckb[i].value;
                }
            }
            location.href="ItemServlet?param=edit&id="+id;
        })
        $("#btn1").click(function () {
            location.href="add.jsp"
        })


    })


    // 初始化表格数据的方法
    function bindTable(data) {
        // 清空表格原有数据
        $("#item_table tr:gt(0)").remove();
        // 初始化表格数据
        $.each(data, function (index, item) {

            var tr = $("<tr></tr>");
            var td1 = $("<td>"+item.id+"</td>");
            var td2 = $("<td>"+item.name+"</td>");
            var td3 = $("<td>"+item.price+"</td>");
            var td4 = $("<td>"+item.detail+"</td>");
            var td5 = $("<td>"+item.pic+"</td>");
            var td6 = $("<td>"+item.createtime+"</td>");
            var td7= $("<input type='checkbox' id='ckb' value="+item.id+">");
            tr.append(td1);
            tr.append(td2);
            tr.append(td3);
            tr.append(td4);
            tr.append(td5);
            tr.append(td6);
            tr.append(td7);
            $("#item_table").append(tr);
        })
    }
</script>
</html>
