<%@ page import="java.util.ArrayList" %>
<%@ page import="api.model.entity.PhieuDangKyHoc" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 4/6/2020
  Time: 10:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Xem Thời Khóa Biểu</title>
    <link rel="stylesheet" type="text/css" href="css/view-tkb.css">
    <link rel="stylesheet" type="text/css" href="css/base.css">

</head>
<body class="magin-0">
    <%
        if (session == null || session.getAttribute("customer-account-id") == null) {
            response.sendRedirect("user_login.jsp");
        }
    %>

    <%@include file="resource/layout/header.jsp"%>

    <%
        if(request.getAttribute("listLearnForm")!=null){
            ArrayList<PhieuDangKyHoc> phieuDangKyHocs = (ArrayList<PhieuDangKyHoc>) request.getAttribute("listLearnForm");
    %>

        <div id="view-tkb-home">
            <div id="class-result">
                <table>
                    <tr>
                        <th class="title">
                            Mã MH
                        </th>
                        <th class="title">
                            Tên MH
                        </th>
                        <th class="title">
                            Nhóm MH
                        </th>
                        <th class="title">
                            STC
                        </th>
                        <th class="title">
                            Thứ
                        </th>
                        <th class="title">
                            Giờ bắt đầu
                        </th>
                        <th class="title">
                            Giờ kết thúc
                        </th>

                        <th class="title">
                            Tuần bắt đầu
                        </th>
                        <th class="title">
                            Tuần kết thúc
                        </th>

                        <th class="title">
                            Phòng
                        </th>
                    </tr>
                    <%
                        for(int i = 0 ; i < phieuDangKyHocs.size(); i++){
                    %>
                    <tr>
                        <th><%=phieuDangKyHocs.get(i).getPhieuDangKyDay().getListLopHocPhan().get(0).getMonHoc().getMaMon()%></th>
                        <th><%=phieuDangKyHocs.get(i).getPhieuDangKyDay().getListLopHocPhan().get(0).getMonHoc().getTenMon()%></th>
                        <th><%=phieuDangKyHocs.get(i).getPhieuDangKyDay().getNhomLop()%></th>
                        <th><%=phieuDangKyHocs.get(i).getPhieuDangKyDay().getListLopHocPhan().get(0).getMonHoc().getSoTinChi()%></th>
                        <th>
                            <%
                                String strThu = "";
                                for(int j = 0 ; j < phieuDangKyHocs.get(i).getPhieuDangKyDay().getListLopHocPhan().size();j++){
                                    strThu += phieuDangKyHocs.get(i).getPhieuDangKyDay().getListLopHocPhan().get(j).getKipHoc().getThu() + "\n";
                                }
                            %>
                            <%= strThu %>
                        </th>
                        <th>
                            <%
                                String strGioBD = "";
                                for(int j = 0 ; j < phieuDangKyHocs.get(i).getPhieuDangKyDay().getListLopHocPhan().size();j++){
                                    strGioBD += phieuDangKyHocs.get(i).getPhieuDangKyDay().getListLopHocPhan().get(j).getKipHoc().getGioBatDau() + "\n";
                                }
                            %>
                            <%= strGioBD %>
                        </th>
                        <th>
                            <%
                                String strGioKT = "";
                                for(int j = 0 ; j < phieuDangKyHocs.get(i).getPhieuDangKyDay().getListLopHocPhan().size();j++){
                                    strGioKT += phieuDangKyHocs.get(i).getPhieuDangKyDay().getListLopHocPhan().get(j).getKipHoc().getGioKetThuc() + "\n";
                                }
                            %>
                            <%= strGioKT %>
                        </th>
                        <th>
                            <%
                                String strTuanBD = "";
                                for(int j = 0 ; j < phieuDangKyHocs.get(i).getPhieuDangKyDay().getListLopHocPhan().size();j++){
                                    strTuanBD += phieuDangKyHocs.get(i).getPhieuDangKyDay().getListLopHocPhan().get(j).getTuanBatDau() + "\n";
                                }
                            %>
                            <%= strTuanBD %>
                        </th>
                        <th>
                            <%
                                String strTuanKT = "";
                                for(int j = 0 ; j < phieuDangKyHocs.get(i).getPhieuDangKyDay().getListLopHocPhan().size();j++){
                                    strTuanKT += phieuDangKyHocs.get(i).getPhieuDangKyDay().getListLopHocPhan().get(j).getTuanKetThuc() + "\n";
                                }
                            %>
                            <%= strTuanKT %>
                        </th>
                        <th> <%= phieuDangKyHocs.get(i).getPhieuDangKyDay().getListLopHocPhan().get(0).getPhongHoc().getTenPhong()%></th>
                    </tr>
                    <%
                        }
                    %>
                </table>
            </div>
            <%
                }
            %>

        </div>
    <%@include file="resource/layout/footer.jsp"%>

</body>
</html>
