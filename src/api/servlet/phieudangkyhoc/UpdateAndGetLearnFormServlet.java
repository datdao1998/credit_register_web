package api.servlet.phieudangkyhoc;

import api.model.entity.PhieuDangKyHoc;
import api.service.PhieuDangKyHocService;
import api.service.impl.PhieuDangKyHocServiceImpl;
import api.servlet.AbstractServlet;
import common.constant.FPErrorCode;
import common.constant.FPMessage;
import common.exception.FPException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class UpdateAndGetLearnFormServlet extends AbstractServlet {


    private PhieuDangKyHocService service = new PhieuDangKyHocServiceImpl();

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            PhieuDangKyHoc phieuDangKyHoc = gson.fromJson(readRequestBody(request), PhieuDangKyHoc.class);
            Integer id = Integer.parseInt(getPath(request));
            Optional<PhieuDangKyHoc> opt = service.update(id, phieuDangKyHoc);
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
            Optional<PhieuDangKyHoc> opt = service.findById(id);
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
