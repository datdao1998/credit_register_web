<%@ page import="api.model.entity.MonHoc" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="api.model.entity.PhieuDangKyDay" %>
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
    <title>Đăng ký tín chỉ</title>
    <link rel="stylesheet" type="text/css" href="css/credit-register.css">
    <link rel="stylesheet" type="text/css" href="css/base.css">
</head>
<body>
<%
    if (session == null || session.getAttribute("customer-account-id") == null) {
        response.sendRedirect("user_login.jsp");
    }
%>

<%
    if (request.getAttribute("listLearnForm")!=null){
        ArrayList<PhieuDangKyHoc> phieuDangKyHocs = (ArrayList<PhieuDangKyHoc>) request.getAttribute("listLearnForm");
    }
%>

    <%@include file="resource/layout/header.jsp"%>
    <div id="credit-register-home">
        <div id="credit-register-div-1">
            <form id="subject-form-search" action="search_by_subject" method="get">
                <label class="subject-form-search" for="subject-input"> Nhập môn học </label>
                <input type="text" id="subject-input" name="subject-input"
                       onmouseover="changeBgColorDefault(this)" onmouseout="changeBgColorWhite(this)">
            </form>
            <div  style="width: 100vw; margin-right: 2vw; margin-top: 2vh; float: right">
                <button id="subject-btn-search" >Tìm kiếm</button>
            </div>
        </div>

        <div id="credit-register-div-2">
            <div id="subject-in-semester-label"  class="text-div-2" style="width: 20vw; height: 40vh; margin-left: 10vw; text-align: center; float: left"> <br><br><br><br><br>Môn học theo<br> CTĐT kế hoạch</div>
            <div id="subject-in-semester-result">
                <%
                    if(request.getAttribute("checkCalendar")!=null){
                %>
                <div id="notify">  Notify : <%=request.getAttribute("checkCalendar")%> </div>
                <%
                    }
                %>
                <%
                    if(request.getAttribute("listSubject")!=null){
                        ArrayList<MonHoc> monHocs = (ArrayList<MonHoc>) request.getAttribute("listSubject");
                %>

                <select id="combo-box-subject">
                    <%
                        for(int i = 0; i < monHocs.size() ; i++){
                    %>
                    <option class="text-div-2"> <%= monHocs.get(i).getMaMon() + " - " + monHocs.get(i).getTenMon() + " (" + monHocs.get(i).getSoTinChi() + "TC)"%></option>
                    <%
                        }
                    %>
                </select>
                <%
                    }
                %>
            </div>
        </div>

        <div id="credit-register-div-3">
            <%
                if(request.getAttribute("listTeachForm")!=null){
                    ArrayList<PhieuDangKyDay> phieuDangKyDays = (ArrayList<PhieuDangKyDay>) request.getAttribute("listTeachForm");
                    ArrayList<PhieuDangKyHoc> phieuDangKyHocs = (ArrayList<PhieuDangKyHoc>) request.getAttribute("listLearnForm");

            %>

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
                        Mã lớp
                    </th>
                    <th class="title">
                        Sĩ Số
                    </th>
                    <th class="title">
                        CL
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
                        Giảng Viên
                    </th>
                    <th class="title">
                        Phòng
                    </th>
                    <th class="title">
                        Tuần bắt đầu
                    </th>
                    <th class="title">
                        Tuần kết thúc
                    </th>

                    <th class="title">
                        Trạng thái
                    </th>

                </tr>
                <%
                    for(int i = 0 ; i < phieuDangKyDays.size(); i++){
                %>
                <tr>
                    <th><%=phieuDangKyDays.get(i).getListLopHocPhan().get(0).getMonHoc().getMaMon()%></th>
                    <th><%=phieuDangKyDays.get(i).getListLopHocPhan().get(0).getMonHoc().getTenMon()%></th>
                    <th><%=phieuDangKyDays.get(i).getNhomLop()%></th>
                    <th><%=phieuDangKyDays.get(i).getListLopHocPhan().get(0).getMonHoc().getSoTinChi()%></th>
                    <th><%=phieuDangKyDays.get(i).getMaLop()%></th>
                    <th><%=phieuDangKyDays.get(i).getSiSo()%></th>
                    <th><%=phieuDangKyDays.get(i).getSiSo() -  phieuDangKyDays.get(i).getSoSinhVienDangKy()%></th>
                    <th>
                        <%
                            String strThu = "";
                            for(int j = 0 ; j < phieuDangKyDays.get(i).getListLopHocPhan().size();j++){
                                strThu += phieuDangKyDays.get(i).getListLopHocPhan().get(j).getKipHoc().getThu() + "\n";
                            }
                        %>
                        <%= strThu %>
                    </th>
                    <th>
                        <%
                            String strGioBD = "";
                            for(int j = 0 ; j < phieuDangKyDays.get(i).getListLopHocPhan().size();j++){
                                strGioBD += phieuDangKyDays.get(i).getListLopHocPhan().get(j).getKipHoc().getGioBatDau() + "\n";
                            }
                        %>
                        <%= strGioBD %>
                    </th>
                    <th>
                        <%
                            String strGioKT = "";
                            for(int j = 0 ; j < phieuDangKyDays.get(i).getListLopHocPhan().size();j++){
                                strGioKT += phieuDangKyDays.get(i).getListLopHocPhan().get(j).getKipHoc().getGioKetThuc() + "\n";
                            }
                        %>
                        <%= strGioKT %>
                    </th>

                    <th>
                        <%
                            String strGiangVien = "";
                            for(int j = 0 ; j < phieuDangKyDays.get(i).getListLopHocPhan().size();j++){
                                strGiangVien += phieuDangKyDays.get(i).getListLopHocPhan().get(j).getGiangVien().getTenGiangVien() + "\n";
                            }
                        %>
                        <%= strGiangVien %>
                    </th>

                    <th>
                        <%
                            String strPhong = "";
                            for(int j = 0 ; j < phieuDangKyDays.get(i).getListLopHocPhan().size();j++){
                                strPhong += phieuDangKyDays.get(i).getListLopHocPhan().get(j).getPhongHoc().getTenPhong() + "\n";
                            }
                        %>
                        <%= strPhong %>
                    </th>
                    <th>
                        <%
                            String strTuanBD = "";
                            for(int j = 0 ; j < phieuDangKyDays.get(i).getListLopHocPhan().size();j++){
                                strTuanBD += phieuDangKyDays.get(i).getListLopHocPhan().get(j).getTuanBatDau() + "\n";
                            }
                        %>
                        <%= strTuanBD %>
                    </th>
                    <th>
                        <%
                            String strTuanKT = "";
                            for(int j = 0 ; j < phieuDangKyDays.get(i).getListLopHocPhan().size();j++){
                                strTuanKT += phieuDangKyDays.get(i).getListLopHocPhan().get(j).getTuanKetThuc() + "\n";
                            }
                        %>
                        <%= strTuanKT %>
                    </th>

                    <%
                        boolean oke = false;
                        for (PhieuDangKyHoc phieuDangKyHoc:phieuDangKyHocs){
                            if(phieuDangKyHoc.getPhieuDangKyDay().getPhieuDangKyDayId().equals(phieuDangKyDays.get(i).getPhieuDangKyDayId()))
                                oke = true;
                        }
                        if(oke){
                    %>

                        <form id="choose-class-form-id-<%=phieuDangKyDays.get(i).getPhieuDangKyDayId()%>" action="add_temporal_learn_form" method="post" >
                            <input type="hidden" name="teach-form-id" value="<%=phieuDangKyDays.get(i).getPhieuDangKyDayId()%>">
                        </form>
                        <th>
                            <button  class="choose-button" value="<%=phieuDangKyDays.get(i).getPhieuDangKyDayId()%>"
                                    onclick="submitFormChooseClass(this.value)"> Đã chọn </button>
                        </th>
                    <%
                        }
                        else {
                    %>
                        <form id="choose-class-form-id-<%=phieuDangKyDays.get(i).getPhieuDangKyDayId()%>" action="add_temporal_learn_form" method="post">
                            <input type="hidden" name="teach-form-id" value="<%=phieuDangKyDays.get(i).getPhieuDangKyDayId()%>">
                        </form>
                        <th>
                            <button class="choose-button" value="<%=phieuDangKyDays.get(i).getPhieuDangKyDayId()%>"
                                    onclick="submitFormChooseClass(this.value)"> Chọn </button>
                        </th>
                    <%
                        }
                    %>
                </tr>
                <%
                    }
                %>
            </table>

            <%
                }
            %>

        </div>

        <%
            if(request.getAttribute("listTemporalForm")!=null){
                ArrayList<PhieuDangKyHoc> phieuDangKyHocs1 = (ArrayList<PhieuDangKyHoc>) request.getAttribute("listTemporalForm");
        %>
        <div id="credit-register-div-4">
            <div id="list-class-label"> DANH SÁCH MÔN HỌC ĐÃ CHỌN</div>
            <div id="div-save-form">
                <form id="save-learn-form" action="save_learn_form"></form>
                <button id="save-form-btn" onclick="saveLearnedForm()"> Lưu đăng ký</button>
            </div>
            <div id="table-result">
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
                            Học phí
                        </th>
                        <th class="title">
                            Trạng thái
                        </th>
                        <th class="title">
                            Thao tác
                        </th>

                    </tr>
                    <%
                        Double totalCost = 0.0;
                        Integer totalCredit = 0;
                        for(int i = 0 ; i < phieuDangKyHocs1.size(); i++){
                            totalCost += 430000*phieuDangKyHocs1.get(i).getPhieuDangKyDay().getListLopHocPhan().get(0).getMonHoc().getSoTinChi();
                            totalCredit += phieuDangKyHocs1.get(i).getPhieuDangKyDay().getListLopHocPhan().get(0).getMonHoc().getSoTinChi();
                    %>
                    <tr>
                        <th><%=phieuDangKyHocs1.get(i).getPhieuDangKyDay().getListLopHocPhan().get(0).getMonHoc().getMaMon()%></th>
                        <th><%=phieuDangKyHocs1.get(i).getPhieuDangKyDay().getListLopHocPhan().get(0).getMonHoc().getTenMon()%></th>
                        <th><%=phieuDangKyHocs1.get(i).getPhieuDangKyDay().getNhomLop()%></th>
                        <th><%=phieuDangKyHocs1.get(i).getPhieuDangKyDay().getListLopHocPhan().get(0).getMonHoc().getSoTinChi()%></th>
                        <th><%=430000*phieuDangKyHocs1.get(i).getPhieuDangKyDay().getListLopHocPhan().get(0).getMonHoc().getSoTinChi()%></th>
                        <th>Chưa lưu vào CSDL</th>
                        <form id="change-status-class-form-<%=phieuDangKyHocs1.get(i).getPhieuDangKyHocId()%>" action="change_status_class">
                            <input type="hidden" name="learn-form-id" value="<%=phieuDangKyHocs1.get(i).getPhieuDangKyHocId()%>">
                        </form>
                        <th>
                            <button value="<%=phieuDangKyHocs1.get(i).getPhieuDangKyHocId()%>" class="student-change-status-class-btn"
                                    onclick="submitFormChangeStatusClass(this.value)"> Xóa </button>
                        </th>
                    </tr>
                    <%
                        }
                    %>
                </table>
            </div>
            <div id="total-cost"> <%= "Tổng tiền : " + totalCost + " (đồng)" %></div>
            <input id="total-credit" type="hidden" value="<%=totalCredit%>">
        </div>
        <%
            }
        %>
    </div>
    <%@include file="resource/layout/footer.jsp"%>
    <script type="text/javascript" src="js/base.js"></script>
    <script type="text/javascript" src="js/student-search-subject.js"></script>
    <script type="text/javascript" src="js/student-change-status-class.js"></script>
    <script type="text/javascript" src="js/student-choose-class.js"></script>
    <script type="text/javascript" src="js/student-save-learned-form.js"></script>
</body>
</html>
