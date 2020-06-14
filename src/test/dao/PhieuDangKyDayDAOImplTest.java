package test.dao;

import api.model.connectdb.ConnectDB;
import api.model.dao.LopHocPhanDAO;
import api.model.dao.PhieuDangKyDayDAO;
import api.model.dao.impl.LopHocPhanDAOImpl;
import api.model.dao.impl.PhieuDangKyDayDAOImpl;
import api.model.entity.LopHocPhan;
import api.model.entity.PhieuDangKyDay;
import org.junit.Assert;
import org.junit.Test;

import javax.swing.text.html.Option;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class PhieuDangKyDayDAOImplTest {

    private PhieuDangKyDayDAO phieuDangKyDayDAO = new PhieuDangKyDayDAOImpl();

    private LopHocPhanDAO lopHocPhanDAO = new LopHocPhanDAOImpl();

    private Connection conn = ConnectDB.getInstance().getConnection();

    @Test
    public void getAll() {
        List<PhieuDangKyDay> teachForms = phieuDangKyDayDAO.getAll();
        Assert.assertNotNull(teachForms);
        Assert.assertEquals(7,teachForms.size());
    }

    @Test
    public void save() {
        LopHocPhan lhp1 = lopHocPhanDAO.findById(1).get();
        lhp1.setPhieuDangKyDayId(2);
        LopHocPhan lhp2 = lopHocPhanDAO.findById(2).get();
        lhp2.setPhieuDangKyDayId(2);

        List<LopHocPhan> courses = new ArrayList<>();
        courses.add(lhp1);
        courses.add(lhp2);

        //update
        PhieuDangKyDay phieuDangKyDay = new PhieuDangKyDay(2,60,5,"D16-300",2,courses,20202);
        try {
            conn.setAutoCommit(false);
            Optional<PhieuDangKyDay> opt = phieuDangKyDayDAO.save(phieuDangKyDay);
            Assert.assertTrue(opt.isPresent());
            Assert.assertEquals(60,opt.get().getSiSo().longValue());
            Assert.assertEquals(5,opt.get().getSoSinhVienDangKy().longValue());
            Assert.assertEquals("D16-300",opt.get().getMaLop());
            Assert.assertEquals(2,opt.get().getNhomLop().longValue());
            List<LopHocPhan> list = opt.get().getListLopHocPhan();
            for (LopHocPhan lopHocPhan:list){
                Assert.assertEquals(2,lopHocPhan.getPhieuDangKyDayId().longValue());
            }
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

        //insert a new teach form
        phieuDangKyDay.setPhieuDangKyDayId(null);
        try {
            conn.setAutoCommit(false);
            Optional<PhieuDangKyDay> opt = phieuDangKyDayDAO.save(phieuDangKyDay);
            Assert.assertTrue(opt.isPresent());
            Assert.assertTrue(opt.get().getPhieuDangKyDayId() > 7);
            Assert.assertEquals(8,phieuDangKyDayDAO.getAll().size());

            PhieuDangKyDay teach_form = phieuDangKyDayDAO.findById(opt.get().getPhieuDangKyDayId()).get();
            Assert.assertEquals(60,opt.get().getSiSo().longValue());
            Assert.assertEquals(5,opt.get().getSoSinhVienDangKy().longValue());
            Assert.assertEquals("D16-300",opt.get().getMaLop());
            Assert.assertEquals(2,opt.get().getNhomLop().longValue());

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
        // id in database
        Optional<PhieuDangKyDay> opt = phieuDangKyDayDAO.findById(8);
        Assert.assertFalse(opt.isPresent());

        // id not in database
        opt = phieuDangKyDayDAO.findById(1);
        Assert.assertTrue(opt.isPresent());
        Assert.assertEquals(40,opt.get().getSiSo().longValue());
        Assert.assertEquals(6,opt.get().getSoSinhVienDangKy().longValue());
        Assert.assertEquals("D16-100",opt.get().getMaLop());
        Assert.assertEquals(1,opt.get().getNhomLop().longValue());
        Assert.assertEquals(20202,opt.get().getHocKy().longValue());

        List<LopHocPhan> courses = lopHocPhanDAO.getByPhieuDangKyDay(opt.get().getPhieuDangKyDayId());
        for (LopHocPhan lopHocPhan: courses){
            Assert.assertEquals(1,lopHocPhan.getPhieuDangKyDayId().longValue());
        }

        return;

    }
}