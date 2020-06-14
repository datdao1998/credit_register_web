package test.servlet.monhoc;


import api.servlet.monhoc.SearchSubjectByNameServlet;
import org.junit.Assert;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.PrintWriter;
import java.io.StringWriter;

public class SearchSubjectByNameServletTest {

    @Test
    public void testDoGet() throws Exception{
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        SearchSubjectByNameServlet servlet = new SearchSubjectByNameServlet();
        when(request.getParameter("ten_mon")).thenReturn("Xay dung cac he thong nhung");
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        when(response.getWriter()).thenReturn(pw);
        servlet.doGet(request,response);
        String result = sw.getBuffer().toString().trim();
        Assert.assertEquals(result,"{\"code\":\"00\",\"message\":\"Success\",\"data\":[{\"monHocId\":5,\"maMon\":\"INT1111\",\"tenMon\":\"Xay dung cac he thong nhung\",\"soTinChi\":3,\"soTietLT\":10,\"soTietBT\":20,\"soTietTH\":0,\"hsChuyenCan\":0.1,\"hsKiemTra\":0.1,\"hsThucHanh\":0.0,\"hsBaiTapLon\":0.1,\"hsCuoiKy\":0.7,\"monDK\":\"Khong\",\"boMon\":\"CNPM\",\"khoa\":\"CB\"}]}");
    }
}