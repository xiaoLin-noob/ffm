<%--
  Created by IntelliJ IDEA.
  User: 94772
  Date: 2022/11/20
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--<div class="table-box">--%>
<%--    <!-- 数据表格 -->--%>
<%--    <table id="dataList" class="table table-bordered text-center">--%>
<%--        <thead>--%>
<%--        <tr>--%>
<%--            <th>id</th>--%>
<%--            <th>标题</th>--%>
<%--            <th>用户名</th>--%>
<%--            <th>金额</th>--%>
<%--            <th>类型</th>--%>
<%--            <th>支付方式</th>--%>
<%--            <th>时间</th>--%>
<%--        </tr>--%>
<%--        </thead>--%>
<%--        <tbody>--%>
<%--        <c:forEach items="${bills}" var="book">--%>
<%--            <tr>--%>
<%--                <td>${book.id}</td>--%>
<%--                <td>${book.title}</td>--%>
<%--                <td>${book.userId}</td>--%>
<%--                <td>${book.money}</td>--%>
<%--                <td>${book.type}</td>--%>
<%--                <td>${book.payWayId}</td>--%>
<%--                <td>${book.time}</td>--%>
<%--                <td>${book.user.username}</td>--%>
<%--                <td>${book.payWay.payWay}</td>--%>
<%--            </tr>--%>
<%--        </c:forEach>--%>
<%--        </tbody>--%>
<%--    </table>--%>
<%--</div>--%>


<form action="/changeBill" >
    <input name="id">
    <input name="title">
    <input name="money">
    <input name="type">
    <input name="payWayId">
    <input type="submit">
</form>

</body>
</html>
