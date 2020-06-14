package test.dao;

import api.model.connectdb.ConnectDB;
import api.model.dao.GiangVienDAO;
import api.model.dao.impl.GiangVienDAOImpl;
import api.model.entity.GiangVien;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class GiangVienDAOImplTest {

    private GiangVienDAO giangVienDAO = new GiangVienDAOImpl();

    private Connection conn = ConnectDB.getInstance().getConnection();

    @Test
    public void search() {
        //search all null
        List<GiangVien> teachers = giangVienDAO.search(null,null,null,null);
        Assert.assertNotNull(teachers);
        Assert.assertEquals(4,teachers.size());

        //search all with faculty value
        teachers = giangVienDAO.search("CNTT",null,null,null);
        Assert.assertNotNull(teachers);
        Assert.assertEquals(2,teachers.size());

        //search all with 'nganh' value
        teachers = giangVienDAO.search(null,"To",null,null);
        Assert.assertNotNull(teachers);
        Assert.assertEquals(2,teachers.size()); //value 'Toan'

        //search all with 'hoc vi' value
        teachers = giangVienDAO.search(null,null,"T",null);
        Assert.assertNotNull(teachers);
        Assert.assertEquals(3,teachers.size()); // value 'PGS.TS' ,'Ths' and 'TS'

        //search all with 'boMon' value
        teachers = giangVienDAO.search(null,null,null,"Test");
        Assert.assertNotNull(teachers);
        Assert.assertEquals(1,teachers.size());

        //standard search
        teachers = giangVienDAO.search("CNTT","CNPM","T","Android");
        Assert.assertNotNull(teachers);
        Assert.assertEquals(1,teachers.size());
        for (GiangVien giangVien: teachers){
            Assert.assertEquals("CNTT",giangVien.getKhoa());
            Assert.assertEquals("CNPM",giangVien.getNganh());
            Assert.assertEquals("ThS",giangVien.getHocVi());
            Assert.assertEquals("Android",giangVien.getBoMon());
        }

    }

    @Test
    public void getAll() {
        List<GiangVien> teachers = giangVienDAO.getAll();
        Assert.assertNotNull(teachers);
        Assert.assertEquals(4,teachers.size());
    }

    @Test
    public void save() {
        //update a teacher which is existed
        GiangVien giangVien = new GiangVien(2,2,"Phan Tan Trung","Kinh te","Marketing","ThS","Phuong phap luan");
        try {
            conn.setAutoCommit(false);
            Optional<GiangVien> opt = giangVienDAO.save(giangVien);
            Assert.assertTrue(opt.isPresent());
            Assert.assertEquals(4,giangVienDAO.getAll().size());
            Assert.assertEquals(2,opt.get().getGiangVienId().longValue());
            Assert.assertEquals(2,opt.get().getTblNguoiDungId().longValue());
            Assert.assertEquals("Phan Tan Trung",opt.get().getTenGiangVien());
            Assert.assertEquals("Kinh te",opt.get().getKhoa());
            Assert.assertEquals("Marketing",opt.get().getNganh());
            Assert.assertEquals("ThS",opt.get().getHocVi());
            Assert.assertEquals("Phuong phap luan",opt.get().getBoMon());
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

        //insert a new teacher
        giangVien = new GiangVien(null,2,"Noway","Vien thong","Dien tu truyen thong","TS","Dien tu tuong tu");
        try {
            conn.setAutoCommit(false);
            Optional<GiangVien> opt = giangVienDAO.save(giangVien);
            Assert.assertTrue(opt.isPresent());
            Assert.assertTrue(opt.get().getGiangVienId() > 4);
            Assert.assertEquals(5,giangVienDAO.getAll().size());

            GiangVien teacher = giangVienDAO.findById(opt.get().getGiangVienId()).get();
            Assert.assertEquals("Noway",opt.get().getTenGiangVien());
            Assert.assertEquals("Vien thong",opt.get().getKhoa());
            Assert.assertEquals("Dien tu truyen thong",opt.get().getNganh());
            Assert.assertEquals("TS",opt.get().getHocVi());
            Assert.assertEquals("Dien tu tuong tu",opt.get().getBoMon());

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

        // teacher's id is not exist in database
        Optional<GiangVien> opt = giangVienDAO.findById(5);
        Assert.assertFalse(opt.isPresent());

        //user is existed in database
        opt = giangVienDAO.findById(3);
        Assert.assertTrue(opt.isPresent());
        Assert.assertEquals(6,opt.get().getTblNguoiDungId().longValue());
        Assert.assertEquals("Nguyen Hoang Anh",opt.get().getTenGiangVien());
        Assert.assertEquals("CNTT",opt.get().getKhoa());
        Assert.assertEquals("CNPM",opt.get().getNganh());
        Assert.assertEquals("ThS",opt.get().getHocVi());
        Assert.assertEquals("Android",opt.get().getBoMon());
        return;

    }
}