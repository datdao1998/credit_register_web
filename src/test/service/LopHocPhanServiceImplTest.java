package test.service;

import api.model.connectdb.ConnectDB;
import api.model.entity.GiangVien;
import api.model.entity.LopHocPhan;
import api.service.*;
import api.service.impl.*;
import api.servlet.AbstractServlet;
import common.exception.FPException;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import static org.junit.Assert.*;

public class LopHocPhanServiceImplTest {

    private LopHocPhanService service = new LopHocPhanServiceImpl();

    private KipHocService kipHocService = new KipHocServiceImpl();

    private PhongHocService phongHocService = new PhongHocServiceImpl();

    private GiangVienService giangVienService = new GiangVienServiceImpl();

    private MonHocService monHocService = new MonHocServiceImpl();

    private Connection conn = ConnectDB.getInstance().getConnection();

    @Test
    public void findById() throws FPException {
        Optional<LopHocPhan> opt = service.findById(5);
        Assert.assertTrue(opt.isPresent());
        Assert.assertEquals(2,opt.get().getKipHoc().getKipHocId().longValue());
        Assert.assertEquals(1,opt.get().getGiangVien().getGiangVienId().longValue());
        Assert.assertEquals(1,opt.get().getMonHoc().getMonHocId().longValue());
        Assert.assertEquals(2,opt.get().getPhongHoc().getPhongHocId().longValue());
        Assert.assertEquals(4,opt.get().getPhieuDangKyDayId().longValue());
        Assert.assertEquals(1,opt.get().getTuanBatDau().longValue());
        Assert.assertEquals(15,opt.get().getTuanKetThuc().longValue());
    }

    @Test(expected = FPException.NotFoundEntityException.class)
    public void findByIdException() throws FPException{
        Optional<LopHocPhan> opt = service.findById(9);
    }

}