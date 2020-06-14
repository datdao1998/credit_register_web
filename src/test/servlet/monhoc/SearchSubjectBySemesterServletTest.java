package test.servlet.monhoc;

import api.servlet.monhoc.SearchSubjectBySemesterServlet;
import org.junit.Assert;
import org.junit.Test;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SearchSubjectBySemesterServletTest {

    @Test
    public void testDoGet() throws Exception{
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        SearchSubjectBySemesterServlet servlet = new SearchSubjectBySemesterServlet();
        when(request.getParameter("bo_mon")).thenReturn("CNPM");
        when(request.getParameter("hoc_ky")).thenReturn(String.valueOf(20202));
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        when(response.getWriter()).thenReturn(pw);
        servlet.doGet(request,response);
        String result = sw.getBuffer().toString().trim();
        Assert.assertEquals(result,"{\"code\":\"00\",\"message\":\"Success\",\"data\":[{\"monHocId\":1,\"maMon\":\"INT1448\",\"tenMon\":\"Phat trien phan mem huong dich vu\",\"soTinChi\":3,\"soTietLT\":15,\"soTietBT\":15,\"soTietTH\":2,\"hsChuyenCan\":0.1,\"hsKiemTra\":0.0,\"hsThucHanh\":0.2,\"hsBaiTapLon\":0.2,\"hsCuoiKy\":0.5,\"monDK\":\"Khong\",\"boMon\":\"CNPM\",\"khoa\":\"CNPM\"},{\"monHocId\":3,\"maMon\":\"INT1408\",\"tenMon\":\"Chuyen de Cong nghe phan mem\",\"soTinChi\":1,\"soTietLT\":5,\"soTietBT\":5,\"soTietTH\":0,\"hsChuyenCan\":0.1,\"hsKiemTra\":0.1,\"hsThucHanh\":0.1,\"hsBaiTapLon\":0.0,\"hsCuoiKy\":0.7,\"monDK\":\"Khong\",\"boMon\":\"CNPM\",\"khoa\":\"CNPM\"},{\"monHocId\":4,\"maMon\":\"INT1416\",\"tenMon\":\"Dam bao chat luong phan mem\",\"soTinChi\":3,\"soTietLT\":20,\"soTietBT\":15,\"soTietTH\":2,\"hsChuyenCan\":0.1,\"hsKiemTra\":0.1,\"hsThucHanh\":0.1,\"hsBaiTapLon\":0.2,\"hsCuoiKy\":0.5,\"monDK\":\"PTTKHT\",\"boMon\":\"CNPM\",\"khoa\":\"CNPM\"},{\"monHocId\":5,\"maMon\":\"INT1111\",\"tenMon\":\"Xay dung cac he thong nhung\",\"soTinChi\":3,\"soTietLT\":10,\"soTietBT\":20,\"soTietTH\":0,\"hsChuyenCan\":0.1,\"hsKiemTra\":0.1,\"hsThucHanh\":0.0,\"hsBaiTapLon\":0.1,\"hsCuoiKy\":0.7,\"monDK\":\"Khong\",\"boMon\":\"CNPM\",\"khoa\":\"CB\"},{\"monHocId\":6,\"maMon\":\"BAS1234\",\"tenMon\":\"Phat trien ung dung cho cac thiet bi di dong\",\"soTinChi\":4,\"soTietLT\":20,\"soTietBT\":15,\"soTietTH\":8,\"hsChuyenCan\":0.1,\"hsKiemTra\":0.1,\"hsThucHanh\":0.2,\"hsBaiTapLon\":0.0,\"hsCuoiKy\":0.6,\"monDK\":\"Khong\",\"boMon\":\"CNPM\",\"khoa\":\"CB\"},{\"monHocId\":2,\"maMon\":\"INT1427\",\"tenMon\":\"Kien truc va thiet ke phan mem\",\"soTinChi\":3,\"soTietLT\":15,\"soTietBT\":15,\"soTietTH\":2,\"hsChuyenCan\":0.1,\"hsKiemTra\":0.1,\"hsThucHanh\":0.1,\"hsBaiTapLon\":0.2,\"hsCuoiKy\":0.5,\"monDK\":\"PTTKHT\",\"boMon\":\"CNPM\",\"khoa\":\"CNPM\"}]}");
    }
}