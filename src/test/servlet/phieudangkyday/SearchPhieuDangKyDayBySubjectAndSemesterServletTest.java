package test.servlet.phieudangkyday;

import api.servlet.phieudangkyday.SearchPhieuDangKyDayBySubjectAndSemesterServlet;
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

public class SearchPhieuDangKyDayBySubjectAndSemesterServletTest {

    @Test
    public void testDoGet() throws IOException, ServletException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        SearchPhieuDangKyDayBySubjectAndSemesterServlet servlet = new SearchPhieuDangKyDayBySubjectAndSemesterServlet();
        when(request.getParameter("ma_mon")).thenReturn("INT1448");
        when(request.getParameter("hoc_ky")).thenReturn(String.valueOf(20202));
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        when(response.getWriter()).thenReturn(pw);
        servlet.doGet(request,response);
        String result = sw.getBuffer().toString().trim();
        Assert.assertEquals(result,"{\"code\":\"00\",\"message\":\"Success\",\"data\":[{\"phieuDangKyDayId\":1,\"siSo\":40,\"soSinhVienDangKy\":6,\"maLop\":\"D16-100\",\"nhomLop\":1,\"listLopHocPhan\":[{\"id\":1,\"kipHoc\":{\"kipHocId\":1,\"gioBatDau\":7,\"gioKetThuc\":9,\"thu\":2},\"giangVien\":{\"giangVienId\":2,\"tblNguoiDungId\":2,\"tenGiangVien\":\"Pham Ngoc Anh\",\"khoa\":\"Co ban\",\"nganh\":\"Toan\",\"hocVi\":\"PGS.TS\",\"boMon\":\"Giai tich\"},\"monHoc\":{\"monHocId\":1,\"maMon\":\"INT1448\",\"tenMon\":\"Phat trien phan mem huong dich vu\",\"soTinChi\":3,\"soTietLT\":15,\"soTietBT\":15,\"soTietTH\":2,\"hsChuyenCan\":0.1,\"hsKiemTra\":0.0,\"hsThucHanh\":0.2,\"hsBaiTapLon\":0.2,\"hsCuoiKy\":0.5,\"monDK\":\"Khong\",\"boMon\":\"CNPM\",\"khoa\":\"CNPM\"},\"phongHoc\":{\"phongHocId\":1,\"tenPhong\":\"101A2\",\"viTri\":\"tang 1\",\"sucChua\":40,\"moTa\":\"Khong\"},\"tuanBatDau\":1,\"tuanKetThuc\":15,\"phieuDangKyDayId\":1},{\"id\":2,\"kipHoc\":{\"kipHocId\":3,\"gioBatDau\":12,\"gioKetThuc\":14,\"thu\":2},\"giangVien\":{\"giangVienId\":2,\"tblNguoiDungId\":2,\"tenGiangVien\":\"Pham Ngoc Anh\",\"khoa\":\"Co ban\",\"nganh\":\"Toan\",\"hocVi\":\"PGS.TS\",\"boMon\":\"Giai tich\"},\"monHoc\":{\"monHocId\":1,\"maMon\":\"INT1448\",\"tenMon\":\"Phat trien phan mem huong dich vu\",\"soTinChi\":3,\"soTietLT\":15,\"soTietBT\":15,\"soTietTH\":2,\"hsChuyenCan\":0.1,\"hsKiemTra\":0.0,\"hsThucHanh\":0.2,\"hsBaiTapLon\":0.2,\"hsCuoiKy\":0.5,\"monDK\":\"Khong\",\"boMon\":\"CNPM\",\"khoa\":\"CNPM\"},\"phongHoc\":{\"phongHocId\":1,\"tenPhong\":\"101A2\",\"viTri\":\"tang 1\",\"sucChua\":40,\"moTa\":\"Khong\"},\"tuanBatDau\":1,\"tuanKetThuc\":7,\"phieuDangKyDayId\":1}],\"hocKy\":20202},{\"phieuDangKyDayId\":4,\"siSo\":40,\"soSinhVienDangKy\":1,\"maLop\":\"D16-103\",\"nhomLop\":4,\"listLopHocPhan\":[{\"id\":5,\"kipHoc\":{\"kipHocId\":2,\"gioBatDau\":9,\"gioKetThuc\":11,\"thu\":2},\"giangVien\":{\"giangVienId\":1,\"tblNguoiDungId\":1,\"tenGiangVien\":\"Hoang Phi Dung\",\"khoa\":\"Co ban\",\"nganh\":\"Toan\",\"hocVi\":\"Khong\",\"boMon\":\"Dai So\"},\"monHoc\":{\"monHocId\":1,\"maMon\":\"INT1448\",\"tenMon\":\"Phat trien phan mem huong dich vu\",\"soTinChi\":3,\"soTietLT\":15,\"soTietBT\":15,\"soTietTH\":2,\"hsChuyenCan\":0.1,\"hsKiemTra\":0.0,\"hsThucHanh\":0.2,\"hsBaiTapLon\":0.2,\"hsCuoiKy\":0.5,\"monDK\":\"Khong\",\"boMon\":\"CNPM\",\"khoa\":\"CNPM\"},\"phongHoc\":{\"phongHocId\":2,\"tenPhong\":\"202A2\",\"viTri\":\"tang 2\",\"sucChua\":45,\"moTa\":\"Khong\"},\"tuanBatDau\":1,\"tuanKetThuc\":15,\"phieuDangKyDayId\":4}],\"hocKy\":20202}]}");
    }
}