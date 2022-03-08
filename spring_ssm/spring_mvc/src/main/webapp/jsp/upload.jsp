<%--
  Created by IntelliJ IDEA.
  User: shuli
  Date: 03/08/2022
  Time: 21:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Upload file</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/user/save22" method="post"
      enctype="multipart/form-data">
    名称：<input type="text" name="name"><br>
    文件：<input type="file" name="file"><br>
    <input type="submit" value="提交"><br>
</form>

<form action="${pageContext.request.contextPath}/user/save23" method="post"
      enctype="multipart/form-data">
    名称1：<input type="text" name="name1"><br>
    文件1：<input type="file" name="file1"><br>
    名称2：<input type="text" name="name2"><br>
    文件2：<input type="file" name="file2"><br>
    <input type="submit" value="提交"><br>
</form>

<form action="${pageContext.request.contextPath}/user/save24" method="post"
      enctype="multipart/form-data">
    名称：<input type="text" name="name"><br>
    文件1：<input type="file" name="files"><br>
    文件2：<input type="file" name="files"><br>
    <input type="submit" value="提交"><br>
</form>

</body>
</html>
