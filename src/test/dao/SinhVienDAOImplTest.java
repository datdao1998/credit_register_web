package test.dao;

import api.model.connectdb.ConnectDB;
import api.model.dao.SinhVienDAO;
import api.model.dao.impl.SinhVienDAOImpl;
import api.model.entity.SinhVien;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class SinhVienDAOImplTest {

    private SinhVienDAO sinhVienDAO = new SinhVienDAOImpl();

    private Connection conn = ConnectDB.getInstance().getConnection();

    @Test
    public void findByStudentCode() {
        //search with null code
        List<SinhVien> students = sinhVienDAO.findByStudentCode(null);
        Assert.assertNotNull(students);
        Assert.assertEquals(0,students.size());

        //search with code not in database
        students = sinhVienDAO.findByStudentCode("B16DCVT151");
        Assert.assertNotNull(students);
        Assert.assertEquals(0,students.size());

        //search with code in database
        students = sinhVienDAO.findByStudentCode("B16DCCN059");
        Assert.assertNotNull(students);
        Assert.assertEquals(1,students.size());
        for(SinhVien sinhVien : students){
            Assert.assertEquals("B16DCCN059",sinhVien.getMaSV());
        }

    }

    @Test
    public void getAll() {
        List<SinhVien> students = sinhVienDAO.getAll();
        Assert.assertNotNull(students);
        Assert.assertEquals(6,students.size());
    }

    @Test
    public void save() {
        //update a student which is existed
        SinhVien sinhVien = new SinhVien(3,5,"B16DCCN275","CNTT","CNPM",3.0,114,"Kha","D16CNPM2");
        try {
            conn.setAutoCommit(false);
            Optional<SinhVien> opt = sinhVienDAO.save(sinhVien);
            Assert.assertTrue(opt.isPresent());
            Assert.assertEquals(6,sinhVienDAO.getAll().size());
            Assert.assertEquals(3,opt.get().getSinhVienId().longValue());
            Assert.assertEquals(5,opt.get().getTblNguoiDungId().longValue());
            Assert.assertEquals("B16DCCN275",opt.get().getMaSV());
            Assert.assertEquals("CNTT",opt.get().getKhoa());
            Assert.assertEquals("CNPM",opt.get().getNganh());
            Assert.assertEquals("3.0",opt.get().getGpa().toString());
            Assert.assertEquals(114,opt.get().getStcTichLuy().longValue());
            Assert.assertEquals("Kha",opt.get().getHocLuc());
            Assert.assertEquals("D16CNPM2",opt.get().getLop());
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

        //insert a new student
        sinhVien = new SinhVien(null,5,"B16DCCN372","CNTT","CNPM",3.2,114,"Gioi","D16CNPM3");
        try {
            conn.setAutoCommit(false);
            Optional<SinhVien> opt = sinhVienDAO.save(sinhVien);
            Assert.assertTrue(opt.isPresent());
            Assert.assertTrue(opt.get().getSinhVienId() > 6);
            Assert.assertEquals(7,sinhVienDAO.getAll().size());

            SinhVien student = sinhVienDAO.findById(opt.get().getSinhVienId()).get();
            Assert.assertEquals("B16DCCN372",opt.get().getMaSV());
            Assert.assertEquals("CNTT",opt.get().getKhoa());
            Assert.assertEquals("CNPM",opt.get().getNganh());
            Assert.assertEquals("3.2",opt.get().getGpa().toString());
            Assert.assertEquals(114,opt.get().getStcTichLuy().longValue());
            Assert.assertEquals("Gioi",opt.get().getHocLuc());
            Assert.assertEquals("D16CNPM3",opt.get().getLop());

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
        // student's id is not exist in database
        Optional<SinhVien> opt = sinhVienDAO.findById(7);
        Assert.assertFalse(opt.isPresent());

        //user is existed in database
        opt = sinhVienDAO.findById(4);
        Assert.assertTrue(opt.isPresent());
        Assert.assertEquals(7,opt.get().getTblNguoiDungId().longValue());
        Assert.assertEquals("B16DCCN058",opt.get().getMaSV());
        Assert.assertEquals("CNTT",opt.get().getKhoa());
        Assert.assertEquals("CNPM",opt.get().getNganh());
        Assert.assertEquals("3.2",opt.get().getGpa().toString());
        Assert.assertEquals(114,opt.get().getStcTichLuy().longValue());
        Assert.assertEquals("Gioi",opt.get().getHocLuc());
        Assert.assertEquals("D16CNPM1",opt.get().getLop());

        return;
    }
}