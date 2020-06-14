package test.dao;

import api.model.connectdb.ConnectDB;
import api.model.dao.*;
import api.model.dao.impl.*;
import api.model.entity.GiangVien;
import api.model.entity.LopHocPhan;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class LopHocPhanDAOImplTest {

    private LopHocPhanDAO lopHocPhanDAO = new LopHocPhanDAOImpl();

    private GiangVienDAO giangVienDAO = new GiangVienDAOImpl();

    private MonHocDAO monHocDAO = new MonHocDAOImpl();

    private KipHocDAO kipHocDAO = new KipHocDAOImpl();

    private PhongHocDAO phongHocDAO = new PhongHocDAOImpl();

    private Connection conn = ConnectDB.getInstance().getConnection();

    @Test
    public void getAll() {
        List<LopHocPhan> course = lopHocPhanDAO.getAll();
        Assert.assertNotNull(course);
        Assert.assertEquals(8,course.size());
    }

    @Test
    public void save() {
        //update a course which is existed
        LopHocPhan lopHocPhan = new LopHocPhan(4,kipHocDAO.findById(7).get(),giangVienDAO.findById(2).get(),monHocDAO.findById(1).get(),phongHocDAO.findById(1).get(),1,15,3);
        try {
            conn.setAutoCommit(false);
            Optional<LopHocPhan> opt = lopHocPhanDAO.save(lopHocPhan);
            Assert.assertTrue(opt.isPresent());
            Assert.assertEquals(8,lopHocPhanDAO.getAll().size());
            Assert.assertEquals(7,opt.get().getKipHoc().getKipHocId().longValue());
            Assert.assertEquals(2,opt.get().getGiangVien().getGiangVienId().longValue());
            Assert.assertEquals(1,opt.get().getMonHoc().getMonHocId().longValue());
            Assert.assertEquals(1,opt.get().getPhongHoc().getPhongHocId().longValue());
            Assert.assertEquals(1,opt.get().getTuanBatDau().longValue());
            Assert.assertEquals(15,opt.get().getTuanKetThuc().longValue());
            Assert.assertEquals(3,opt.get().getPhieuDangKyDayId().longValue());
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

        //insert a new course
        lopHocPhan = new LopHocPhan(null,kipHocDAO.findById(7).get(),giangVienDAO.findById(2).get(),monHocDAO.findById(1).get(),phongHocDAO.findById(1).get(),1,15,3);
        try {
            conn.setAutoCommit(false);
            Optional<LopHocPhan> opt = lopHocPhanDAO.save(lopHocPhan);
            Assert.assertTrue(opt.isPresent());
            Assert.assertTrue(opt.get().getId() > 8);
            Assert.assertEquals(9,lopHocPhanDAO.getAll().size());

            LopHocPhan course = lopHocPhanDAO.findById(opt.get().getId()).get();
            Assert.assertEquals(7,opt.get().getKipHoc().getKipHocId().longValue());
            Assert.assertEquals(2,opt.get().getGiangVien().getGiangVienId().longValue());
            Assert.assertEquals(1,opt.get().getMonHoc().getMonHocId().longValue());
            Assert.assertEquals(1,opt.get().getPhongHoc().getPhongHocId().longValue());
            Assert.assertEquals(1,opt.get().getTuanBatDau().longValue());
            Assert.assertEquals(15,opt.get().getTuanKetThuc().longValue());
            Assert.assertEquals(3,opt.get().getPhieuDangKyDayId().longValue());

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
        // course's id not in database
        Optional<LopHocPhan> opt = lopHocPhanDAO.findById(9);
        Assert.assertFalse(opt.isPresent());

        //course's id in database
        opt = lopHocPhanDAO.findById(3);
        Assert.assertTrue(opt.isPresent());
        Assert.assertEquals(7,opt.get().getKipHoc().getKipHocId().longValue());
        Assert.assertEquals(3,opt.get().getGiangVien().getGiangVienId().longValue());
        Assert.assertEquals(3,opt.get().getMonHoc().getMonHocId().longValue());
        Assert.assertEquals(2,opt.get().getPhongHoc().getPhongHocId().longValue());
        Assert.assertEquals(2,opt.get().getPhieuDangKyDayId().longValue());
        Assert.assertEquals(1,opt.get().getTuanBatDau().longValue());
        Assert.assertEquals(15,opt.get().getTuanKetThuc().longValue());
        return;
    }

    @Test
    public void getByPhieuDangKyDay() {
        //teach-form-id not in database
        List<LopHocPhan> courses = lopHocPhanDAO.getByPhieuDangKyDay(8);
        Assert.assertNotNull(courses);
        Assert.assertEquals(0,courses.size());

        //teach-form-id in database
        courses  = lopHocPhanDAO.getByPhieuDangKyDay(1);
        Assert.assertNotNull(courses);
        Assert.assertEquals(2,courses.size());
        for(LopHocPhan lopHocPhan : courses){
            Assert.assertEquals(1,lopHocPhan.getPhieuDangKyDayId().longValue());
        }
    }
}