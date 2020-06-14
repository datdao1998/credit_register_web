package api.servlet.kiphoc;

import api.model.entity.KipHoc;
import api.service.KipHocService;
import api.service.impl.KipHocServiceImpl;
import api.servlet.AbstractServlet;
import common.constant.FPErrorCode;
import common.constant.FPMessage;
import common.exception.FPException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class UpdateAndGetSlotServlet extends AbstractServlet {


    private KipHocService service = new KipHocServiceImpl();

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            KipHoc kipHoc = gson.fromJson(readRequestBody(request), KipHoc.class);
            Integer id = Integer.parseInt(getPath(request));
            Optional<KipHoc> opt = service.update(id, kipHoc);
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
            Optional<KipHoc> opt = service.findById(id);
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
