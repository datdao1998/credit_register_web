package test.service;

import api.model.connectdb.ConnectDB;
import api.model.entity.LopHocPhan;
import api.model.entity.PhieuDangKyDay;
import api.service.LopHocPhanService;
import api.service.PhieuDangKyDayService;
import api.service.impl.LopHocPhanServiceImpl;
import api.service.impl.PhieuDangKyDayServiceImpl;
import common.exception.FPException;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class PhieuDangKyDayServiceImplTest {

    private PhieuDangKyDayService service = new PhieuDangKyDayServiceImpl();

    private LopHocPhanService lopHocPhanService = new LopHocPhanServiceImpl();

    private Connection conn = ConnectDB.getInstance().getConnection();

    @Test
    public void findById() throws FPException {
        Optional<PhieuDangKyDay> opt = service.findById(5);
        Assert.assertTrue(opt.isPresent());
        Assert.assertEquals(40,opt.get().getSiSo().longValue());
        Assert.assertEquals(1,opt.get().getSoSinhVienDangKy().longValue());
        Assert.assertEquals("D16-220",opt.get().getMaLop());
        Assert.assertEquals(5,opt.get().getNhomLop().longValue());
        Assert.assertEquals(20202,opt.get().getHocKy().longValue());
        List<LopHocPhan> courses = opt.get().getListLopHocPhan();
        for(LopHocPhan lopHocPhan : courses){
            Assert.assertEquals(5, lopHocPhan.getPhieuDangKyDayId().longValue());
        }
    }

    @Test(expected = FPException.NotFoundEntityException.class)
    public void findByIdException() throws Exception{
        Optional<PhieuDangKyDay> opt = service.findById(8);
    }

    @Test
    public void update() throws FPException {
        LopHocPhan lhp1 = lopHocPhanService.findById(1).get();
        lhp1.setPhieuDangKyDayId(1);
        LopHocPhan lhp2 = lopHocPhanService.findById(2).get();
        lhp2.setPhieuDangKyDayId(1);
        List<LopHocPhan> list = new ArrayList<>();
        list.add(lhp1);
        list.add(lhp2);
        PhieuDangKyDay phieuDangKyDay = new PhieuDangKyDay(null,50,0,"D16-333",2,list,20202);
        Integer id = 1;
        try {
            conn.setAutoCommit(false);
            Optional<PhieuDangKyDay> opt = service.update(id,phieuDangKyDay);
            Assert.assertTrue(opt.isPresent());
            Assert.assertEquals(50,opt.get().getSiSo().longValue());
            Assert.assertEquals("D16-333",opt.get().getMaLop());

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
        LopHocPhan lhp1 = lopHocPhanService.findById(1).get();
        lhp1.setPhieuDangKyDayId(8);
        LopHocPhan lhp2 = lopHocPhanService.findById(2).get();
        lhp2.setPhieuDangKyDayId(8);
        List<LopHocPhan> list = new ArrayList<>();
        list.add(lhp1);
        list.add(lhp2);
        PhieuDangKyDay phieuDangKyDay = new PhieuDangKyDay(null,50,0,"D16-333",2,list,20202);
        Integer id = 8;
        Optional<PhieuDangKyDay> opt = service.update(id,phieuDangKyDay);
    }

    @Test
    public void searchBySubjectAndSemester() {
        List<PhieuDangKyDay> list = service.searchBySubjectAndSemester("INT1448",20202);
        Assert.assertNotNull(list);
        Assert.assertEquals(2,list.size());
    }
}