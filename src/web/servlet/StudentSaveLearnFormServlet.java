package web.servlet;

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

public class StudentSaveLearnFormServlet extends AbstractServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("customer-account-id") == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        Integer semester = 20202;

        //cancel old learn form
        String code = (String) session.getAttribute("student-code");
        String url = FPConstant.Url.BASE_URL_API_LEARN_FORM+"available/";
        String url1 = FPConstant.Url.BASE_URL_API_LEARN_FORM;
        String url2 = FPConstant.Url.BASE_URL_API_TEACH_FORM;
        List<String> params = new ArrayList<>();
        params.add("maSV=" + code);
        params.add("hoc_ky=" + semester);
        String json = invokeService.get(url, params);
        FPResponse<List<PhieuDangKyHoc>> fpResponse = gson.fromJson(json, new TypeToken<FPResponse<List<PhieuDangKyHoc>>>(){}.getType());

        for (PhieuDangKyHoc dangKyHoc : fpResponse.getData()){
            dangKyHoc.setTrangThai(FPConstant.FormStatus.CANCELED);
            String json1 = invokeService.put(url1,dangKyHoc,dangKyHoc.getPhieuDangKyHocId());
            FPResponse<PhieuDangKyHoc> fpResponse1 = gson.fromJson(json1, new TypeToken<FPResponse<PhieuDangKyHoc>>(){}.getType());
            PhieuDangKyDay dangKyDay = dangKyHoc.getPhieuDangKyDay();
            dangKyDay.setSoSinhVienDangKy(dangKyDay.getSoSinhVienDangKy()-1);
            String json2 = invokeService.put(url2,dangKyDay,dangKyDay.getPhieuDangKyDayId());
            FPResponse<PhieuDangKyDay> fpResponse2 = gson.fromJson(json2, new TypeToken<FPResponse<PhieuDangKyDay>>(){}.getType());
        }

        //save new form
        String url3 = FPConstant.Url.BASE_URL_API_LEARN_FORM+"temporal/";
        String url4 = FPConstant.Url.BASE_URL_API_LEARN_FORM;
        String url5 = FPConstant.Url.BASE_URL_API_TEACH_FORM;
        List<String> params3 = new ArrayList<>();
        params3.add("maSV=" + code);
        params3.add("hoc_ky=" + semester);
        String json3 = invokeService.get(url3, params3);
        FPResponse<List<PhieuDangKyHoc>> fpResponse3 = gson.fromJson(json3, new TypeToken<FPResponse<List<PhieuDangKyHoc>>>(){}.getType());

        for (PhieuDangKyHoc dangKyHoc : fpResponse3.getData()){
            dangKyHoc.setTrangThai(FPConstant.FormStatus.LEARNED);
            String json4 = invokeService.put(url4,dangKyHoc,dangKyHoc.getPhieuDangKyHocId());
            FPResponse<PhieuDangKyHoc> fpResponse4 = gson.fromJson(json4, new TypeToken<FPResponse<PhieuDangKyHoc>>(){}.getType());
            PhieuDangKyDay dangKyDay = dangKyHoc.getPhieuDangKyDay();
            dangKyDay.setSoSinhVienDangKy(dangKyDay.getSoSinhVienDangKy()+1);
            String json5 = invokeService.put(url5,dangKyDay,dangKyDay.getPhieuDangKyDayId());
            FPResponse<PhieuDangKyDay> fpResponse5 = gson.fromJson(json5, new TypeToken<FPResponse<PhieuDangKyDay>>(){}.getType());
        }


        if (!fpResponse.getCode().equals(FPErrorCode.SUCCESS)) {
            response.sendRedirect(FPConstant.Url.BASE_WEB_URL + "server_error.jsp");
            return;
        }
        else {
            response.sendRedirect("view_tkb");
            return;
        }
    }
}
