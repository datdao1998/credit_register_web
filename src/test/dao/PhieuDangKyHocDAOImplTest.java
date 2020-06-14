package test.dao;

import api.model.connectdb.ConnectDB;
import api.model.dao.PhieuDangKyDayDAO;
import api.model.dao.PhieuDangKyHocDAO;
import api.model.dao.SinhVienDAO;
import api.model.dao.impl.PhieuDangKyDayDAOImpl;
import api.model.dao.impl.PhieuDangKyHocDAOImpl;
import api.model.dao.impl.SinhVienDAOImpl;
import api.model.entity.PhieuDangKyDay;
import api.model.entity.PhieuDangKyHoc;
import api.model.entity.SinhVien;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class PhieuDangKyHocDAOImplTest {

    private PhieuDangKyHocDAO phieuDangKyHocDAO = new PhieuDangKyHocDAOImpl();

    private PhieuDangKyDayDAO phieuDangKyDayDAO = new PhieuDangKyDayDAOImpl();

    private SinhVienDAO sinhVienDAO = new SinhVienDAOImpl();

    private Connection conn = ConnectDB.getInstance().getConnection();

    @Test
    public void getAll() {
        List<PhieuDangKyHoc> learnForms = phieuDangKyHocDAO.getAll();
        Assert.assertNotNull(learnForms);
        Assert.assertEquals(9,learnForms.size());
    }

    @Test
    public void save() {
        //update
        PhieuDangKyDay phieuDangKyDay = phieuDangKyDayDAO.findById(2).get();
        SinhVien sinhVien = sinhVienDAO.findById(1).get();
        PhieuDangKyHoc phieuDangKyHoc = new PhieuDangKyHoc(36,sinhVien,phieuDangKyDay,"Mon May 18 22:52:57 ICT 2020",430000.0,"LEARNING");
        try {
            conn.setAutoCommit(false);
            Optional<PhieuDangKyHoc> opt = phieuDangKyHocDAO.save(phieuDangKyHoc);
            Assert.assertTrue(opt.isPresent());
            Assert.assertEquals(9,phieuDangKyHocDAO.getAll().size());
            Assert.assertEquals(1,opt.get().getSinhVien().getSinhVienId().longValue());
            Assert.assertEquals(2,opt.get().getPhieuDangKyDay().getPhieuDangKyDayId().longValue());
            Assert.assertEquals("Mon May 18 22:52:57 ICT 2020",opt.get().getThoiGianDangKy());
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

        //insert new learn form
        phieuDangKyHoc = new PhieuDangKyHoc(null,sinhVien,phieuDangKyDay,"Mon May 18 22:52:57 ICT 2020",430000.0,"LEARNING");
        try {
            conn.setAutoCommit(false);
            Optional<PhieuDangKyHoc> opt = phieuDangKyHocDAO.save(phieuDangKyHoc);
            Assert.assertTrue(opt.isPresent());
            Assert.assertEquals(10,phieuDangKyHocDAO.getAll().size());
            Assert.assertTrue(opt.get().getPhieuDangKyHocId() > 36);

            PhieuDangKyHoc teachForm = phieuDangKyHocDAO.findById(opt.get().getPhieuDangKyHocId()).get();
            Assert.assertEquals(1,teachForm.getSinhVien().getSinhVienId().longValue());
            Assert.assertEquals(2,teachForm.getPhieuDangKyDay().getPhieuDangKyDayId().longValue());
            Assert.assertEquals("Mon May 18 22:52:57 ICT 2020",teachForm.getThoiGianDangKy());
            Assert.assertEquals("LEARNING",teachForm.getTrangThai());

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

    @Test
    public void findById() {
        //id not in database
        Optional<PhieuDangKyHoc> opt = phieuDangKyHocDAO.findById(37);
        Assert.assertFalse(opt.isPresent());

        //id in database
        opt = phieuDangKyHocDAO.findById(33);
        Assert.assertTrue(opt.isPresent());
        Assert.assertEquals(1,opt.get().getSinhVien().getSinhVienId().longValue());
        Assert.assertEquals(1,opt.get().getPhieuDangKyDay().getPhieuDangKyDayId().longValue());
        Assert.assertEquals("Mon May 18 22:52:57 ICT 2020",opt.get().getThoiGianDangKy());
        Assert.assertEquals("430000.0",opt.get().getHocPhi().toString());
        Assert.assertEquals("CANCELED",opt.get().getTrangThai());
    }

    @Test
    public void getByStatus() {
        //find status not in database
        List<PhieuDangKyHoc> learn_forms = phieuDangKyHocDAO.getByStatus("INFOR");
        Assert.assertEquals(0,learn_forms.size());

        //find LEARNING STATUS
        learn_forms = phieuDangKyHocDAO.getByStatus("LEARNING");
        Assert.assertEquals(1,learn_forms.size());

        //find LEARNED STATUS
        learn_forms = phieuDangKyHocDAO.getByStatus("LEARNED");
        Assert.assertEquals(5,learn_forms.size());
        for (PhieuDangKyHoc phieuDangKyHoc:learn_forms){
            Assert.assertEquals("LEARNED",phieuDangKyHoc.getTrangThai());
        }

        //find CANCELED STATUS
        learn_forms = phieuDangKyHocDAO.getByStatus("CANCELED");
        Assert.assertEquals(3,learn_forms.size());
        for (PhieuDangKyHoc phieuDangKyHoc:learn_forms){
            Assert.assertEquals("CANCELED",phieuDangKyHoc.getTrangThai());
        }

        return;

    }
}