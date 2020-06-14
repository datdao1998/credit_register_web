package api.model.dao.impl;

import api.model.dao.KipHocDAO;
import api.model.entity.KipHoc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class KipHocDAOImpl implements KipHocDAO {


    @Override
    public List<KipHoc> getAll() {
        String sql = "SELECT * FROM kip_hoc";
        ArrayList<KipHoc> kipHocs = new ArrayList<>();
        try {
            PreparedStatement ps = connectDB.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KipHoc kipHoc = new KipHoc();
                kipHoc.setKipHocId(rs.getInt("id"));
                kipHoc.setGioBatDau(rs.getInt("gio_bat_dau"));
                kipHoc.setGioKetThuc(rs.getInt("gio_ket_thuc"));
                kipHoc.setThu(rs.getInt("thu"));
                kipHocs.add(kipHoc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kipHocs;
    }

    @Override
    public Optional<KipHoc> save(KipHoc kipHoc) {
        if (kipHoc.getKipHocId()!=null){
            Optional<KipHoc> opt = findById(kipHoc.getKipHocId());
            if (opt.isPresent()){
                String sql = "UPDATE kip_hoc SET gio_bat_dau = ?, gio_ket_thuc = ?, thu = ? WHERE id = ?";
                try {
                    PreparedStatement statement = connectDB.getConnection().prepareStatement(sql);
                    statement.setInt(1, kipHoc.getGioBatDau());
                    statement.setInt(2, kipHoc.getGioKetThuc());
                    statement.setInt(3, kipHoc.getThu());
                    statement.setInt(4, kipHoc.getKipHocId());
                    statement.executeUpdate();
                    return findById(kipHoc.getKipHocId());
                } catch (Exception e) {
                    e.printStackTrace();
                    return Optional.empty();
                }
            }
        }

        String sql = "INSERT INTO kip_hoc (gio_bat_dau, gio_ket_thuc, thu) VALUES (?,?,?)";
        try {
            PreparedStatement statement = connectDB.getConnection().prepareStatement(sql);
            statement.setInt(1, kipHoc.getGioBatDau());
            statement.setInt(2, kipHoc.getGioKetThuc());
            statement.setInt(3, kipHoc.getThu());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }

        return Optional.of(getAll().get(getAll().size() - 1));
    }

    @Override
    public Optional<KipHoc> findById(Integer id) {
        KipHoc kipHoc = new KipHoc();
        String query = "SELECT * FROM kip_hoc WHERE id = " + id;
        try {
            PreparedStatement statement = connectDB.getConnection().prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                kipHoc.setKipHocId(rs.getInt("id"));
                kipHoc.setGioBatDau(rs.getInt("gio_bat_dau"));
                kipHoc.setGioKetThuc(rs.getInt("gio_ket_thuc"));
                kipHoc.setThu(rs.getInt("thu"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (kipHoc.getKipHocId() == null) {
            return Optional.empty();
        }
        return Optional.of(kipHoc);

    }
}
