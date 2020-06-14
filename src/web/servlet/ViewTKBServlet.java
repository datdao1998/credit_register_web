package web.servlet;

import api.model.entity.PhieuDangKyHoc;
import com.google.gson.reflect.TypeToken;
import common.constant.FPConstant;
import common.constant.FPErrorCode;
import common.dto.response.FPResponse;
import common.util.FPUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ViewTKBServlet extends AbstractServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("customer-account-id") == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        String code = (String) session.getAttribute("student-code");
        System.out.println(code);
        String url = FPConstant.Url.BASE_URL_API_LEARN_FORM+"available/";
        System.out.println(url);
        Integer semester = 20202;
        List<String> params = new ArrayList<>();
        params.add("maSV=" + code);
        params.add("hoc_ky=" + semester);
        String json = invokeService.get(url, params);
        FPResponse<List<PhieuDangKyHoc>> fpResponse = gson.fromJson(json, new TypeToken<FPResponse<List<PhieuDangKyHoc>>>(){}.getType());
        if (!fpResponse.getCode().equals(FPErrorCode.SUCCESS)) {
            response.sendRedirect(FPConstant.Url.BASE_WEB_URL + "server_error.jsp");
            return;
        }

        else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("view_tkb.jsp");
            System.out.println("size send = " + fpResponse.getData().size());
            request.setAttribute("listLearnForm", fpResponse.getData());
            dispatcher.forward(request, response);
        }


    }
}
