package test.servlet.sinhvien;

import api.servlet.sinhvien.SearchStudentByCodeServlet;
import org.junit.Assert;
import org.junit.Test;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SearchStudentByCodeServletTest {

    @Test
    public void testDoGet() throws Exception{
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        SearchStudentByCodeServlet servlet = new SearchStudentByCodeServlet();
        when(request.getParameter("maSV")).thenReturn("B16DCCN013");
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        when(response.getWriter()).thenReturn(pw);
        servlet.doGet(request,response);
        String result = sw.getBuffer().toString().trim();
        Assert.assertEquals(result,"{\"code\":\"00\",\"message\":\"Success\",\"data\":[{\"sinhVienId\":2,\"tblNguoiDungId\":4,\"maSV\":\"B16DCCN013\",\"khoa\":\"CNTT\",\"nganh\":\"CNPM\",\"gpa\":3.5,\"stcTichLuy\":114,\"hocLuc\":\"Gioi\",\"lop\":\"D16CNPM2\"}]}");
    }
}