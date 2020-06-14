package test.servlet.nguoidung;

import api.servlet.nguoidung.SearchAndCreateUserServlet;
import org.junit.Assert;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SearchAndCreateUserServletTest {

    @Test
    public void testDoGet() throws IOException, ServletException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        SearchAndCreateUserServlet servlet = new SearchAndCreateUserServlet();
        when(request.getParameter("ten_dang_nhap")).thenReturn("B16DCCN059");
        when(request.getParameter("ho_ten")).thenReturn("Dao Quoc Dat");
        when(request.getParameter("vai_tro")).thenReturn("SV");
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        when(response.getWriter()).thenReturn(pw);
        servlet.doGet(request,response);
        String result = sw.getBuffer().toString().trim();
        Assert.assertEquals(result,"{\"code\":\"00\",\"message\":\"Success\",\"data\":[{\"nguoiDungId\":3,\"tenDangNhap\":\"B16DCCN059\",\"matKhau\":\"ede47bfb33bd3b3dead7ddb34315ecebbd59b18d\",\"hoTen\":\"Dao Quoc Dat\",\"ngaySinh\":\"22/08/1998\",\"gioiTinh\":\"Nam\",\"diaChi\":\"Thai Binh\",\"vaiTro\":\"SV\"}]}");
    }

    @Test
    public void testDoPost() {
    }
}