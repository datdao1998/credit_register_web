package test.service;

import api.model.connectdb.ConnectDB;
import api.model.entity.MonHoc;
import api.service.MonHocService;
import api.service.impl.MonHocServiceImpl;
import common.exception.FPException;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class MonHocServiceImplTest {

    private MonHocService service = new MonHocServiceImpl();

    private Connection conn = ConnectDB.getInstance().getConnection();

    @Test
    public void findById() throws FPException {
        Optional<MonHoc> opt = service.findById(6);
        Assert.assertTrue(opt.isPresent());
        Assert.assertEquals("BAS1234",opt.get().getMaMon());
        Assert.assertEquals("Phat trien ung dung cho cac thiet bi di dong",opt.get().getTenMon());
        Assert.assertEquals(4,opt.get().getSoTinChi().longValue());
        Assert.assertEquals(20,opt.get().getSoTietLT().longValue());
        Assert.assertEquals(15,opt.get().getSoTietBT().longValue());
        Assert.assertEquals(8,opt.get().getSoTietTH().longValue());
        Assert.assertEquals("Khong",opt.get().getMonDK());
        Assert.assertEquals("CNPM",opt.get().getBoMon());
        Assert.assertEquals("CB",opt.get().getKhoa());
    }

    @Test(expected = FPException.NotFoundEntityException.class)
    public void findByIdException() throws FPException{
        Optional<MonHoc> opt = service.findById(7);
    }

    @Test
    public void searchByName() {
        List<MonHoc> subjects = service.searchByName("mem");
        Assert.assertNotNull(subjects);
        Assert.assertEquals(4,subjects.size());
        for(MonHoc monHoc : subjects){
            Assert.assertTrue(monHoc.getTenMon().contains("mem"));
        }
    }

    @Test
    public void searchByCode() {
        Optional<MonHoc> opt = service.searchByCode("BAS1234");
        Assert.assertTrue(opt.isPresent());
        Assert.assertEquals("Phat trien ung dung cho cac thiet bi di dong",opt.get().getTenMon());
        Assert.assertEquals(4,opt.get().getSoTinChi().longValue());
        Assert.assertEquals(20,opt.get().getSoTietLT().longValue());
        Assert.assertEquals(15,opt.get().getSoTietBT().longValue());
        Assert.assertEquals(8,opt.get().getSoTietTH().longValue());
        Assert.assertEquals("Khong",opt.get().getMonDK());
        Assert.assertEquals("CNPM",opt.get().getBoMon());
        Assert.assertEquals("CB",opt.get().getKhoa());
    }

    @Test
    public void searchByFacultyAndSemester() {
        String faculty = "CNPM";
        Integer semester = 20202;
        List<MonHoc> list = service.searchByFacultyAndSemester(faculty,semester);
        Assert.assertNotNull(list);
        Assert.assertEquals(6,list.size());
    }
}