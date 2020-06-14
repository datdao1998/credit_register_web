package api.model.dao.impl;

import api.model.dao.NguoiDungDAO;
import api.model.entity.NguoiDung;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NguoiDungDAOImpl implements NguoiDungDAO {


    @Override
    public Optional<NguoiDung> findByUserName(String userName) {
        NguoiDung nguoiDung = new NguoiDung();
        String query = "SELECT * FROM nguoi_dung WHERE ten_dang_nhap = ?";
        try {
            PreparedStatement statement = connectDB.getConnection().prepareStatement(query);
            statement.setString(1, userName);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                nguoiDung.setNguoiDungId(rs.getInt("id"));
                nguoiDung.setTenDangNhap(rs.getString("ten_dang_nhap"));
                nguoiDung.setMatKhau(rs.getString("mat_khau"));
                nguoiDung.setHoTen(rs.getString("ho_ten"));
                nguoiDung.setGioiTinh(rs.getString("gioi_tinh"));
                nguoiDung.setNgaySinh(rs.getString("ngay_sinh"));
                nguoiDung.setDiaChi(rs.getString("dia_chi"));
                nguoiDung.setVaiTro(rs.getString("vai_tro"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (nguoiDung.getNguoiDungId() == null) {
            return Optional.empty();
        }
        return Optional.of(nguoiDung);
    }



    //search user by username,name and role
    @Override
    public List<NguoiDung> search(String userName, String name, String vaiTro) {
        String sql = "SELECT * FROM nguoi_dung " +
                "WHERE (? is null OR ten_dang_nhap = ?) " +
                "AND (? is null OR ho_ten LIKE ?) " +
                "AND (? is null OR vai_tro = ?) ";
        ArrayList<NguoiDung> nguoiDungs = new ArrayList<>();
        try {
            PreparedStatement stament = connectDB.getConnection().prepareStatement(sql);
            stament.setString(1, userName);
            stament.setString(2,userName);
            stament.setString(3, name);
            stament.setString(4, "%" + name + "%");
            stament.setString(5, vaiTro);
            stament.setString(6, vaiTro);
            ResultSet rs = stament.executeQuery();
            while (rs.next()) {
                NguoiDung nguoiDung = new NguoiDung();
                nguoiDung.setNguoiDungId(rs.getInt("id"));
                nguoiDung.setTenDangNhap(rs.getString("ten_dang_nhap"));
                nguoiDung.setGioiTinh(rs.getString("gioi_tinh"));
                nguoiDung.setMatKhau(rs.getString("mat_khau"));
                nguoiDung.setHoTen(rs.getString("ho_ten"));
                nguoiDung.setNgaySinh(rs.getString("ngay_sinh"));
                nguoiDung.setDiaChi(rs.getString("dia_chi"));
                nguoiDung.setVaiTro(rs.getString("vai_tro"));
                nguoiDungs.add(nguoiDung);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nguoiDungs;
    }

    @Override
    public List<NguoiDung> getAll() {
        String sql = "SELECT * FROM nguoi_dung";
        ArrayList<NguoiDung> nguoiDungs = new ArrayList<>();
        try {
            PreparedStatement ps = connectDB.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NguoiDung nguoiDung = new NguoiDung();
                nguoiDung.setNguoiDungId(rs.getInt("id"));
                nguoiDung.setTenDangNhap(rs.getString("ten_dang_nhap"));
                nguoiDung.setMatKhau(rs.getString("mat_khau"));
                nguoiDung.setHoTen(rs.getString("ho_ten"));
                nguoiDung.setGioiTinh(rs.getString("gioi_tinh"));
                nguoiDung.setNgaySinh(rs.getString("ngay_sinh"));
                nguoiDung.setDiaChi(rs.getString("dia_chi"));
                nguoiDung.setVaiTro(rs.getString("vai_tro"));
                nguoiDungs.add(nguoiDung);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nguoiDungs;
    }

    @Override
    public Optional<NguoiDung> save(NguoiDung nguoiDung) {
        if (nguoiDung.getNguoiDungId()!=null){
            Optional<NguoiDung> opt = findById(nguoiDung.getNguoiDungId());
            if (opt.isPresent()){
                String sql = "UPDATE nguoi_dung SET ten_dang_nhap = ?,mat_khau = ?,ho_ten = ?, ngay_sinh = ?, dia_chi = ?, vai_tro = ? WHERE id = ?";
                try {
                    PreparedStatement statement = connectDB.getConnection().prepareStatement(sql);
                    statement.setString(1, nguoiDung.getTenDangNhap());
                    statement.setString(2, nguoiDung.getMatKhau());
                    statement.setString(3, nguoiDung.getHoTen());
                    statement.setString(4, nguoiDung.getNgaySinh());
                    statement.setString(5, nguoiDung.getDiaChi());
                    statement.setString(6, nguoiDung.getVaiTro());
                    statement.setInt(7, nguoiDung.getNguoiDungId());
                    statement.executeUpdate();
                    return findById(nguoiDung.getNguoiDungId());
                } catch (Exception e) {
                    e.printStackTrace();
                    return Optional.empty();
                }
            }
        }

        String sql = "INSERT INTO nguoi_dung(ten_dang_nhap,mat_khau,ho_ten,ngay_sinh,gioi_tinh,dia_chi,vai_tro) VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement statement = connectDB.getConnection().prepareStatement(sql);
            statement.setString(1, nguoiDung.getTenDangNhap());
            statement.setString(2, nguoiDung.getMatKhau());
            statement.setString(3, nguoiDung.getHoTen());
            statement.setString(4, nguoiDung.getNgaySinh());
            statement.setString(5,nguoiDung.getGioiTinh());
            statement.setString(6, nguoiDung.getDiaChi());
            statement.setString(7, nguoiDung.getVaiTro());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }

        return Optional.of(getAll().get(getAll().size() - 1));
    }

    @Override
    public Optional<NguoiDung> findById(Integer id) {
        NguoiDung nguoiDung = new NguoiDung();
        String query = "SELECT * FROM nguoi_dung WHERE id = " + id;
        try {
            PreparedStatement statement = connectDB.getConnection().prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                nguoiDung.setNguoiDungId(rs.getInt("id"));
                nguoiDung.setTenDangNhap(rs.getString("ten_dang_nhap"));
                nguoiDung.setMatKhau(rs.getString("mat_khau"));
                nguoiDung.setHoTen(rs.getString("ho_ten"));
                nguoiDung.setNgaySinh(rs.getString("ngay_sinh"));
                nguoiDung.setGioiTinh(rs.getString("gioi_tinh"));
                nguoiDung.setDiaChi(rs.getString("dia_chi"));
                nguoiDung.setVaiTro(rs.getString("vai_tro"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (nguoiDung.getNguoiDungId() == null) {
            return Optional.empty();
        }
        return Optional.of(nguoiDung);
    }

}
