package api.model.dao.impl;

import api.model.dao.GiangVienDAO;
import api.model.entity.GiangVien;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GiangVienDAOImpl implements GiangVienDAO {

    @Override
    public List<GiangVien> search(String khoa, String nganh, String hocVi, String boMon) {
        String sql = "SELECT * FROM giang_vien "
                + "WHERE(? is null OR khoa LIKE ?) "
                + "AND (? is null OR nganh LIKE ?) "
                + "AND (? is null OR hoc_vi LIKE ?) "
                + "AND (? is null OR bo_mon LIKE ?)";
        ArrayList<GiangVien> giangViens = new ArrayList<>();
        try {
            PreparedStatement stament = connectDB.getConnection().prepareStatement(sql);
            stament.setString(1, khoa);
            stament.setString(2, "%" + khoa + "%");
            stament.setString(3, nganh);
            stament.setString(4, "%" + nganh + "%");
            stament.setString(5, hocVi);
            stament.setString(6, "%" + hocVi + "%");
            stament.setString(7, boMon);
            stament.setString(8, "%" + boMon + "%");
            ResultSet rs = stament.executeQuery();
            while (rs.next()) {
                GiangVien giangVien = new GiangVien();
                giangVien.setGiangVienId(rs.getInt("id"));
                giangVien.setTblNguoiDungId(rs.getInt("nguoi_dung_id"));
                giangVien.setTenGiangVien(rs.getString("ten_GV"));
                giangVien.setKhoa(rs.getString("khoa"));
                giangVien.setNganh(rs.getString("nganh"));
                giangVien.setHocVi(rs.getString("hoc_vi"));
                giangVien.setBoMon(rs.getString("bo_mon"));
                giangViens.add(giangVien);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return giangViens;
    }

    @Override
    public List<GiangVien> getAll() {
        String sql = "SELECT * FROM giang_vien";
        ArrayList<GiangVien> giangViens = new ArrayList<>();
        try {
            PreparedStatement ps = connectDB.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                GiangVien giangVien = new GiangVien();
                giangVien.setGiangVienId(rs.getInt("id"));
                giangVien.setTblNguoiDungId(rs.getInt("nguoi_dung_id"));
                giangVien.setTenGiangVien(rs.getString("ten_GV"));
                giangVien.setKhoa(rs.getString("khoa"));
                giangVien.setNganh(rs.getString("nganh"));
                giangVien.setHocVi(rs.getString("hoc_vi"));
                giangVien.setBoMon(rs.getString("bo_mon"));
                giangViens.add(giangVien);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return giangViens;
    }

    @Override
    public Optional<GiangVien> save(GiangVien giangVien) {
       if(giangVien.getGiangVienId()!= null){
            Optional<GiangVien> opt = findById(giangVien.getGiangVienId());
            if(opt.isPresent()){
                String sql = "UPDATE giang_vien SET nguoi_dung_id = ?,ten_GV = ?, khoa = ?, nganh = ?, hoc_vi = ?, bo_mon = ? WHERE id = ?";
                try {
                    PreparedStatement statement = connectDB.getConnection().prepareStatement(sql);
                    statement.setInt(1, giangVien.getTblNguoiDungId());
                    statement.setString(2,giangVien.getTenGiangVien());
                    statement.setString(3, giangVien.getKhoa());
                    statement.setString(4, giangVien.getNganh());
                    statement.setString(5, giangVien.getHocVi());
                    statement.setString(6, giangVien.getBoMon());
                    statement.setInt(7, giangVien.getGiangVienId());
                    statement.executeUpdate();
                    return findById(giangVien.getGiangVienId());
                } catch (Exception e) {
                    e.printStackTrace();
                    return Optional.empty();
                }
            }
       }

        String sql = "INSERT INTO giang_vien(nguoi_dung_id,ten_GV,khoa,nganh,hoc_vi,bo_mon) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement statement = connectDB.getConnection().prepareStatement(sql);
            statement.setInt(1, giangVien.getTblNguoiDungId());
            statement.setString(2,giangVien.getTenGiangVien());
            statement.setString(3, giangVien.getKhoa());
            statement.setString(4, giangVien.getNganh());
            statement.setString(5, giangVien.getHocVi());
            statement.setString(6, giangVien.getBoMon());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }

        return Optional.of(getAll().get(getAll().size() - 1));


    }

    @Override
    public Optional<GiangVien> findById(Integer id) {
        String sql = "SELECT * FROM giang_vien WHERE id = " + id;
        GiangVien giangVien = new GiangVien();
        try {
            PreparedStatement ps = connectDB.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                giangVien.setGiangVienId(rs.getInt("id"));
                giangVien.setTblNguoiDungId(rs.getInt("nguoi_dung_id"));
                giangVien.setTenGiangVien(rs.getString("ten_GV"));
                giangVien.setKhoa(rs.getString("khoa"));
                giangVien.setNganh(rs.getString("nganh"));
                giangVien.setHocVi(rs.getString("hoc_vi"));
                giangVien.setBoMon(rs.getString("bo_mon"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(giangVien.getGiangVienId() == null ) return Optional.empty();
        return Optional.of(giangVien);
    }
}
