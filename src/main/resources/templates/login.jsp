<%--
  Created by IntelliJ IDEA.
  User: L508
  Date: 2022/10/26
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>云借阅-图书管理系统</title>
    <link rel="stylesheet" type="text/css" href="/css/webbase.css"/>
    <link rel="stylesheet" type="text/css" href="/css/pages-login-manage.css"/>
</head>
<body>
<div class="loginmanage">
    <div class="py-container">
        <h4 class="manage-title">云借阅-图书管理系统</h4>
        <div class="loginform">
            <ul class="sui-nav nav-tabs tab-wraped">
                <li class="active">
                    <h3>账户登录</h3>
                </li>
            </ul>
            <div class="tab-content tab-wraped">
                <!--登录提示信息-->
                <span style="color: #ff0000">${msg}</span>
                <div id="profile" class="tab-pane  active">
                    <form id="loginform" class="sui-form" action="/login"
                          method="get">
                        <div class="input-prepend"><span class="add-on loginname">用户名</span>
                            <input type="text" placeholder="企业邮箱" class="span2 input-xfat" name="username">
                        </div>
                        <div class="input-prepend"><span class="add-on loginpwd">密码</span>
                            <input type="password" placeholder="请输入密码" class="span2 input-xfat" name="password">
                        </div>
                        <div class="logined">
                            <a class="sui-btn btn-block btn-xlarge btn-danger"
                               href='javascript:loginform.submit();' target="_self">登&nbsp;&nbsp;录</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    /**
     * 登录超时 展示区跳出iframe
     */
    var _topWin = window;
    while (_topWin != _topWin.parent.window) {
        _topWin = _topWin.parent.window;
    }
    if (window != _topWin)
        _topWin.document.location.href = 'login.jsp';
</script>
</html>
