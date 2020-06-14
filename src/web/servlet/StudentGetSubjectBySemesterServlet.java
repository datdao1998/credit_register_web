package web.servlet;

import api.model.entity.MonHoc;
import api.model.entity.PhieuDangKyDay;
import api.model.entity.PhieuDangKyHoc;
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

public class StudentGetSubjectBySemesterServlet extends AbstractServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("customer-account-id") == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        String url = FPConstant.Url.BASE_URL_API_SUBJECT+"search_by_semester/";
        Integer semester = 20202;
        String falculty = "CNPM";
        List<String> params = new ArrayList<>();
        params.add("bo_mon="+falculty);
        params.add("hoc_ky="+semester);
        String json = invokeService.get(url, params);
        FPResponse<List<MonHoc>> fpResponse = gson.fromJson(json, new TypeToken<FPResponse<List<MonHoc>>>(){}.getType());
        List<MonHoc>  monHocs = fpResponse.getData();

        String code = (String) session.getAttribute("student-code");
        String url1 = FPConstant.Url.BASE_URL_API_LEARN_FORM+"temporal/";
        List<String> params1 = new ArrayList<>();
        params1.add("maSV=" + code);
        params1.add("hoc_ky=" + semester);
        String json1 = invokeService.get(url1, params1);
        FPResponse<List<PhieuDangKyHoc>> fpResponse1 = gson.fromJson(json1, new TypeToken<FPResponse<List<PhieuDangKyHoc>>>(){}.getType());
        List<PhieuDangKyHoc> phieuDangKyHocs = fpResponse1.getData();

        if (!fpResponse.getCode().equals(FPErrorCode.SUCCESS)) {
            response.sendRedirect(FPConstant.Url.BASE_WEB_URL + "server_error.jsp");
            return;
        }

        else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("credit_register.jsp");
            request.setAttribute("listSubject", monHocs);
            request.setAttribute("listLearnForm",phieuDangKyHocs);
            dispatcher.forward(request, response);
        }


    }
}
