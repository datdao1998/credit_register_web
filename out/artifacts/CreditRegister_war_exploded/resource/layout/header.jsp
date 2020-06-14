<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 3/27/2020
  Time: 10:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/layout.css">
</head>
<body class="magin-0">
    <div id="header">

        <div id="header-logo">
            <a href="index.jsp" id="logo-link-home">
                <img id="img-logo-link-home" src="imgs/logo.JPG" alt="HOME">
            </a>
        </div>

        <div id="header-background">
            <div id="header-background-img">
                <img src="imgs/bg-ptit.PNG">
            </div>

            <div id="header-one">
                <div id="header-background-info"> <%= session.getAttribute("customer-account-user-name") != null ? session.getAttribute("customer-account-user-name") : ""%> </div>

                <div id="header-log-out"> <a href="user_login.jsp" onclick="logout()" onmouseover="changeLogoutColorBlue(this)" onmouseout="changeLogoutColorDefault(this)">Đăng xuất</a> </div>

            </div>


        </div>

        <div id="header-content">
            <div id="header-home">
                <a href="index.jsp" class="header-text" onmouseover="changeColorRed(this)" onmouseout = "changeColorDefault(this)"> TRANG CHỦ </a>
            </div>

            <div id="header-register">
                <a href="get_subject_by_semester" class="header-text" onmouseover="changeColorRed(this)" onmouseout = "changeColorDefault(this)"> ĐĂNG KÝ MÔN HỌC</a>
            </div>

            <div id="header-view-tkb">
                <a href="view_tkb" class="header-text" onmouseover="changeColorRed(this)" onmouseout = "changeColorDefault(this)"> XEM TKB </a>
            </div>
        </div>

        <div id="student-semester"> Học kỳ : <%=session.getAttribute("semester")%></div>

    </div>
    <script type="text/javascript" src="js/log-out.js"></script>

    </body>
</html>
