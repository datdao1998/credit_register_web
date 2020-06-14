package test.service;

import api.model.connectdb.ConnectDB;
import api.model.entity.GiangVien;
import api.service.GiangVienService;
import api.service.impl.GiangVienServiceImpl;
import common.exception.FPException;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class GiangVienServiceImplTest {

    private GiangVienService service = new GiangVienServiceImpl();

    private Connection conn = ConnectDB.getInstance().getConnection();

    @Test
    public void findById() throws FPException {
        Optional<GiangVien> opt = service.findById(4);
        Assert.assertTrue(opt.isPresent());
        Assert.assertEquals(10,opt.get().getTblNguoiDungId().longValue());
        Assert.assertEquals("Do Thi Bich Ngoc",opt.get().getTenGiangVien());
        Assert.assertEquals("CNTT",opt.get().getKhoa());
        Assert.assertEquals("CNPM",opt.get().getNganh());
        Assert.assertEquals("TS",opt.get().getHocVi());
        Assert.assertEquals("Test",opt.get().getBoMon());
    }

    @Test(expected = FPException.NotFoundEntityException.class)
    public  void findByIdException() throws FPException {
        Optional<GiangVien> opt = service.findById(5);
    }

    @Test
    public void update() throws FPException {
        GiangVien giangVien = new GiangVien(null,1,"test","test","test","test","test");
        Integer id = 1;
        try {
            conn.setAutoCommit(false);
            Optional<GiangVien> opt = service.update(id,giangVien);
            Assert.assertEquals("test",opt.get().getTenGiangVien());
            Assert.assertEquals("test",opt.get().getKhoa());
            Assert.assertEquals("test",opt.get().getNganh());
            Assert.assertEquals("test",opt.get().getHocVi());
            Assert.assertEquals("test",opt.get().getBoMon());
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
        GiangVien giangVien = new GiangVien(null,1,"test","test","test","test","test");
        Integer id = 5;
        Optional<GiangVien> opt = service.update(id,giangVien);
    }

    @Test
    public void search() {
        //search all teacher
        List<GiangVien> teachers = service.search(null,null,null,null);
        Assert.assertNotNull(teachers);
        Assert.assertEquals(4,teachers.size());

        //search by faculty
        teachers = service.search("CN",null,null,null);
        Assert.assertNotNull(teachers);
        Assert.assertEquals(2,teachers.size());

        //search by 'nganh'
        teachers = service.search(null,"To",null,null);
        Assert.assertNotNull(teachers);
        Assert.assertEquals(2,teachers.size());

        //search by 'hoc vi'
        teachers = service.search(null,null,"T",null);
        Assert.assertNotNull(teachers);
        Assert.assertEquals(3,teachers.size());

        //search by 'bo mon'
        teachers = service.search(null,null,null,"Giai tich");
        Assert.assertNotNull(teachers);
        Assert.assertEquals(1,teachers.size());

        //standard search
        teachers = service.search("C","T","G","D");
        Assert.assertNotNull(teachers);
        Assert.assertEquals(1,teachers.size());

    }
}