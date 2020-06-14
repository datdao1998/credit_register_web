package web.servlet;

import api.model.entity.NguoiDung;
import com.google.gson.reflect.TypeToken;
import common.constant.FPConstant;
import common.constant.FPErrorCode;
import common.dto.response.FPResponse;
import common.util.FPUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomerLoginServlet extends AbstractServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("customer-login-input-user-name") == null) {
            response.sendRedirect(FPConstant.Url.BASE_WEB_URL + "customer_login.jsp");
            return;
        }
        String userName = request.getParameter("customer-login-input-user-name");
        userName = userName.toLowerCase();
        String password = request.getParameter("customer-login-input-password");
        password = FPUtils.SHA1Encrypt(password);
        List<String> params = new ArrayList<>();
        params.add("ten_dang_nhap=" + userName);
        String json = invokeService.get(FPConstant.Url.BASE_URL_API_USER, params);
        FPResponse<List<NguoiDung>> fpResponse = gson.fromJson(json, new TypeToken<FPResponse<List<NguoiDung>>>(){}.getType());
        if (fpResponse.getCode().equals(FPErrorCode.SUCCESS)) {
            if (fpResponse.getData().size() == 0) {
                HttpSession session = request.getSession();
                session.setAttribute("customer-account-authenticate", "User name is not existed");
                response.sendRedirect(FPConstant.Url.BASE_WEB_URL + "user_login.jsp");
                return;
            }
            if (password.equals(fpResponse.getData().get(0).getMatKhau()) && fpResponse.getData().get(0).getVaiTro().equals(FPConstant.AccountRole.ACCOUNT_ROLE_STUDENT)) {
                HttpSession session = request.getSession();
                session.setAttribute("customer-account-id", fpResponse.getData().get(0).getNguoiDungId());
                session.setAttribute("student-code",fpResponse.getData().get(0).getTenDangNhap());
                session.setAttribute("customer-account-user-name", "Xin chào " + fpResponse.getData().get(0).getHoTen() + " (" + fpResponse.getData().get(0).getTenDangNhap() +")");
                session.setAttribute("customer-account-name", fpResponse.getData().get(0).getHoTen());
                session.setAttribute("account-role", fpResponse.getData().get(0).getVaiTro());
                session.setAttribute("semester",20202);
                session.removeAttribute("customer-account-authenticate");
                response.sendRedirect(FPConstant.Url.BASE_WEB_URL + "index.jsp");
                return;
            }
            else  {
                HttpSession session = request.getSession();
                session.setAttribute("customer-account-authenticate", "Password is wrong");
                response.sendRedirect(FPConstant.Url.BASE_WEB_URL + "user_login.jsp");
                return;
            }
        }
        else {
            response.sendRedirect(FPConstant.Url.BASE_WEB_URL + "server_error.jsp");
            return;
        }
    }

}
