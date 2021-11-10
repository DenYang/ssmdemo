<%--
  Created by IntelliJ IDEA.
  User: 13084
  Date: 2021/11/5
  Time: 23:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户注册</title>
    <style type="text/css">
        #root{
            text-align: center;
        }
    </style>
</head>
<body>
    <div id="root">
    <form action="/myweb/user/adduser.do" method="post">
        用户姓名:<input type="text" name="name"><br/>
        用户密码:<input type="password" name="pwd"><br/>
        用户邮箱:<input type="text" name="email"><br/>
        用户性别:<input type="radio" name="sex" value="男">男
                <input type="radio" name="sex" value="女">女
        <br/>
        <input type="submit" value="注册"><input type="reset" value="重新填写">
    </form>
    </div>
</body>
</html>
