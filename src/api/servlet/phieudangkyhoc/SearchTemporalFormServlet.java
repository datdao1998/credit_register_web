package api.servlet.phieudangkyhoc;

import api.model.entity.PhieuDangKyHoc;
import api.service.PhieuDangKyHocService;
import api.service.impl.PhieuDangKyHocServiceImpl;
import api.servlet.AbstractServlet;
import common.constant.FPConstant;
import common.constant.FPErrorCode;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SearchTemporalFormServlet extends AbstractServlet {

    private PhieuDangKyHocService service = new PhieuDangKyHocServiceImpl();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            String maSV = request.getParameter("maSV");
            Integer hocKy = Integer.parseInt(request.getParameter("hoc_ky"));
            String trangThai = FPConstant.FormStatus.LEARNING;
            List<PhieuDangKyHoc> phieuDangKyHocs = service.searchAvailableForm(maSV, hocKy, trangThai);
            response.getWriter().print(toResponse(phieuDangKyHocs));
        } catch (Exception e) {
            response.getWriter().print(toErrorResponse(FPErrorCode.SEARCH_FAIL, e.getMessage()));
        }
    }
}
