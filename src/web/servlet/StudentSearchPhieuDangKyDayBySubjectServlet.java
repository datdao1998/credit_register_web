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

public class StudentSearchPhieuDangKyDayBySubjectServlet extends AbstractServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("customer-account-id") == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        String url = FPConstant.Url.BASE_URL_API_TEACH_FORM+"search_teach_form_by_subject/";
        Integer semester = 20202;
        String subjectCode = request.getParameter("subject-input").toString();
        List<String> params = new ArrayList<>();
        params.add("ma_mon="+subjectCode);
        params.add("hoc_ky="+semester);
        String json = invokeService.get(url, params);
        FPResponse<List<PhieuDangKyDay>> fpResponse = gson.fromJson(json, new TypeToken<FPResponse<List<PhieuDangKyDay>>>(){}.getType());

        String url1 = FPConstant.Url.BASE_URL_API_SUBJECT+"search_by_semester/";
        String falculty = "CNPM";
        List<String> params1 = new ArrayList<>();
        params1.add("bo_mon="+falculty);
        params1.add("hoc_ky="+semester);
        String json1 = invokeService.get(url1, params1);
        FPResponse<List<MonHoc>> fpResponse1 = gson.fromJson(json1, new TypeToken<FPResponse<List<MonHoc>>>(){}.getType());

        String code = (String) session.getAttribute("student-code");
        String url2 = FPConstant.Url.BASE_URL_API_LEARN_FORM+"available/";
        List<String> params2 = new ArrayList<>();
        params2.add("maSV=" + code);
        params2.add("hoc_ky=" + semester);
        String json2 = invokeService.get(url2, params2);
        FPResponse<List<PhieuDangKyHoc>> fpResponse2 = gson.fromJson(json2, new TypeToken<FPResponse<List<PhieuDangKyHoc>>>(){}.getType());

        String url3 = FPConstant.Url.BASE_URL_API_LEARN_FORM+"temporal/";
        List<String> params3 = new ArrayList<>();
        params3.add("maSV=" + code);
        params3.add("hoc_ky=" + semester);
        String json3 = invokeService.get(url3, params3);
        FPResponse<List<PhieuDangKyHoc>> fpResponse3 = gson.fromJson(json3, new TypeToken<FPResponse<List<PhieuDangKyHoc>>>(){}.getType());

        if (!fpResponse.getCode().equals(FPErrorCode.SUCCESS)) {
            response.sendRedirect(FPConstant.Url.BASE_WEB_URL + "server_error.jsp");
            return;
        }

        else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("credit_register.jsp");
            request.setAttribute("listTeachForm", fpResponse.getData());
            request.setAttribute("listSubject", fpResponse1.getData());
            request.setAttribute("listLearnForm", fpResponse2.getData());
            request.setAttribute("listTemporalForm",fpResponse3.getData());
            dispatcher.forward(request, response);
        }


    }

}
