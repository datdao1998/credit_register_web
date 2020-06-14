package api.servlet.lophocphan;

import api.model.entity.LopHocPhan;
import api.service.LopHocPhanService;
import api.service.impl.LopHocPhanServiceImpl;
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

public class UpdateAndGetClassServlet extends AbstractServlet {

    private LopHocPhanService service = new LopHocPhanServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            Integer id = Integer.parseInt(getPath(request));
            Optional<LopHocPhan> opt = service.findById(id);
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
