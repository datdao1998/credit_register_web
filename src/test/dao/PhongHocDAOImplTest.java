package test.dao;

import api.model.connectdb.ConnectDB;
import api.model.dao.PhongHocDAO;
import api.model.dao.impl.PhongHocDAOImpl;
import api.model.entity.PhongHoc;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class PhongHocDAOImplTest {

    private PhongHocDAO phongHocDAO = new PhongHocDAOImpl();

    private Connection conn = ConnectDB.getInstance().getConnection();

    @Test
    public void findByName() {
        //search a room not in database
         Optional<PhongHoc> opt = phongHocDAO.findByName("102A2");
         Assert.assertFalse(opt.isPresent());

         //search a room in database
        opt = phongHocDAO.findByName("401A3");
        Assert.assertTrue(opt.isPresent());
        Assert.assertEquals("401A3",opt.get().getTenPhong());
        Assert.assertEquals(60,opt.get().getSucChua().longValue());
        Assert.assertEquals("tang 4 A3",opt.get().getViTri());
        Assert.assertEquals("nong",opt.get().getMoTa());

        return;
    }

    @Test
    public void getAll() {
        List<PhongHoc> rooms = phongHocDAO.getAll();
        Assert.assertNotNull(rooms);
        Assert.assertEquals(4,rooms.size());
    }

    @Test
    public void save() {
        // update a room existed in database
        PhongHoc phongHoc = new PhongHoc(2,"309A3","Tang 3 A3", 50, "Van khong co dieu hoa");
        try {
            conn.setAutoCommit(false);
            Optional<PhongHoc> opt = phongHocDAO.save(phongHoc);
            Assert.assertTrue(opt.isPresent());
            Assert.assertEquals(4,phongHocDAO.getAll().size());
            Assert.assertEquals(2, opt.get().getPhongHocId().longValue());
            Assert.assertEquals(50,opt.get().getSucChua().longValue());
            Assert.assertEquals("309A3",opt.get().getTenPhong());
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

        //add a new room
        phongHoc =  new PhongHoc(null,"609A3","Tang 6 A3",50,"Dieu hoa mat ruoi");
        try {
            conn.setAutoCommit(false);
            Optional<PhongHoc> opt = phongHocDAO.save(phongHoc);
            Assert.assertTrue(opt.isPresent());
            Assert.assertTrue(opt.get().getPhongHocId() > 4);
            Assert.assertEquals(5,phongHocDAO.getAll().size());

            PhongHoc room = phongHocDAO.findById(opt.get().getPhongHocId()).get();
            Assert.assertEquals("609A3",room.getTenPhong());
            Assert.assertEquals("Tang 6 A3",room.getViTri());
            Assert.assertEquals(50,room.getSucChua().longValue());
            Assert.assertEquals("Dieu hoa mat ruoi",room.getMoTa());

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
        // room's id is not exist in database
        Optional<PhongHoc> opt = phongHocDAO.findById(5);
        Assert.assertFalse(opt.isPresent());

        //room is existed in database
        opt = phongHocDAO.findById(3);
        Assert.assertTrue(opt.isPresent());
        Assert.assertEquals("305A2",opt.get().getTenPhong());
        Assert.assertEquals(45,opt.get().getSucChua().longValue());
        Assert.assertEquals("tang 3",opt.get().getViTri());
        Assert.assertEquals("co dieu hoa",opt.get().getMoTa());

        return;
    }
}