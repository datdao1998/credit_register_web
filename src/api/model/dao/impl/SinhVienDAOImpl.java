package api.model.dao.impl;

import api.model.dao.SinhVienDAO;
import api.model.entity.NguoiDung;
import api.model.entity.SinhVien;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SinhVienDAOImpl implements SinhVienDAO {

    @Override
    public List<SinhVien> findByStudentCode(String code) {
        String sql = "SELECT * FROM sinh_vien WHERE maSV = ?";
        ArrayList<SinhVien> sinhViens = new ArrayList<>();
        try {
            PreparedStatement ps = connectDB.getConnection().prepareStatement(sql);
            ps.setString(1,code);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SinhVien sinhVien = new SinhVien();
                sinhVien.setSinhVienId(rs.getInt("id"));
                sinhVien.setTblNguoiDungId(rs.getInt("nguoi_dung_id"));
                sinhVien.setMaSV(rs.getString("maSV"));
                sinhVien.setKhoa(rs.getString("khoa"));
                sinhVien.setNganh(rs.getString("nganh"));
                sinhVien.setGpa(rs.getDouble("gpa"));
                sinhVien.setStcTichLuy(rs.getInt("so_tin_tich_luy"));
                sinhVien.setHocLuc(rs.getString("hoc_luc"));
                sinhVien.setLop(rs.getString("lop"));
                sinhViens.add(sinhVien);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sinhViens;
    }


    @Override
    public List<SinhVien> getAll() {
        String sql = "SELECT * FROM sinh_vien";
        ArrayList<SinhVien> sinhViens = new ArrayList<>();
        try {
            PreparedStatement ps = connectDB.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SinhVien sinhVien = new SinhVien();
                sinhVien.setSinhVienId(rs.getInt("id"));
                sinhVien.setTblNguoiDungId(rs.getInt("nguoi_dung_id"));
                sinhVien.setMaSV(rs.getString("maSV"));
                sinhVien.setKhoa(rs.getString("khoa"));
                sinhVien.setNganh(rs.getString("nganh"));
                sinhVien.setGpa(rs.getDouble("gpa"));
                sinhVien.setStcTichLuy(rs.getInt("so_tin_tich_luy"));
                sinhVien.setHocLuc(rs.getString("hoc_luc"));
                sinhVien.setLop(rs.getString("lop"));
                sinhViens.add(sinhVien);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sinhViens;
    }

    @Override
    public Optional<SinhVien> save(SinhVien sinhVien) {
        if(sinhVien.getSinhVienId()!=null){
            Optional<SinhVien> opt = findById(sinhVien.getSinhVienId());
            if(opt.isPresent()){
                String sql = "UPDATE sinh_vien SET nguoi_dung_id = ?, maSV = ?, khoa = ?, nganh = ?, gpa =?, so_tin_tich_luy = ?, hoc_luc = ?, lop = ? WHERE id = ?";
                try {
                    PreparedStatement statement = connectDB.getConnection().prepareStatement(sql);
                    statement.setInt(1, sinhVien.getTblNguoiDungId());
                    statement.setString(2, sinhVien.getMaSV());
                    statement.setString(3, sinhVien.getKhoa());
                    statement.setString(4, sinhVien.getNganh());
                    statement.setDouble(5, sinhVien.getGpa());
                    statement.setInt(6, sinhVien.getStcTichLuy());
                    statement.setString(7, sinhVien.getHocLuc());
                    statement.setString(8, sinhVien.getLop());
                    statement.setInt(9, sinhVien.getSinhVienId());
                    statement.executeUpdate();
                    return findById(sinhVien.getSinhVienId());
                } catch (Exception e) {
                    e.printStackTrace();
                    return Optional.empty();
                }
            }
        }

        String sql = "INSERT INTO sinh_vien(nguoi_dung_id,maSV,khoa,nganh,gpa,so_tin_tich_luy,hoc_luc,lop) VALUES (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement statement = connectDB.getConnection().prepareStatement(sql);
            statement.setInt(1, sinhVien.getTblNguoiDungId());
            statement.setString(2, sinhVien.getMaSV());
            statement.setString(3, sinhVien.getKhoa());
            statement.setString(4, sinhVien.getNganh());
            statement.setDouble(5, sinhVien.getGpa());
            statement.setInt(6, sinhVien.getStcTichLuy());
            statement.setString(7, sinhVien.getHocLuc());
            statement.setString(8, sinhVien.getLop());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }

        return Optional.of(getAll().get(getAll().size() - 1));
    }

    @Override
    public Optional<SinhVien> findById(Integer id) {
        String sql = "SELECT * FROM sinh_vien WHERE id = " + id;
        SinhVien sinhVien = new SinhVien();
        try {
            PreparedStatement ps = connectDB.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                sinhVien.setSinhVienId(rs.getInt("id"));
                sinhVien.setTblNguoiDungId(rs.getInt("nguoi_dung_id"));
                sinhVien.setMaSV(rs.getString("maSV"));
                sinhVien.setKhoa(rs.getString("khoa"));
                sinhVien.setNganh(rs.getString("nganh"));
                sinhVien.setGpa(rs.getDouble("gpa"));
                sinhVien.setStcTichLuy(rs.getInt("so_tin_tich_luy"));
                sinhVien.setHocLuc(rs.getString("hoc_luc"));
                sinhVien.setLop(rs.getString("lop"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (sinhVien.getSinhVienId() == null) return Optional.empty();
        return Optional.of(sinhVien);
    }
}
