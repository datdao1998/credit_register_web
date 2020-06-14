package web.servlet;

import api.model.entity.*;
import com.google.gson.reflect.TypeToken;
import common.constant.FPConstant;
import common.constant.FPErrorCode;
import common.dto.response.FPResponse;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StudentAddTemporalLearnFormServlet extends AbstractServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        if (session.getAttribute("customer-account-id") == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        String check = "oke";

        //getPhieuDangKyDay
        Integer id = Integer.parseInt(request.getParameter("teach-form-id"));
        String url = FPConstant.Url.BASE_URL_API_TEACH_FORM+id;
        List<String> params = new ArrayList<>();
        String json = invokeService.get(url,params);
        FPResponse<PhieuDangKyDay> fpResponse = gson.fromJson(json, new TypeToken<FPResponse<PhieuDangKyDay>>(){}.getType());
        //getSinhVien
        String studentCode = (String) session.getAttribute("student-code");
        String url1 = FPConstant.Url.BASE_URL_API_STUDENT;
        List<String> param1 = new ArrayList<>();
        param1.add("maSV=" + studentCode);
        String json1 = invokeService.get(url1,param1);
        FPResponse<List<SinhVien>> fpResponse1 = gson.fromJson(json1, new TypeToken<FPResponse<List<SinhVien>>>(){}.getType());

        //checkCalendar
        Integer semester = 20202;
        String url3 = FPConstant.Url.BASE_URL_API_LEARN_FORM+"temporal/";
        List<String> params3 = new ArrayList<>();
        params3.add("maSV="+studentCode);
        params3.add("hoc_ky="+ semester);
        String json3 = invokeService.get(url3, params3);
        FPResponse<List<PhieuDangKyHoc>> fpResponse3 = gson.fromJson(json3, new TypeToken<FPResponse<List<PhieuDangKyHoc>>>(){}.getType());
        List<PhieuDangKyHoc> phieuDangKyHocs = fpResponse3.getData();
        List<PhieuDangKyHoc> listTemporalForm = phieuDangKyHocs.stream().distinct().collect(Collectors.toList());

        //check
        Integer totalCredit = 0;
        for(PhieuDangKyHoc phieuDangKyHoc:listTemporalForm){
            totalCredit += phieuDangKyHoc.getPhieuDangKyDay().getListLopHocPhan().get(0).getMonHoc().getSoTinChi();
            if(phieuDangKyHoc.getPhieuDangKyDay().getListLopHocPhan().get(0).getMonHoc().equals(fpResponse.getData().getListLopHocPhan().get(0).getMonHoc()))
                check = "Duplicate Subject";
            else{
                List<LopHocPhan> courseTemp = fpResponse.getData().getListLopHocPhan();
                List<LopHocPhan> course = phieuDangKyHoc.getPhieuDangKyDay().getListLopHocPhan();
                for(LopHocPhan lhp1: course){
                    for (LopHocPhan lhp2: courseTemp){
                        if(lhp1.getKipHoc().equals(lhp2.getKipHoc())) check = "Duplicate Calendar";
                    }
                }
            }
        }

        String url4 = FPConstant.Url.BASE_URL_API_SUBJECT+"search_by_semester/";
        String falculty = "CNPM";
        List<String> params4 = new ArrayList<>();
        params4.add("bo_mon="+falculty);
        params4.add("hoc_ky="+semester);
        String json4 = invokeService.get(url4, params4);
        FPResponse<List<MonHoc>> fpResponse4 = gson.fromJson(json4, new TypeToken<FPResponse<List<MonHoc>>>(){}.getType());
        List<MonHoc>  monHocs = fpResponse4.getData();

        if (check.equals("oke")){
            PhieuDangKyHoc phieuDangKyHoc = new PhieuDangKyHoc();
            phieuDangKyHoc.setTrangThai(FPConstant.FormStatus.LEARNING);
            phieuDangKyHoc.setHocPhi(430000.00);
            phieuDangKyHoc.setPhieuDangKyDay(fpResponse.getData());
            phieuDangKyHoc.setSinhVien(fpResponse1.getData().get(0));
            phieuDangKyHoc.setThoiGianDangKy(new java.util.Date().toString());
            System.out.println(phieuDangKyHoc);
            String url2 = FPConstant.Url.BASE_URL_API_LEARN_FORM;
            String json2 = invokeService.post(url2,phieuDangKyHoc);
            FPResponse<PhieuDangKyHoc> fpResponse2 = gson.fromJson(json2, new TypeToken<FPResponse<PhieuDangKyHoc>>(){}.getType());
            listTemporalForm.add(phieuDangKyHoc);
        }

        System.out.println(check);
        RequestDispatcher dispatcher = request.getRequestDispatcher("credit_register.jsp");
        request.setAttribute("listTemporalForm", listTemporalForm);
        request.setAttribute("checkCalendar",check);
        request.setAttribute("totalCredit",totalCredit.toString());
        request.setAttribute("listSubject",monHocs);
        dispatcher.forward(request, response);
    }
}
