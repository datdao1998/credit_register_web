package api.servlet.nguoidung;

import api.model.entity.NguoiDung;
import api.service.NguoiDungService;
import api.service.impl.NguoiDungServiceImpl;
import api.servlet.AbstractServlet;
import common.constant.FPErrorCode;
import common.constant.FPMessage;
import common.exception.FPException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class SearchAndCreateUserServlet extends AbstractServlet {

    private NguoiDungService service = new NguoiDungServiceImpl();


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            String userName = request.getParameter("ten_dang_nhap");
            String name = request.getParameter("ho_ten");
            String role = request.getParameter("vai_tro");
            List<NguoiDung> nguoiDungs = service.search(userName, name, role);
            response.getWriter().print(toResponse(nguoiDungs));
        } catch (Exception e) {
            response.getWriter().print(toErrorResponse(FPErrorCode.SEARCH_FAIL, e.getMessage()));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            NguoiDung nguoiDung = gson.fromJson(readRequestBody(request), NguoiDung.class);
            response.setContentType("text/html;charset=UTF-8");
            Optional<NguoiDung> opt = service.create(nguoiDung);
            if (!opt.isPresent()) {
                response.getWriter().print(toErrorResponse(FPErrorCode.CREATE_FAIL, FPMessage.CREATE_FAIL));
                return;
            }
            response.getWriter().print(toResponse(opt));
        } catch (FPException e) {
            response.getWriter().print(toErrorResponse(FPErrorCode.CREATE_FAIL, e.getMessage()));
        }

    }

}
