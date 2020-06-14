package web.servlet;

import api.model.entity.MonHoc;
import api.model.entity.PhieuDangKyDay;
import api.model.entity.PhieuDangKyHoc;
import com.google.gson.reflect.TypeToken;
import common.constant.FPConstant;
import common.constant.FPErrorCode;
import common.dto.response.FPResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentChangeStatusClassServlet extends AbstractServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("customer-account-id") == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        Integer id = Integer.parseInt(request.getParameter("learn-form-id"));
        String code = (String) session.getAttribute("student-code");
        Integer semester = 20202;

        String url = FPConstant.Url.BASE_URL_API_LEARN_FORM+"temporal/";
        String url1 = FPConstant.Url.BASE_URL_API_LEARN_FORM;
        List<String> params = new ArrayList<>();
        params.add("maSV=" + code);
        params.add("hoc_ky=" + semester);
        String json = invokeService.get(url, params);
        FPResponse<List<PhieuDangKyHoc>> fpResponse = gson.fromJson(json, new TypeToken<FPResponse<List<PhieuDangKyHoc>>>(){}.getType());
        for (PhieuDangKyHoc dangKyHoc : fpResponse.getData()){
            if (dangKyHoc.getPhieuDangKyHocId().equals(id)){
                dangKyHoc.setTrangThai(FPConstant.FormStatus.CANCELED);
                String json1 = invokeService.put(url1,dangKyHoc,dangKyHoc.getPhieuDangKyHocId());
                FPResponse<PhieuDangKyHoc> fpResponse1 = gson.fromJson(json1, new TypeToken<FPResponse<PhieuDangKyHoc>>(){}.getType());
            }
        }


        String url2 = FPConstant.Url.BASE_URL_API_SUBJECT+"search_by_semester/";
        String falculty = "CNPM";
        List<String> params2 = new ArrayList<>();
        params2.add("bo_mon="+falculty);
        params2.add("hoc_ky="+semester);
        String json2 = invokeService.get(url2, params2);
        FPResponse<List<MonHoc>> fpResponse2 = gson.fromJson(json2, new TypeToken<FPResponse<List<MonHoc>>>(){}.getType());
        List<MonHoc>  monHocs = fpResponse2.getData();

        String url3 = FPConstant.Url.BASE_URL_API_LEARN_FORM+"temporal/";
        List<String> params3 = new ArrayList<>();
        params3.add("maSV=" + code);
        params3.add("hoc_ky=" + semester);
        String json3 = invokeService.get(url3, params3);
        FPResponse<List<PhieuDangKyHoc>> fpResponse3 = gson.fromJson(json3, new TypeToken<FPResponse<List<PhieuDangKyHoc>>>(){}.getType());
        List<PhieuDangKyHoc> phieuDangKyHocs = fpResponse3.getData();

        if (!fpResponse.getCode().equals(FPErrorCode.SUCCESS)) {
            response.sendRedirect(FPConstant.Url.BASE_WEB_URL + "server_error.jsp");
            return;
        }
        else {
            request.setAttribute("listSubject", monHocs);
            request.setAttribute("listLearnForm",phieuDangKyHocs);
            response.sendRedirect("credit_register.jsp");
        }
    }

}
