package api.model.dao.impl;

import api.model.dao.PhieuDangKyDayDAO;
import api.model.dao.PhieuDangKyHocDAO;
import api.model.dao.SinhVienDAO;
import api.model.entity.PhieuDangKyHoc;
import com.google.gson.internal.bind.util.ISO8601Utils;
import common.constant.FPConstant;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PhieuDangKyHocDAOImpl implements PhieuDangKyHocDAO {


    @Override
    public List<PhieuDangKyHoc> getAll() {
        String sql = "SELECT * FROM phieu_dang_ky_hoc";
        ArrayList<PhieuDangKyHoc> phieuDangKyHocs = new ArrayList<>();
        SinhVienDAO sinhVienDAO = new SinhVienDAOImpl();
        PhieuDangKyDayDAO phieuDangKyDayDAO = new PhieuDangKyDayDAOImpl();
        try {
            PreparedStatement statement = connectDB.getConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                PhieuDangKyHoc phieuDangKyHoc = new PhieuDangKyHoc();
                phieuDangKyHoc.setPhieuDangKyHocId(rs.getInt("id"));
                phieuDangKyHoc.setSinhVien(sinhVienDAO.findById(rs.getInt("sinh_vien_id")).get());
                phieuDangKyHoc.setPhieuDangKyDay(phieuDangKyDayDAO.findById(rs.getInt("phieu_dang_ky_day_id")).get());
                phieuDangKyHoc.setThoiGianDangKy(rs.getString("thoi_gian_dang_ky"));
                phieuDangKyHoc.setHocPhi(rs.getDouble("hoc_phi"));
                phieuDangKyHoc.setTrangThai(rs.getString("trang_thai"));
                phieuDangKyHocs.add(phieuDangKyHoc);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return phieuDangKyHocs;
    }

    @Override
    public Optional<PhieuDangKyHoc> save(PhieuDangKyHoc phieuDangKyHoc) {
        Optional<PhieuDangKyHoc> opt = findById(phieuDangKyHoc.getPhieuDangKyHocId());
        if (opt.isPresent()){
            String sql = "UPDATE phieu_dang_ky_hoc SET sinh_vien_id = ?, phieu_dang_ky_day_id = ?, thoi_gian_dang_ky = ?, hoc_phi = ?, trang_thai = ?  WHERE id = ?";
            try {
                PreparedStatement statement = connectDB.getConnection().prepareStatement(sql);
                statement.setInt(1, phieuDangKyHoc.getSinhVien().getSinhVienId());
                statement.setInt(2, phieuDangKyHoc.getPhieuDangKyDay().getPhieuDangKyDayId());
                statement.setString(3, phieuDangKyHoc.getThoiGianDangKy());
                statement.setDouble(4, phieuDangKyHoc.getHocPhi());
                statement.setString(5,phieuDangKyHoc.getTrangThai());
                statement.setInt(6, phieuDangKyHoc.getPhieuDangKyHocId());
                statement.executeUpdate();
                return findById(phieuDangKyHoc.getPhieuDangKyHocId());
            } catch (Exception e) {
                e.printStackTrace();
                return Optional.empty();
            }
        }

        String sql = "INSERT INTO phieu_dang_ky_hoc(sinh_vien_id,phieu_dang_ky_day_id,thoi_gian_dang_ky , hoc_phi,trang_thai) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement statement = connectDB.getConnection().prepareStatement(sql);
            statement.setInt(1, phieuDangKyHoc.getSinhVien().getSinhVienId());
            statement.setInt(2, phieuDangKyHoc.getPhieuDangKyDay().getPhieuDangKyDayId());
            statement.setString(3, phieuDangKyHoc.getThoiGianDangKy());
            statement.setDouble(4, phieuDangKyHoc.getHocPhi());
            statement.setString(5, phieuDangKyHoc.getTrangThai());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }

        return Optional.of(getAll().get(getAll().size() - 1));
    }

    @Override
    public Optional<PhieuDangKyHoc> findById(Integer id) {
        String sql = "SELECT * FROM phieu_dang_ky_hoc WHERE id = " + id;
        PhieuDangKyHoc phieuDangKyHoc = new PhieuDangKyHoc();
        SinhVienDAO sinhVienDAO = new SinhVienDAOImpl();
        PhieuDangKyDayDAO phieuDangKyDayDAO = new PhieuDangKyDayDAOImpl();
        try {
            PreparedStatement statement = connectDB.getConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                phieuDangKyHoc.setPhieuDangKyHocId(rs.getInt("id"));
                phieuDangKyHoc.setSinhVien(sinhVienDAO.findById(rs.getInt("sinh_vien_id")).get());
                phieuDangKyHoc.setPhieuDangKyDay(phieuDangKyDayDAO.findById(rs.getInt("phieu_dang_ky_day_id")).get());
                phieuDangKyHoc.setThoiGianDangKy(rs.getString("thoi_gian_dang_ky"));
                phieuDangKyHoc.setHocPhi(rs.getDouble("hoc_phi"));
                phieuDangKyHoc.setTrangThai(rs.getString("trang_thai"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (phieuDangKyHoc.getPhieuDangKyHocId() == null) return Optional.empty();
        return Optional.of(phieuDangKyHoc);
    }

    @Override
    public List<PhieuDangKyHoc> getByStatus(String trangThai) {
        String sql = "SELECT * FROM phieu_dang_ky_hoc WHERE trang_thai = ?";
        ArrayList<PhieuDangKyHoc> phieuDangKyHocs = new ArrayList<>();
        SinhVienDAO sinhVienDAO = new SinhVienDAOImpl();
        PhieuDangKyDayDAO phieuDangKyDayDAO = new PhieuDangKyDayDAOImpl();
        try {
            PreparedStatement statement = connectDB.getConnection().prepareStatement(sql);
            statement.setString(1,trangThai);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                PhieuDangKyHoc phieuDangKyHoc = new PhieuDangKyHoc();
                phieuDangKyHoc.setPhieuDangKyHocId(rs.getInt("id"));
                phieuDangKyHoc.setSinhVien(sinhVienDAO.findById(rs.getInt("sinh_vien_id")).get());
                phieuDangKyHoc.setPhieuDangKyDay(phieuDangKyDayDAO.findById(rs.getInt("phieu_dang_ky_day_id")).get());
                phieuDangKyHoc.setThoiGianDangKy(rs.getString("thoi_gian_dang_ky"));
                phieuDangKyHoc.setHocPhi(rs.getDouble("hoc_phi"));
                phieuDangKyHoc.setTrangThai(rs.getString("trang_thai"));
                phieuDangKyHocs.add(phieuDangKyHoc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return phieuDangKyHocs;
    }

}
