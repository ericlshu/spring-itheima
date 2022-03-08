<%--
  Created by IntelliJ IDEA.
  User: shuli
  Date: 03/08/2022
  Time: 19:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Input user list with Ajax</title>
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
    <script>
        let userList = [];
        userList.push({name:"张三", age:30});
        userList.push({name:"李四", age:40});
        userList.push({name:"王五", age:50});

        $.ajax({
            type:"POST",
            url:"${pageContext.request.contextPath}/user/save15",
            data:JSON.stringify(userList),
            contentType:"application/json;charset=utf-8"
        });

    </script>

</head>
<body>



</body>
</html>
