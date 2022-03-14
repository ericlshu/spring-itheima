<%--
  Created by IntelliJ IDEA.
  User: shuli
  Date: 03/14/2022
  Time: 13:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>账户添加</title>
</head>
<body>
    <h1>添加账户信息的表单</h1>
    <form name="accountForm" action="${pageContext.request.contextPath}/account/save" method="post">
        账户名称：
        <label>
            <input type="text" name="name">
        </label>
        <br>
        账户金额：
        <label>
            <input type="text" name="money">
        </label>
        <br>
        <input type="submit" value="保存"><br>
    </form>
</body>
</html>
