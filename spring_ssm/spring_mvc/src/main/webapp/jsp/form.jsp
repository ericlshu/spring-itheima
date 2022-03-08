<%--
  Created by IntelliJ IDEA.
  User: shuli
  Date: 03/08/2022
  Time: 19:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Input user list with form</title>
    </head>
    <body>
        <form action="${pageContext.request.contextPath}/user/save14" method="post">
            <label>
                <input type="text" name="userList[0].name">
            </label><br>
            <label>
                <input type="text" name="userList[0].age">
            </label><br>
            <label>
                <input type="text" name="userList[1].name">
            </label><br>
            <label>
                <input type="text" name="userList[1].age">
            </label><br>
            <input type="submit" value="提交"><br>
        </form>
    </body>
</html>
