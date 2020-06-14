package api.servlet.sinhvien;

import api.model.entity.SinhVien;
import api.service.SinhVienService;
import api.service.impl.SinhVienServiceImpl;
import api.servlet.AbstractServlet;
import common.constant.FPErrorCode;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class SearchStudentByCodeServlet extends AbstractServlet {

    private SinhVienService service = new SinhVienServiceImpl();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            String code = request.getParameter("maSV");
            List<SinhVien> sinhViens = service.searchByCode(code);
            response.getWriter().print(toResponse(sinhViens));
        } catch (Exception e) {
            response.getWriter().print(toErrorResponse(FPErrorCode.SEARCH_FAIL, e.getMessage()));
        }
    }

}
