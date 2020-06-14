package api.servlet.phieudangkyday;

import api.model.entity.PhieuDangKyDay;
import api.service.PhieuDangKyDayService;
import api.service.impl.PhieuDangKyDayServiceImpl;
import api.servlet.AbstractServlet;
import common.constant.FPErrorCode;
import common.constant.FPMessage;
import common.exception.FPException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class UpdateAndGetPhieuDangKyDayServlet extends AbstractServlet {

    private PhieuDangKyDayService service = new PhieuDangKyDayServiceImpl();

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            PhieuDangKyDay phieuDangKyDay = gson.fromJson(readRequestBody(request), PhieuDangKyDay.class);
            Integer id = Integer.parseInt(getPath(request));
            Optional<PhieuDangKyDay> opt = service.update(id, phieuDangKyDay);
            if (!opt.isPresent()) {
                response.getWriter().print(toErrorResponse(FPErrorCode.UPDATE_FAIL, FPMessage.UPDATE_FAIL));
                return;
            }
            response.getWriter().print(toResponse(opt));
        } catch (FPException e) {
            response.getWriter().print(toErrorResponse(FPErrorCode.UPDATE_FAIL, e.getMessage()));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            Integer id = Integer.parseInt(getPath(request));
            Optional<PhieuDangKyDay> opt = service.findById(id);
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
