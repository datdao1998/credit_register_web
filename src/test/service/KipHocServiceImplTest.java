package test.service;

import api.model.connectdb.ConnectDB;
import api.model.entity.KipHoc;
import api.service.KipHocService;
import api.service.impl.KipHocServiceImpl;
import com.mysql.cj.jdbc.ConnectionImpl;
import common.exception.FPException;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import static org.junit.Assert.*;

public class KipHocServiceImplTest {

    private KipHocService service = new KipHocServiceImpl();

    private Connection conn = ConnectDB.getInstance().getConnection();


    @Test
    public void findById() throws FPException {
        Optional<KipHoc> opt = service.findById(3);
        Assert.assertTrue(opt.isPresent());
        Assert.assertEquals(12,opt.get().getGioBatDau().longValue());
        Assert.assertEquals(14,opt.get().getGioKetThuc().longValue());
        Assert.assertEquals(2,opt.get().getThu().longValue());
    }

    @Test(expected = FPException.NotFoundEntityException.class)
    public void findByIdException() throws FPException{
        Optional<KipHoc> opt = service.findById(10);
    }

    @Test
    public void update() throws FPException {
        KipHoc kipHoc = new KipHoc(null,7,9,6);
        Integer id = 3;
        try {
            conn.setAutoCommit(false);
            Optional<KipHoc> opt = service.update(id,kipHoc);
            Assert.assertTrue(opt.isPresent());
            Assert.assertEquals(7,opt.get().getGioBatDau().longValue());
            Assert.assertEquals(9,opt.get().getGioKetThuc().longValue());
            Assert.assertEquals(6,opt.get().getThu().longValue());
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
        KipHoc kipHoc = new KipHoc(null,7,9,6);
        Integer id = 10;
        Optional<KipHoc> opt = service.update(id,kipHoc);
    }
}