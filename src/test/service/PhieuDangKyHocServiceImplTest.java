package test.service;

import api.model.connectdb.ConnectDB;
import api.model.entity.PhieuDangKyHoc;
import api.service.PhieuDangKyDayService;
import api.service.PhieuDangKyHocService;
import api.service.SinhVienService;
import api.service.impl.PhieuDangKyDayServiceImpl;
import api.service.impl.PhieuDangKyHocServiceImpl;
import api.service.impl.SinhVienServiceImpl;
import common.exception.FPException;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class PhieuDangKyHocServiceImplTest {

    private PhieuDangKyHocService service = new PhieuDangKyHocServiceImpl();

    private SinhVienService sinhVienService = new SinhVienServiceImpl();

    private PhieuDangKyDayService phieuDangKyDayService = new PhieuDangKyDayServiceImpl();

    private Connection conn = ConnectDB.getInstance().getConnection();

    @Test
    public void create() throws FPException {
        PhieuDangKyHoc phieuDangKyHoc = new PhieuDangKyHoc(null,sinhVienService.findById(1).get(),phieuDangKyDayService.findById(1).get(),"test",430000.0,"LEARNING");
        try {
            conn.setAutoCommit(false);
            Optional<PhieuDangKyHoc> opt = service.create(phieuDangKyHoc);
            Assert.assertEquals(1,opt.get().getSinhVien().getSinhVienId().longValue());
            Assert.assertEquals(1,opt.get().getPhieuDangKyDay().getPhieuDangKyDayId().longValue());
            Assert.assertEquals("LEARNING",opt.get().getTrangThai());
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.rollback();
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Test(expected = FPException.DuplicateEntityException.class)
    public void createException() throws FPException{
        PhieuDangKyHoc phieuDangKyHoc = new PhieuDangKyHoc(36,sinhVienService.findById(1).get(),phieuDangKyDayService.findById(1).get(),"test",430000.0,"LEARNING");
        Optional<PhieuDangKyHoc> opt = service.create(phieuDangKyHoc);
    }

    @Test
    public void findById() throws FPException {
        Optional<PhieuDangKyHoc> opt = service.findById(36);
        Assert.assertTrue(opt.isPresent());
        Assert.assertEquals(4,opt.get().getSinhVien().getSinhVienId().longValue());
        Assert.assertEquals(3,opt.get().getPhieuDangKyDay().getPhieuDangKyDayId().longValue());
        Assert.assertEquals("Fri Jun 12 15:52:42 ICT 2020",opt.get().getThoiGianDangKy());
        Assert.assertEquals("CANCELED",opt.get().getTrangThai());
    }

    @Test(expected = FPException.NotFoundEntityException.class)
    public void findByIdException() throws FPException{
        Optional<PhieuDangKyHoc> opt = service.findById(100);
    }

    @Test
    public void update() throws FPException {
        PhieuDangKyHoc phieuDangKyHoc = new PhieuDangKyHoc(null,sinhVienService.findById(1).get(),phieuDangKyDayService.findById(1).get(),"test",430000.0,"LEARNING");
        Integer id = 36;
        try {
            conn.setAutoCommit(false);
            Optional<PhieuDangKyHoc> opt = service.update(id,phieuDangKyHoc);
            Assert.assertTrue(opt.isPresent());
            Assert.assertEquals(1,opt.get().getSinhVien().getSinhVienId().longValue());
            Assert.assertEquals(1,opt.get().getPhieuDangKyDay().getPhieuDangKyDayId().longValue());
            Assert.assertEquals("LEARNING",opt.get().getTrangThai());
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.rollback();
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Test(expected = FPException.NotFoundEntityException.class)
    public void updateException() throws FPException{
        PhieuDangKyHoc phieuDangKyHoc = new PhieuDangKyHoc(null,sinhVienService.findById(1).get(),phieuDangKyDayService.findById(1).get(),"test",430000.0,"LEARNING");
        Integer id = 100;
        Optional<PhieuDangKyHoc> opt = service.update(id,phieuDangKyHoc);
    }

    @Test
    public void searchAvailableForm() {
        List<PhieuDangKyHoc> learnForms = service.searchAvailableForm("B16DCCN013",20202,"LEARNED");
        Assert.assertNotNull(learnForms);
        Assert.assertEquals(5,learnForms.size());
    }
}