package test.dao;

import api.model.connectdb.ConnectDB;
import api.model.dao.KipHocDAO;
import api.model.dao.impl.KipHocDAOImpl;
import api.model.entity.KipHoc;
import api.model.entity.PhongHoc;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class KipHocDAOImplTest {

    private KipHocDAO kipHocDAO = new KipHocDAOImpl();

    private Connection conn = ConnectDB.getInstance().getConnection();

    @Test
    public void getAll() {
        List<KipHoc> shifts = kipHocDAO.getAll();
        Assert.assertNotNull(shifts);
        Assert.assertEquals(9,shifts.size());
    }

    @Test
    public void save() {

        //update a shift
        KipHoc kipHoc = new KipHoc(9,9,11,5);
        try {
            conn.setAutoCommit(false);
            Optional<KipHoc> opt = kipHocDAO.save(kipHoc);
            Assert.assertTrue(opt.isPresent());
            Assert.assertEquals(9,kipHocDAO.getAll().size());
            Assert.assertEquals(9,opt.get().getGioBatDau().longValue());
            Assert.assertEquals(11,opt.get().getGioKetThuc().longValue());
            Assert.assertEquals(5,opt.get().getThu().longValue());

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

        //add a new shift
        kipHoc = new KipHoc(null,14,16,6);
        try {
            conn.setAutoCommit(false);
            Optional<KipHoc> opt = kipHocDAO.save(kipHoc);
            Assert.assertTrue(opt.isPresent());
            Assert.assertTrue(opt.get().getKipHocId() > 9);
            Assert.assertEquals(10,kipHocDAO.getAll().size());

            KipHoc shift = kipHocDAO.findById(opt.get().getKipHocId()).get();
            Assert.assertEquals(14,shift.getGioBatDau().longValue());
            Assert.assertEquals(16,shift.getGioKetThuc().longValue());
            Assert.assertEquals(6,shift.getThu().longValue());

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
        // id not found in database
        Optional<KipHoc> opt = kipHocDAO.findById(10);
        Assert.assertFalse(opt.isPresent());

        //shift is existed in database
        opt = kipHocDAO.findById(3);
        Assert.assertTrue(opt.isPresent());
        Assert.assertEquals(12,opt.get().getGioBatDau().longValue());
        Assert.assertEquals(14,opt.get().getGioKetThuc().longValue());
        Assert.assertEquals(2,opt.get().getThu().longValue());
        return;
    }
}