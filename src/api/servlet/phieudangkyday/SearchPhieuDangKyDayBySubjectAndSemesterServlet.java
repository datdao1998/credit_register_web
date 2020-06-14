package api.servlet.phieudangkyday;

import api.model.entity.PhieuDangKyDay;
import api.service.PhieuDangKyDayService;
import api.service.impl.PhieuDangKyDayServiceImpl;
import api.servlet.AbstractServlet;
import common.constant.FPErrorCode;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SearchPhieuDangKyDayBySubjectAndSemesterServlet extends AbstractServlet {

    private PhieuDangKyDayService service = new PhieuDangKyDayServiceImpl();


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            String  maMon = request.getParameter("ma_mon");
            Integer hocKy = Integer.parseInt(request.getParameter("hoc_ky"));
            List<PhieuDangKyDay> phieuDangKyDays = service.searchBySubjectAndSemester(maMon,hocKy);
            response.getWriter().print(toResponse(phieuDangKyDays));
        } catch (Exception e) {
            response.getWriter().print(toErrorResponse(FPErrorCode.SEARCH_FAIL, e.getMessage()));
        }
    }

}
