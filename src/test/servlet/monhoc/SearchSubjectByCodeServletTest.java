package test.servlet.monhoc;

import api.servlet.monhoc.SearchSubjectByCodeServlet;
import org.junit.Assert;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SearchSubjectByCodeServletTest {

    @Test
    public void testDoGet() throws Exception{
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        SearchSubjectByCodeServlet servlet = new SearchSubjectByCodeServlet();
        when(request.getParameter("ma_mon")).thenReturn("BAS1234");
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        when(response.getWriter()).thenReturn(pw);
        servlet.doGet(request,response);
        String result = sw.getBuffer().toString().trim();
        Assert.assertEquals(result,"{\"code\":\"00\",\"message\":\"Success\",\"data\":{\"monHocId\":6,\"maMon\":\"BAS1234\",\"tenMon\":\"Phat trien ung dung cho cac thiet bi di dong\",\"soTinChi\":4,\"soTietLT\":20,\"soTietBT\":15,\"soTietTH\":8,\"hsChuyenCan\":0.1,\"hsKiemTra\":0.1,\"hsThucHanh\":0.2,\"hsBaiTapLon\":0.0,\"hsCuoiKy\":0.6,\"monDK\":\"Khong\",\"boMon\":\"CNPM\",\"khoa\":\"CB\"}}");
    }
}