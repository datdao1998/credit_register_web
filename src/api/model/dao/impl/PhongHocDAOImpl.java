package api.model.dao.impl;

import api.model.dao.PhongHocDAO;
import api.model.entity.PhongHoc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PhongHocDAOImpl implements PhongHocDAO {

    @Override
    public Optional<PhongHoc> findByName(String name) {
        PhongHoc phongHoc = new PhongHoc();
        String query = "SELECT * FROM phong_hoc WHERE ten_phong = ?";
        try {
            PreparedStatement statement = connectDB.getConnection().prepareStatement(query);
            statement.setString(1,name);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                phongHoc.setPhongHocId(rs.getInt("id"));
                phongHoc.setTenPhong(rs.getString("ten_phong"));
                phongHoc.setSucChua(rs.getInt("suc_chua"));
                phongHoc.setViTri(rs.getString("vi_tri"));
                phongHoc.setMoTa(rs.getString("mo_ta"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (phongHoc.getPhongHocId() == null) {
            return Optional.empty();
        }
        return Optional.of(phongHoc);
    }

    @Override
    public List<PhongHoc> getAll() {
        String sql = "SELECT * FROM phong_hoc";
        ArrayList<PhongHoc> phongHocs = new ArrayList<>();
        try {
            PreparedStatement ps = connectDB.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PhongHoc phongHoc = new PhongHoc();
                phongHoc.setPhongHocId(rs.getInt("id"));
                phongHoc.setTenPhong(rs.getString("ten_phong"));
                phongHoc.setSucChua(rs.getInt("suc_chua"));
                phongHoc.setViTri(rs.getString("vi_tri"));
                phongHoc.setMoTa(rs.getString("mo_ta"));
                phongHocs.add(phongHoc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return phongHocs;
    }

    @Override
    public Optional<PhongHoc> save(PhongHoc phongHoc) {

        if(phongHoc.getPhongHocId()!=null){
            Optional<PhongHoc> opt = findById(phongHoc.getPhongHocId());
            if (opt.isPresent()){
                String sql = "UPDATE phong_hoc SET ten_phong = ?, suc_chua = ? WHERE id = ?";
                try {
                    PreparedStatement statement = connectDB.getConnection().prepareStatement(sql);
                    statement.setString(1, phongHoc.getTenPhong());
                    statement.setInt(2, phongHoc.getSucChua());
                    statement.setInt(3, phongHoc.getPhongHocId());
                    statement.executeUpdate();
                    return findById(phongHoc.getPhongHocId());
                } catch (Exception e) {
                    e.printStackTrace();
                    return Optional.empty();
                }
            }
        }

        String sql = "INSERT INTO phong_hoc (ten_phong, suc_chua, vi_tri, mo_ta) VALUES (?,?,?,?)";
        try {
            PreparedStatement statement = connectDB.getConnection().prepareStatement(sql);
            statement.setString(1, phongHoc.getTenPhong());
            statement.setInt(2, phongHoc.getSucChua());
            statement.setString(3, phongHoc.getViTri());
            statement.setString(4, phongHoc.getMoTa());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }

        return Optional.of(getAll().get(getAll().size() - 1));
    }

    @Override
    public Optional<PhongHoc> findById(Integer id) {
        PhongHoc phongHoc = new PhongHoc();
        String query = "SELECT * FROM phong_hoc WHERE id = " + id;
        try {
            PreparedStatement statement = connectDB.getConnection().prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                phongHoc.setPhongHocId(rs.getInt("id"));
                phongHoc.setTenPhong(rs.getString("ten_phong"));
                phongHoc.setSucChua(rs.getInt("suc_chua"));
                phongHoc.setViTri(rs.getString("vi_tri"));
                phongHoc.setMoTa(rs.getString("mo_ta"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (phongHoc.getPhongHocId() == null) {
            return Optional.empty();
        }
        return Optional.of(phongHoc);
    }

}
