package test.service;

import api.model.connectdb.ConnectDB;
import api.model.entity.SinhVien;
import api.service.SinhVienService;
import api.service.impl.SinhVienServiceImpl;
import common.exception.FPException;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class SinhVienServiceImplTest {

    private SinhVienService service = new SinhVienServiceImpl();

    private Connection conn = ConnectDB.getInstance().getConnection();

    @Test
    public void findById() throws FPException {
        Optional<SinhVien> opt = service.findById(4);
        Assert.assertTrue(opt.isPresent());
        Assert.assertEquals(7,opt.get().getTblNguoiDungId().longValue());
        Assert.assertEquals("B16DCCN058",opt.get().getMaSV());
        Assert.assertEquals("CNTT",opt.get().getKhoa());
        Assert.assertEquals("CNPM",opt.get().getNganh());
        Assert.assertEquals("3.2",opt.get().getGpa().toString());
        Assert.assertEquals(114,opt.get().getStcTichLuy().longValue());
        Assert.assertEquals("Gioi",opt.get().getHocLuc());
        Assert.assertEquals("D16CNPM1",opt.get().getLop());
    }

    @Test(expected = FPException.NotFoundEntityException.class)
    public void findByIdException() throws FPException {
        Optional<SinhVien> opt = service.findById(7);
    }

    @Test
    public void update() throws FPException {
        SinhVien sinhVien = new SinhVien(null,5,"test","test","test",3.5,114,"test","D16CNPM2");
        Integer id = 6;
        try {
            conn.setAutoCommit(false);
            Optional<SinhVien> opt = service.update(id,sinhVien);
            Assert.assertEquals("test",opt.get().getMaSV());
            Assert.assertEquals("test",opt.get().getKhoa());
            Assert.assertEquals("test",opt.get().getNganh());
            Assert.assertEquals("3.5",opt.get().getGpa().toString());
            Assert.assertEquals(114,opt.get().getStcTichLuy().longValue());
            Assert.assertEquals("test",opt.get().getHocLuc());
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
    public void updateException() throws Exception{
        SinhVien sinhVien = new SinhVien(null,5,"test","test","test",3.5,114,"test","D16CNPM2");
        Integer id = 7;
        Optional<SinhVien> opt = service.update(id,sinhVien);
    }

    @Test
    public void searchByCode() {
        //search null value
        List<SinhVien> students = service.searchByCode(null);
        Assert.assertNotNull(students);
        Assert.assertEquals(0,students.size());

        //search existed value
        students = service.searchByCode("B16DCCN059");
        Assert.assertNotNull(students);
        Assert.assertEquals(1,students.size());

        //search not existed value
        students = service.searchByCode("B16DCCN318");
        Assert.assertNotNull(students);
        Assert.assertEquals(0,students.size());
    }
}