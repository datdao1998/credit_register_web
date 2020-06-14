package api.model.dao.impl;

import api.model.dao.GiangVienDAO;
import api.model.dao.LopHocPhanDAO;
import api.model.dao.PhieuDangKyDayDAO;
import api.model.entity.PhieuDangKyDay;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PhieuDangKyDayDAOImpl implements PhieuDangKyDayDAO {

    @Override
    public List<PhieuDangKyDay> getAll() {
        String sql = "SELECT * FROM phieu_dang_ky_day";
        ArrayList<PhieuDangKyDay> phieuDangKyDays = new ArrayList<>();
        LopHocPhanDAO lopHocPhanDAO = new LopHocPhanDAOImpl();
        try {
            PreparedStatement statement = connectDB.getConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                PhieuDangKyDay phieuDangKyDay = new PhieuDangKyDay();
                phieuDangKyDay.setPhieuDangKyDayId(rs.getInt("id"));
                phieuDangKyDay.setSiSo(rs.getInt("si_so"));
                phieuDangKyDay.setSoSinhVienDangKy(rs.getInt("sv_dang_ky"));
                phieuDangKyDay.setHocKy(rs.getInt("hoc_ky"));
                phieuDangKyDay.setMaLop(rs.getString("ma_lop"));
                phieuDangKyDay.setNhomLop(rs.getInt("nhom_lop"));
                phieuDangKyDay.setListLopHocPhan(lopHocPhanDAO.getByPhieuDangKyDay(phieuDangKyDay.getPhieuDangKyDayId()));
                phieuDangKyDays.add(phieuDangKyDay);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return phieuDangKyDays;
    }

    @Override
    public Optional<PhieuDangKyDay> save(PhieuDangKyDay phieuDangKyDay) {
        LopHocPhanDAO lopHocPhanDAO = new LopHocPhanDAOImpl();
        if (phieuDangKyDay.getPhieuDangKyDayId()!=null){
            Optional<PhieuDangKyDay> opt = findById(phieuDangKyDay.getPhieuDangKyDayId());
            if (opt.isPresent()){
                String sql = "UPDATE phieu_dang_ky_day SET si_so = ?,sv_dang_ky = ?, hoc_ky = ?, ma_lop = ?, nhom_lop = ?  WHERE id = ?";
                try {
                    PreparedStatement statement = connectDB.getConnection().prepareStatement(sql);
                    // dang fix code
                    statement.setInt(1, phieuDangKyDay.getSiSo());
                    statement.setInt(2, phieuDangKyDay.getSoSinhVienDangKy());
                    statement.setInt(3, phieuDangKyDay.getHocKy());
                    statement.setString(4, phieuDangKyDay.getMaLop());
                    statement.setInt(5,phieuDangKyDay.getNhomLop());
                    statement.setInt(6,phieuDangKyDay.getPhieuDangKyDayId());
                    statement.executeUpdate();
                    return findById(phieuDangKyDay.getPhieuDangKyDayId());
                } catch (Exception e) {
                    e.printStackTrace();
                    return Optional.empty();
                }
            }
        }

        String sql = "INSERT INTO phieu_dang_ky_day(si_so,sv_dang_ky,hoc_ky,ma_lop,nhom_lop) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement statement = connectDB.getConnection().prepareStatement(sql);
            statement.setInt(1, phieuDangKyDay.getSiSo());
            statement.setInt(2, phieuDangKyDay.getSoSinhVienDangKy());
            statement.setInt(3, phieuDangKyDay.getHocKy());
            statement.setString(4, phieuDangKyDay.getMaLop());
            statement.setInt(5,phieuDangKyDay.getNhomLop());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }

        return Optional.of(getAll().get(getAll().size() - 1));
    }

    @Override
    public Optional<PhieuDangKyDay> findById(Integer id) {
        String sql = "SELECT * FROM phieu_dang_ky_day WHERE id = " + id;
        PhieuDangKyDay phieuDangKyDay = new PhieuDangKyDay();
        GiangVienDAO giangVienDAO = new GiangVienDAOImpl();
        LopHocPhanDAO lopHocPhanDAO = new LopHocPhanDAOImpl();
        try {
            PreparedStatement statement = connectDB.getConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                phieuDangKyDay.setPhieuDangKyDayId(rs.getInt("id"));
                phieuDangKyDay.setSiSo(rs.getInt("si_so"));
                phieuDangKyDay.setSoSinhVienDangKy(rs.getInt("sv_dang_ky"));
                phieuDangKyDay.setHocKy(rs.getInt("hoc_ky"));
                phieuDangKyDay.setMaLop(rs.getString("ma_lop"));
                phieuDangKyDay.setNhomLop(rs.getInt("nhom_lop"));
                phieuDangKyDay.setListLopHocPhan(lopHocPhanDAO.getByPhieuDangKyDay(phieuDangKyDay.getPhieuDangKyDayId()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (phieuDangKyDay.getPhieuDangKyDayId() == null) return Optional.empty();
        return Optional.of(phieuDangKyDay);
    }

}
