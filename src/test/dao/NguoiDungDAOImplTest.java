package test.dao;

import api.model.connectdb.ConnectDB;
import api.model.dao.NguoiDungDAO;
import api.model.dao.impl.NguoiDungDAOImpl;
import api.model.entity.NguoiDung;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class NguoiDungDAOImplTest {

    private NguoiDungDAO nguoiDungDAO = new NguoiDungDAOImpl();
    private Connection conn = ConnectDB.getInstance().getConnection();

    @Test
    public void findByUserName() {
        // username is not existed in database
        Optional<NguoiDung> opt = nguoiDungDAO.findByUserName("B16DCVT151");
        Assert.assertFalse(opt.isPresent());

        //usernaem is existed in database
        opt = nguoiDungDAO.findByUserName("B16DCCN059");
        Assert.assertEquals("Dao Quoc Dat",opt.get().getHoTen());
        Assert.assertEquals("Thai Binh",opt.get().getDiaChi());
        Assert.assertEquals("22/08/1998",opt.get().getNgaySinh());
        Assert.assertEquals("Nam", opt.get().getGioiTinh());
        Assert.assertEquals("B16DCCN059",opt.get().getTenDangNhap());
        Assert.assertEquals("SV",opt.get().getVaiTro());
        return;

    }

    @Test
    public void search() {
        // search all
        List<NguoiDung> users = nguoiDungDAO.search(null,null,null);
        Assert.assertNotNull(users);
        Assert.assertEquals(10,users.size());

        //search all username and name and role in database
        users = nguoiDungDAO.search(null,null,"SV");
        Assert.assertNotNull(users);
        Assert.assertEquals(6,users.size());
        for (NguoiDung nguoiDung: users){
            Assert.assertEquals("SV",nguoiDung.getVaiTro());
        }

        //search all username and name and role not in database
        users = nguoiDungDAO.search(null,null,"Giao vu");
        Assert.assertNotNull(users);
        Assert.assertEquals(0,users.size());

        //search all username and role and name in database
        users = nguoiDungDAO.search(null,"Dat",null);
        Assert.assertNotNull(users);
        Assert.assertEquals(2,users.size());
        for(NguoiDung nguoiDung:users){
            Assert.assertTrue(nguoiDung.getHoTen().contains("Dat"));
        }

        //search all username and role and name not in database
        users = nguoiDungDAO.search(null,"Z",null);
        Assert.assertNotNull(users);
        Assert.assertEquals(0,users.size());

        //search all name and role and username in database
        users = nguoiDungDAO.search("B16DCCN059",null,null);
        Assert.assertNotNull(users);
        Assert.assertEquals(1,users.size());
        for(NguoiDung nguoiDung:users){
            Assert.assertTrue(nguoiDung.getHoTen().contains("Dat"));
        }

        //search all username and role and name not in database
        users = nguoiDungDAO.search("Z",null,null);
        Assert.assertNotNull(users);
        Assert.assertEquals(0,users.size());

        //search in standard input - in database
        users = nguoiDungDAO.search("B16DCCN013","Nguyen Viet Anh", "SV");
        Assert.assertEquals(1,users.size());
        for (NguoiDung nguoiDung : users){
            Assert.assertEquals("B16DCCN013",nguoiDung.getTenDangNhap());
            Assert.assertEquals("Nguyen Viet Anh",nguoiDung.getHoTen());
            Assert.assertEquals("SV",nguoiDung.getVaiTro());
        }

    }

    @Test
    public void getAll() {
        // check list user is not null and size equals size in database
        List<NguoiDung> list = nguoiDungDAO.getAll();
        Assert.assertNotNull(list);
        Assert.assertEquals(10,list.size());
        return;
    }

    @Test
    public void save() {
        //update a user which id is existed
        NguoiDung insertUser = new NguoiDung(1,"DungHP","dungak1983","Hoang Phi Hong","30/04/1983","Nam","Gia Lai","GV");
        try {
            conn.setAutoCommit(false);
            Optional<NguoiDung> opt = nguoiDungDAO.save(insertUser);
            Assert.assertTrue(opt.isPresent());
            Assert.assertEquals(10,nguoiDungDAO.getAll().size());
            Assert.assertEquals(1,opt.get().getNguoiDungId().longValue());
            Assert.assertEquals("DungHP",opt.get().getTenDangNhap());
            Assert.assertEquals("dungak1983",opt.get().getMatKhau());
            Assert.assertEquals("Hoang Phi Hong",opt.get().getHoTen());
            Assert.assertEquals("30/04/1983",opt.get().getNgaySinh());
            Assert.assertEquals("Nam",opt.get().getGioiTinh());
            Assert.assertEquals("Gia Lai",opt.get().getDiaChi());
            Assert.assertEquals("GV",opt.get().getVaiTro());
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.rollback();
                conn.setAutoCommit(true);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        //add a new user
        insertUser = new NguoiDung(null,"ConanDoyle","shinichikudo","SherlockHolmes","13/01/1998","Nam","Japan","SV");
        try {
            conn.setAutoCommit(false);
            Optional<NguoiDung> opt = nguoiDungDAO.save(insertUser);
            Assert.assertTrue(opt.isPresent());
            Assert.assertTrue(opt.get().getNguoiDungId() > 9);
            Assert.assertEquals(11,nguoiDungDAO.getAll().size());

            NguoiDung nguoiDung = nguoiDungDAO.findById(opt.get().getNguoiDungId()).get();
            Assert.assertEquals("ConanDoyle",nguoiDung.getTenDangNhap());
            Assert.assertEquals("shinichikudo",nguoiDung.getMatKhau());
            Assert.assertEquals("SherlockHolmes",nguoiDung.getHoTen());
            Assert.assertEquals("13/01/1998",nguoiDung.getNgaySinh());
            Assert.assertEquals("Nam",nguoiDung.getGioiTinh());
            Assert.assertEquals("Japan",nguoiDung.getDiaChi());
            Assert.assertEquals("SV",nguoiDung.getVaiTro());

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
    public void findById()   {
        // id not found in database
        Optional<NguoiDung> opt = nguoiDungDAO.findById(11);
        Assert.assertFalse(opt.isPresent());

        //user is existed in database
        opt = nguoiDungDAO.findById(3);
        Assert.assertTrue(opt.isPresent());
        Assert.assertEquals("Dao Quoc Dat",opt.get().getHoTen());
        Assert.assertEquals("Thai Binh",opt.get().getDiaChi());
        Assert.assertEquals("22/08/1998",opt.get().getNgaySinh());
        Assert.assertEquals("Nam", opt.get().getGioiTinh());
        Assert.assertEquals("B16DCCN059",opt.get().getTenDangNhap());
        Assert.assertEquals("SV",opt.get().getVaiTro());
        return;
    }
}