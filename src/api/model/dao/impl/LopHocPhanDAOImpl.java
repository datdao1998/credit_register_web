package api.model.dao.impl;

import api.model.dao.*;
import api.model.entity.LopHocPhan;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LopHocPhanDAOImpl implements LopHocPhanDAO {


    @Override
    public List<LopHocPhan> getAll() {
        String sql = "SELECT * FROM lop_hoc_phan";
        ArrayList<LopHocPhan> lopHocPhans = new ArrayList<>();
        KipHocDAO kipHocDAO = new KipHocDAOImpl();
        GiangVienDAO giangVienDAO = new GiangVienDAOImpl();
        PhongHocDAO phongHocDAO = new PhongHocDAOImpl();
        MonHocDAO monHocDAO = new MonHocDAOImpl();
        try {
            PreparedStatement ps = connectDB.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LopHocPhan lopHocPhan = new LopHocPhan();
                lopHocPhan.setId(rs.getInt("id"));
                lopHocPhan.setKipHoc(kipHocDAO.findById(rs.getInt("kip_hoc_id")).get());
                lopHocPhan.setGiangVien(giangVienDAO.findById(rs.getInt("giang_vien_id")).get());
                lopHocPhan.setMonHoc(monHocDAO.findById(rs.getInt("mon_hoc_id")).get());
                lopHocPhan.setPhongHoc(phongHocDAO.findById(rs.getInt("phong_hoc_id")).get());
                lopHocPhan.setTuanBatDau(rs.getInt("tuan_bat_dau"));
                lopHocPhan.setTuanKetThuc(rs.getInt("tuan_ket_thuc"));
                lopHocPhan.setPhieuDangKyDayId(rs.getInt("phieu_dang_ky_day_id"));
                lopHocPhans.add(lopHocPhan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lopHocPhans;
    }

    @Override
    public Optional<LopHocPhan> save(LopHocPhan lopHocPhan) {
        if (lopHocPhan.getId()!=null){
            Optional<LopHocPhan> opt = findById(lopHocPhan.getId());
            if(opt.isPresent()){
                String sql = "UPDATE lop_hoc_phan SET  kip_hoc_id = ?,giang_vien_id = ?, mon_hoc_id = ?,phong_hoc_id = ?, tuan_bat_dau = ?,tuan_ket_thuc = ?, phieu_dang_ky_day_id = ? " +
                        "WHERE id = ?";
                try {
                    PreparedStatement statement = connectDB.getConnection().prepareStatement(sql);
                    statement.setInt(1,lopHocPhan.getKipHoc().getKipHocId());
                    statement.setInt(2,lopHocPhan.getGiangVien().getGiangVienId());
                    statement.setInt(3,lopHocPhan.getMonHoc().getMonHocId());
                    statement.setInt(4,lopHocPhan.getPhongHoc().getPhongHocId());
                    statement.setInt(5,lopHocPhan.getTuanBatDau());
                    statement.setInt(6,lopHocPhan.getTuanKetThuc());
                    statement.setInt(7,lopHocPhan.getPhieuDangKyDayId());
                    statement.setInt(8, lopHocPhan.getId());
                    statement.executeUpdate();
                    return findById(lopHocPhan.getId());
                } catch (Exception e) {
                    e.printStackTrace();
                    return Optional.empty();
                }
            }
        }

        String sql = "INSERT INTO lop_hoc_phan(kip_hoc_id,giang_vien_id,mon_hoc_id,phong_hoc_id,tuan_bat_dau,tuan_ket_thuc,phieu_dang_ky_day_id) VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement statement = connectDB.getConnection().prepareStatement(sql);
            statement.setInt(1,lopHocPhan.getKipHoc().getKipHocId());
            statement.setInt(2,lopHocPhan.getGiangVien().getGiangVienId());
            statement.setInt(3,lopHocPhan.getMonHoc().getMonHocId());
            statement.setInt(4,lopHocPhan.getPhongHoc().getPhongHocId());
            statement.setInt(5,lopHocPhan.getTuanBatDau());
            statement.setInt(6,lopHocPhan.getTuanKetThuc());
            statement.setInt(7,lopHocPhan.getPhieuDangKyDayId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }

        return Optional.of(getAll().get(getAll().size() - 1));
    }

    @Override
    public Optional<LopHocPhan> findById(Integer id) {
        String sql = "SELECT * FROM lop_hoc_phan WHERE id = ?";
        KipHocDAO kipHocDAO = new KipHocDAOImpl();
        GiangVienDAO giangVienDAO = new GiangVienDAOImpl();
        PhongHocDAO phongHocDAO = new PhongHocDAOImpl();
        MonHocDAO monHocDAO = new MonHocDAOImpl();
        LopHocPhan lopHocPhan = new LopHocPhan();
        try {
            PreparedStatement ps = connectDB.getConnection().prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lopHocPhan.setId(rs.getInt("id"));
                lopHocPhan.setKipHoc(kipHocDAO.findById(rs.getInt("kip_hoc_id")).get());
                lopHocPhan.setGiangVien(giangVienDAO.findById(rs.getInt("giang_vien_id")).get());
                lopHocPhan.setMonHoc(monHocDAO.findById(rs.getInt("mon_hoc_id")).get());
                lopHocPhan.setPhongHoc(phongHocDAO.findById(rs.getInt("phong_hoc_id")).get());
                lopHocPhan.setTuanBatDau(rs.getInt("tuan_bat_dau"));
                lopHocPhan.setTuanKetThuc(rs.getInt("tuan_ket_thuc"));
                lopHocPhan.setPhieuDangKyDayId(rs.getInt("phieu_dang_ky_day_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (lopHocPhan.getId() == null) return Optional.empty();
        return Optional.of(lopHocPhan);
    }

    @Override
    public List<LopHocPhan> getByPhieuDangKyDay(Integer phieuDangKyDayId) {
        String sql = "SELECT * FROM lop_hoc_phan WHERE phieu_dang_ky_day_id = ?";
        ArrayList<LopHocPhan> lopHocPhans = new ArrayList<>();
        KipHocDAO kipHocDAO = new KipHocDAOImpl();
        GiangVienDAO giangVienDAO = new GiangVienDAOImpl();
        PhongHocDAO phongHocDAO = new PhongHocDAOImpl();
        MonHocDAO monHocDAO = new MonHocDAOImpl();
        try {
            PreparedStatement ps = connectDB.getConnection().prepareStatement(sql);
            ps.setInt(1,phieuDangKyDayId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LopHocPhan lopHocPhan = new LopHocPhan();
                lopHocPhan.setId(rs.getInt("id"));
                lopHocPhan.setKipHoc(kipHocDAO.findById(rs.getInt("kip_hoc_id")).get());
                lopHocPhan.setGiangVien(giangVienDAO.findById(rs.getInt("giang_vien_id")).get());
                lopHocPhan.setMonHoc(monHocDAO.findById(rs.getInt("mon_hoc_id")).get());
                lopHocPhan.setPhongHoc(phongHocDAO.findById(rs.getInt("phong_hoc_id")).get());
                lopHocPhan.setTuanBatDau(rs.getInt("tuan_bat_dau"));
                lopHocPhan.setTuanKetThuc(rs.getInt("tuan_ket_thuc"));
                lopHocPhan.setPhieuDangKyDayId(rs.getInt("phieu_dang_ky_day_id"));
                lopHocPhans.add(lopHocPhan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lopHocPhans;
    }
}
