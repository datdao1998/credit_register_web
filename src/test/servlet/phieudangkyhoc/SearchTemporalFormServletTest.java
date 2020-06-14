package test.servlet.phieudangkyhoc;


import api.servlet.phieudangkyhoc.SearchTemporalFormServlet;
import org.junit.Assert;
import org.junit.Test;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SearchTemporalFormServletTest {

    @Test
    public void testDoGet() throws Exception{
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        SearchTemporalFormServlet servlet = new SearchTemporalFormServlet();
        when(request.getParameter("maSV")).thenReturn("B16DCCN059");
        when(request.getParameter("hoc_ky")).thenReturn(String.valueOf(20202));
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        when(response.getWriter()).thenReturn(pw);
        servlet.doGet(request,response);
        String result = sw.getBuffer().toString().trim();
        Assert.assertEquals(result,"{\"code\":\"00\",\"message\":\"Success\",\"data\":[{\"phieuDangKyHocId\":35,\"sinhVien\":{\"sinhVienId\":1,\"tblNguoiDungId\":3,\"maSV\":\"B16DCCN059\",\"khoa\":\"CNTT\",\"nganh\":\"CNPM\",\"gpa\":3.45,\"stcTichLuy\":114,\"hocLuc\":\"Gioi\",\"lop\":\"D16CNPM1\"},\"phieuDangKyDay\":{\"phieuDangKyDayId\":4,\"siSo\":40,\"soSinhVienDangKy\":1,\"maLop\":\"D16-103\",\"nhomLop\":4,\"listLopHocPhan\":[{\"id\":5,\"kipHoc\":{\"kipHocId\":2,\"gioBatDau\":9,\"gioKetThuc\":11,\"thu\":2},\"giangVien\":{\"giangVienId\":1,\"tblNguoiDungId\":1,\"tenGiangVien\":\"Hoang Phi Dung\",\"khoa\":\"Co ban\",\"nganh\":\"Toan\",\"hocVi\":\"Khong\",\"boMon\":\"Dai So\"},\"monHoc\":{\"monHocId\":1,\"maMon\":\"INT1448\",\"tenMon\":\"Phat trien phan mem huong dich vu\",\"soTinChi\":3,\"soTietLT\":15,\"soTietBT\":15,\"soTietTH\":2,\"hsChuyenCan\":0.1,\"hsKiemTra\":0.0,\"hsThucHanh\":0.2,\"hsBaiTapLon\":0.2,\"hsCuoiKy\":0.5,\"monDK\":\"Khong\",\"boMon\":\"CNPM\",\"khoa\":\"CNPM\"},\"phongHoc\":{\"phongHocId\":2,\"tenPhong\":\"202A2\",\"viTri\":\"tang 2\",\"sucChua\":45,\"moTa\":\"Khong\"},\"tuanBatDau\":1,\"tuanKetThuc\":15,\"phieuDangKyDayId\":4}],\"hocKy\":20202},\"thoiGianDangKy\":\"Wed Jun 10 15:33:28 ICT 2020\",\"hocPhi\":430000.0,\"trangThai\":\"LEARNING\"}]}");
    }
}