<%--
  Created by IntelliJ IDEA.
  User: L508
  Date: 2022/10/26
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="/css/login.css">
    <title>Document</title>
    <style>
        * {
            margin: 0;
            padding: 0;
        }
        html {
            height: 100%;
        }
        body {
            height: 100%;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="login-wrapper">
        <div class="header">账户登录</div>
        <div class="form-wrapper">
            <form action="/login" method="get">
                <input type="text" name="username" placeholder="username" class="input-item">
                <input type="password" name="password" placeholder="password" class="input-item">
                <span style="color: #ff0000">${msg}</span>
                <input type="submit" class="btn">
            </form>
        </div>
        <div class="msg">
            没有账号？
            <a href="#">点我注册</a>
        </div>
    </div>
</div>
</body>
</html>
