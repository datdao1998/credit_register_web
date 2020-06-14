<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 4/6/2020
  Time: 10:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Đăng Nhập</title>
    <link rel="stylesheet" type="text/css" href="css/user-login.css">
</head>
<body class="magin-0">
    <div id="log-in-content">
        <div id="customer-login-div-1">
            <img src="imgs/logo.JPG" id="customer-login-logo">
            <div id="customer-login-logo-des">
                <span style="color: #fdbd00">ĐĂNG   </span>
                <span style="color: white"> NHẬP</span>
            </div>
        </div>

        <div id="customer-login-div-2">
            <form id="customer-login-form" method="post" action="customer/login">
                <label class="customer-login-label" for="customer-login-input-user-name">Tên đăng nhập</label>
                <input type="text" id="customer-login-input-user-name" name="customer-login-input-user-name"><br>
                <label class="customer-login-label" for="customer-login-input-user-name">Mật khẩu</label>
                <input type="password" id="customer-login-input-password" name="customer-login-input-password">
            </form>
        </div>

        <div id="customer-login-div-3">
            <button id="customer-login-btn-login"
                    onmouseout="changeBgColorDefault(this)"
                    onmouseover="changeBgColorWhite(this)">Đăng nhập</button>
        </div>
        <div id="customer-login-account-authenticate">
            <%= session.getAttribute("customer-account-authenticate") != null ?
                    session.getAttribute("customer-account-authenticate") : ""%>
        </div>
    </div>

    <%@include file="resource/layout/footer.jsp"%>
    <script type="text/javascript" src="js/base.js"></script>
    <script type="text/javascript" src="js/customer-login.js"></script>
</body>
</html>
