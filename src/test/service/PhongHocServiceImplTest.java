package test.service;

import api.model.connectdb.ConnectDB;
import api.model.entity.PhongHoc;
import api.service.PhongHocService;
import api.service.impl.PhongHocServiceImpl;
import common.exception.FPException;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.util.Optional;

import static org.junit.Assert.*;

public class PhongHocServiceImplTest {

    private PhongHocService service = new PhongHocServiceImpl();

    @Test
    public void findById() throws FPException {
        Optional<PhongHoc> opt = service.findById(3);
        Assert.assertTrue(opt.isPresent());
        Assert.assertEquals("305A2",opt.get().getTenPhong());
        Assert.assertEquals(45,opt.get().getSucChua().longValue());
        Assert.assertEquals("tang 3",opt.get().getViTri());
        Assert.assertEquals("co dieu hoa",opt.get().getMoTa());
    }

    @Test(expected = FPException.NotFoundEntityException.class)
    public void findByIdException() throws FPException{
        Optional<PhongHoc> opt = service.findById(5);
    }

    @Test
    public void search() {
        Optional<PhongHoc> opt = service.search("101A2");
        Assert.assertTrue(opt.isPresent());
        Assert.assertEquals(40,opt.get().getSucChua().longValue());
        Assert.assertEquals("tang 1",opt.get().getViTri());
        Assert.assertEquals("Khong",opt.get().getMoTa());
    }
}