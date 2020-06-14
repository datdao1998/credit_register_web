package test.service;

import api.model.connectdb.ConnectDB;
import api.model.entity.NguoiDung;
import api.service.NguoiDungService;
import api.service.impl.NguoiDungServiceImpl;
import common.exception.FPException;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class NguoiDungServiceImplTest {

    private NguoiDungService service = new NguoiDungServiceImpl();

    private Connection conn = ConnectDB.getInstance().getConnection();

    @Test
    public void create() throws FPException {
        NguoiDung nguoiDung = new NguoiDung(null,"test","test","test","test","test","test","test");
        try {
            conn.setAutoCommit(false);
            Optional<NguoiDung> opt = service.create(nguoiDung);
            Assert.assertEquals("test",opt.get().getHoTen());
            Assert.assertEquals("test",opt.get().getMatKhau());
            Assert.assertEquals("test",opt.get().getNgaySinh());
            Assert.assertEquals("test",opt.get().getGioiTinh());
            Assert.assertEquals("test",opt.get().getDiaChi());
            Assert.assertEquals("test",opt.get().getVaiTro());
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
        NguoiDung nguoiDung = new NguoiDung(10,"ngocdb","test","test","test","test","test","test");
        Optional<NguoiDung> opt = service.create(nguoiDung);
    }

    @Test(expected = FPException.NotFoundEntityException.class)
    public void findByIdException() throws FPException {
        Optional<NguoiDung> opt = service.findById(11);
    }

    @Test
    public void findById() throws FPException{
        Optional<NguoiDung> opt = service.findById(3);
        Assert.assertTrue(opt.isPresent());
        Assert.assertEquals("Dao Quoc Dat",opt.get().getHoTen());
        Assert.assertEquals("Thai Binh",opt.get().getDiaChi());
        Assert.assertEquals("22/08/1998",opt.get().getNgaySinh());
        Assert.assertEquals("Nam", opt.get().getGioiTinh());
        Assert.assertEquals("B16DCCN059",opt.get().getTenDangNhap());
        Assert.assertEquals("SV",opt.get().getVaiTro());
    }

    @Test
    public void update() throws FPException {
        NguoiDung nguoiDung = new NguoiDung(null,"test","test","test","test","test","test","test");
        Integer id = 10;
        try {
            conn.setAutoCommit(false);
            Optional<NguoiDung> opt = service.update(id,nguoiDung);
            Assert.assertEquals("test",opt.get().getHoTen());
            Assert.assertEquals("test",opt.get().getTenDangNhap());
            Assert.assertEquals("test",opt.get().getMatKhau());
            Assert.assertEquals("test",opt.get().getNgaySinh());
            Assert.assertEquals("test",opt.get().getDiaChi());
            Assert.assertEquals("test",opt.get().getVaiTro());
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
        NguoiDung nguoiDung = new NguoiDung(null,"test","test","test","test","test","test","test");
        Integer id = 11;
        Optional<NguoiDung> opt = service.update(id,nguoiDung);
    }

    @Test
    public void search() {
        // search all user
        List<NguoiDung> users = service.search(null,null,null);
        Assert.assertNotNull(users);
        Assert.assertEquals(10,users.size());

        //search a existed username
        users = service.search("B16DCCN059",null,null);
        Assert.assertNotNull(users);
        Assert.assertEquals(1,users.size());

        //search a username not exist
        users = service.search("Z",null,null);
        Assert.assertNotNull(users);
        Assert.assertEquals(0,users.size());

        //search a  name existed
        users = service.search(null,"Dat",null);
        Assert.assertNotNull(users);
        Assert.assertEquals(2,users.size());

        //search a name not existed
        users = service.search(null,"Z",null);
        Assert.assertNotNull(users);
        Assert.assertEquals(0,users.size());

        //search standard
        users = service.search("B16DCCN059","Dat","SV");
        Assert.assertNotNull(users);
        Assert.assertEquals(1,users.size());

    }
}