package test.dao;

import api.model.connectdb.ConnectDB;
import api.model.dao.MonHocDAO;
import api.model.dao.impl.MonHocDAOImpl;
import api.model.entity.MonHoc;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class MonHocDAOImplTest {

    private MonHocDAO monHocDAO = new MonHocDAOImpl();

    private Connection conn = ConnectDB.getInstance().getConnection();

    @Test
    public void findByName() {
        //subject's name is not in database
        List<MonHoc> subjects = monHocDAO.findByName("Z");
        Assert.assertEquals(0,subjects.size());

        //subject's name is in database
        subjects = monHocDAO.findByName("mem");
        Assert.assertEquals(4,subjects.size());
        for (MonHoc monHoc : subjects){
            Assert.assertTrue(monHoc.getTenMon().contains("mem"));
        }
    }

    @Test
    public void findByCode() {
        //subject 's code is not in database
        Optional<MonHoc> opt = monHocDAO.findByCode("INT1000");
        Assert.assertFalse(opt.isPresent());

        //subject's code is in database
        opt = monHocDAO.findByCode("INT1111");
        Assert.assertTrue(opt.isPresent());
        Assert.assertEquals("Xay dung cac he thong nhung",opt.get().getTenMon());
        Assert.assertEquals(3,opt.get().getSoTinChi().longValue());
        Assert.assertEquals(10,opt.get().getSoTietLT().longValue());
        Assert.assertEquals(20,opt.get().getSoTietBT().longValue());
        Assert.assertEquals(0,opt.get().getSoTietTH().longValue());
        Assert.assertEquals("0.1",opt.get().getHsChuyenCan().toString());
        Assert.assertEquals("0.1",opt.get().getHsKiemTra().toString());
        Assert.assertEquals("0.0",opt.get().getHsThucHanh().toString());
        Assert.assertEquals("0.1",opt.get().getHsBaiTapLon().toString());
        Assert.assertEquals("0.7",opt.get().getHsCuoiKy().toString());
        Assert.assertEquals("Khong",opt.get().getMonDK());
        Assert.assertEquals("CNPM",opt.get().getBoMon());
        Assert.assertEquals("CB",opt.get().getKhoa());
    }

    @Test
    public void getAll() {
        List<MonHoc> subjects = monHocDAO.getAll();
        Assert.assertNotNull(subjects);
        Assert.assertEquals(6,subjects.size());
    }

    @Test
    public void save() {
        // update a subject existed in database
        MonHoc monHoc = new MonHoc(4,"BAS1998","Tieng Anh A21",4,15,15,0,0.1,0.1,0.0,0.3,0.5,"Tieng Anh A12","CNPM","CB");
        try {
            conn.setAutoCommit(false);
            Optional<MonHoc> opt = monHocDAO.save(monHoc);
            Assert.assertTrue(opt.isPresent());
            Assert.assertEquals(6,monHocDAO.getAll().size());
            Assert.assertEquals("BAS1998",opt.get().getMaMon());
            Assert.assertEquals("Tieng Anh A21",opt.get().getTenMon());
            Assert.assertEquals(4,opt.get().getSoTinChi().longValue());
            Assert.assertEquals(15,opt.get().getSoTietLT().longValue());
            Assert.assertEquals(15,opt.get().getSoTietBT().longValue());
            Assert.assertEquals(0,opt.get().getSoTietTH().longValue());
            Assert.assertEquals("0.1",opt.get().getHsChuyenCan().toString());
            Assert.assertEquals("0.1",opt.get().getHsKiemTra().toString());
            Assert.assertEquals("0.3",opt.get().getHsBaiTapLon().toString());
            Assert.assertEquals("0.5",opt.get().getHsCuoiKy().toString());
            Assert.assertEquals("Tieng Anh A12",opt.get().getMonDK());
            Assert.assertEquals("CNPM",opt.get().getBoMon());
            Assert.assertEquals("CB",opt.get().getKhoa());
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

        //add a new subject
        monHoc =  new MonHoc(null,"BAS1998","Tieng Anh A21",4,15,15,0,0.1,0.1,0.0,0.3,0.5,"Tieng Anh A12","CNPM","CB");
        try {
            conn.setAutoCommit(false);
            Optional<MonHoc> opt = monHocDAO.save(monHoc);
            Assert.assertTrue(opt.isPresent());
            Assert.assertTrue(opt.get().getMonHocId() > 6);
            Assert.assertEquals(7,monHocDAO.getAll().size());

            MonHoc subject = monHocDAO.findById(opt.get().getMonHocId()).get();
            Assert.assertEquals("BAS1998",subject.getMaMon());
            Assert.assertEquals("Tieng Anh A21",subject.getTenMon());
            Assert.assertEquals(4,subject.getSoTinChi().longValue());
            Assert.assertEquals(15,subject.getSoTietLT().longValue());
            Assert.assertEquals(15,subject.getSoTietBT().longValue());
            Assert.assertEquals(0,subject.getSoTietTH().longValue());
            Assert.assertEquals("0.1",subject.getHsChuyenCan().toString());
            Assert.assertEquals("0.1",subject.getHsKiemTra().toString());
            Assert.assertEquals("0.0",subject.getHsThucHanh().toString());
            Assert.assertEquals("0.3",subject.getHsBaiTapLon().toString());
            Assert.assertEquals("0.5",subject.getHsCuoiKy().toString());
            Assert.assertEquals("Tieng Anh A12",subject.getMonDK());
            Assert.assertEquals("CNPM",subject.getBoMon());
            Assert.assertEquals("CB",subject.getKhoa());

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
        //subject's id is not in database
        Optional<MonHoc> opt = monHocDAO.findById(7);
        Assert.assertFalse(opt.isPresent());

        // subject's id is in database
        opt = monHocDAO.findById(5);
        Assert.assertTrue(opt.isPresent());
        Assert.assertEquals("INT1111",opt.get().getMaMon());
        Assert.assertEquals("Xay dung cac he thong nhung",opt.get().getTenMon());
        Assert.assertEquals(3,opt.get().getSoTinChi().longValue());
        Assert.assertEquals(10,opt.get().getSoTietLT().longValue());
        Assert.assertEquals(20,opt.get().getSoTietBT().longValue());
        Assert.assertEquals(0,opt.get().getSoTietTH().longValue());
        Assert.assertEquals("0.1",opt.get().getHsChuyenCan().toString());
        Assert.assertEquals("0.1",opt.get().getHsKiemTra().toString());
        Assert.assertEquals("0.0",opt.get().getHsThucHanh().toString());
        Assert.assertEquals("0.1",opt.get().getHsBaiTapLon().toString());
        Assert.assertEquals("0.7",opt.get().getHsCuoiKy().toString());
        Assert.assertEquals("Khong",opt.get().getMonDK());
        Assert.assertEquals("CNPM",opt.get().getBoMon());
        Assert.assertEquals("CB",opt.get().getKhoa());

        return;
    }
}