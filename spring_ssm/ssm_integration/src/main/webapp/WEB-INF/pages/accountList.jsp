<%--
  Created by IntelliJ IDEA.
  User: shuli
  Date: 03/14/2022
  Time: 13:56
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>账户展示</title>
</head>
<body>
<h1>展示账户数据列表</h1>
<table border="1">
    <tr>
        <th>Account ID</th>
        <th>Account Name</th>
        <th>Account Money</th>
    </tr>
    <c:forEach items="${accountList}" var="account">
        <tr>
            <td>${account.id}</td>
            <td>${account.name}</td>
            <td>${account.money}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
