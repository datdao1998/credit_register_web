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
import java.util.Optional;

public class UpdateAndGetUserServlet extends AbstractServlet {

    private NguoiDungService service = new NguoiDungServiceImpl();

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            NguoiDung nguoiDung = gson.fromJson(readRequestBody(request), NguoiDung.class);
            Integer id = Integer.parseInt(getPath(request));
            Optional<NguoiDung> opt = service.update(id, nguoiDung);
            if (!opt.isPresent()) {
                response.getWriter().print(toErrorResponse(FPErrorCode.UPDATE_FAIL, FPMessage.UPDATE_FAIL));
                return;
            }
            NguoiDung nguoiDung1 = opt.get();
            nguoiDung1.setMatKhau(null);
            response.getWriter().print(toResponse(nguoiDung1));
        } catch (FPException e) {
            response.getWriter().print(toErrorResponse(FPErrorCode.UPDATE_FAIL, e.getMessage()));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            Integer id = Integer.parseInt(getPath(request));
            Optional<NguoiDung> opt = service.findById(id);
            if (!opt.isPresent()) {
                response.getWriter().print(toErrorResponse(FPErrorCode.GET_FAIL, FPMessage.GET_FAIL));
                return;
            }
            response.getWriter().print(toResponse(opt));
        } catch (FPException e) {
            response.getWriter().print(toErrorResponse(FPErrorCode.GET_FAIL, e.getMessage()));
        }
    }

}
