package api.servlet.monhoc;

import api.model.entity.MonHoc;
import api.service.MonHocService;
import api.service.impl.MonHocServiceImpl;
import api.servlet.AbstractServlet;
import common.constant.FPErrorCode;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class SearchSubjectByCodeServlet extends AbstractServlet {

    private MonHocService service = new MonHocServiceImpl();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            String subjectCode = request.getParameter("ma_mon");
            Optional<MonHoc> opt = service.searchByCode(subjectCode);
            response.getWriter().print(toResponse(opt));
        } catch (Exception e) {
            response.getWriter().print(toErrorResponse(FPErrorCode.SEARCH_FAIL, e.getMessage()));
        }
    }

}
