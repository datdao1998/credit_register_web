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

public class CreateLearnFormServlet extends AbstractServlet {

    private PhieuDangKyHocService service = new PhieuDangKyHocServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PhieuDangKyHoc phieuDangKyHoc = gson.fromJson(readRequestBody(request), PhieuDangKyHoc.class);
            response.setContentType("text/html;charset=UTF-8");
            Optional<PhieuDangKyHoc> opt = service.create(phieuDangKyHoc);
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
