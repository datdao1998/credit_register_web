package api.servlet.monhoc;

import api.model.entity.MonHoc;
import api.service.MonHocService;
import api.service.impl.MonHocServiceImpl;
import api.servlet.AbstractServlet;
import common.constant.FPErrorCode;
import common.exception.FPException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchSubjectBySemesterServlet extends AbstractServlet {

    private MonHocService service = new MonHocServiceImpl();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            String  boMon = request.getParameter("bo_mon");
            Integer hocKy = Integer.parseInt(request.getParameter("hoc_ky"));
            List<MonHoc> monHocs = service.searchByFacultyAndSemester(boMon,hocKy);
            response.getWriter().print(toResponse(monHocs));
        } catch (Exception e) {
            response.getWriter().print(toErrorResponse(FPErrorCode.SEARCH_FAIL, e.getMessage()));
        }
    }

}
