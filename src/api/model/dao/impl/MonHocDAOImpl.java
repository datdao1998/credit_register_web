package api.model.dao.impl;

import api.model.dao.MonHocDAO;
import api.model.entity.MonHoc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MonHocDAOImpl implements MonHocDAO {


    @Override
    public List<MonHoc> findByName(String name) {
        ArrayList<MonHoc> monHocs = new ArrayList<>();
        String query = "SELECT * FROM mon_hoc WHERE ten_mon LIKE ? ";
        try {
            PreparedStatement statement = connectDB.getConnection().prepareStatement(query);
            statement.setString(1, "%" + name + "%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                MonHoc monHoc = new MonHoc();
                monHoc.setMonHocId(rs.getInt("id"));
                monHoc.setMaMon(rs.getString("ma_mon"));
                monHoc.setTenMon(rs.getString("ten_mon"));
                monHoc.setSoTinChi(rs.getInt("so_tin_chi"));
                monHoc.setSoTietLT(rs.getInt("so_tiet_LT"));
                monHoc.setSoTietBT(rs.getInt("so_tiet_BT"));
                monHoc.setSoTietTH(rs.getInt("so_tiet_TH"));
                monHoc.setHsChuyenCan(rs.getDouble("hs_chuyen_can"));
                monHoc.setHsKiemTra(rs.getDouble("hs_kiem_tra"));
                monHoc.setHsBaiTapLon(rs.getDouble("hs_btl"));
                monHoc.setHsThucHanh(rs.getDouble("hs_thuc_hanh"));
                monHoc.setHsCuoiKy(rs.getDouble("hs_cuoi_ki"));
                monHoc.setMonDK(rs.getString("mon_dk"));
                monHoc.setBoMon(rs.getString("bo_mon"));
                monHoc.setKhoa(rs.getString("khoa"));
                monHocs.add(monHoc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return monHocs;
    }

    @Override
    public Optional<MonHoc> findByCode(String code) {
        MonHoc monHoc = new MonHoc();
        String query = "SELECT * FROM mon_hoc WHERE ma_mon = ? ";
        try {
            PreparedStatement statement = connectDB.getConnection().prepareStatement(query);
            statement.setString(1, code);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                monHoc.setMonHocId(rs.getInt("id"));
                monHoc.setMaMon(rs.getString("ma_mon"));
                monHoc.setTenMon(rs.getString("ten_mon"));
                monHoc.setSoTinChi(rs.getInt("so_tin_chi"));
                monHoc.setSoTietLT(rs.getInt("so_tiet_LT"));
                monHoc.setSoTietBT(rs.getInt("so_tiet_BT"));
                monHoc.setSoTietTH(rs.getInt("so_tiet_TH"));
                monHoc.setHsChuyenCan(rs.getDouble("hs_chuyen_can"));
                monHoc.setHsKiemTra(rs.getDouble("hs_kiem_tra"));
                monHoc.setHsBaiTapLon(rs.getDouble("hs_btl"));
                monHoc.setHsThucHanh(rs.getDouble("hs_thuc_hanh"));
                monHoc.setHsCuoiKy(rs.getDouble("hs_cuoi_ki"));
                monHoc.setMonDK(rs.getString("mon_dk"));
                monHoc.setBoMon(rs.getString("bo_mon"));
                monHoc.setKhoa(rs.getString("khoa"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (monHoc.getMonHocId() == null) {
            return Optional.empty();
        }
        return Optional.of(monHoc);
    }


    @Override
    public List<MonHoc> getAll() {
        String sql = "SELECT * FROM mon_hoc";
        ArrayList<MonHoc> monHocs = new ArrayList<>();
        try {
            PreparedStatement ps = connectDB.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MonHoc monHoc = new MonHoc();
                monHoc.setMonHocId(rs.getInt("id"));
                monHoc.setMaMon(rs.getString("ma_mon"));
                monHoc.setTenMon(rs.getString("ten_mon"));
                monHoc.setSoTinChi(rs.getInt("so_tin_chi"));
                monHoc.setSoTietLT(rs.getInt("so_tiet_LT"));
                monHoc.setSoTietBT(rs.getInt("so_tiet_BT"));
                monHoc.setSoTietTH(rs.getInt("so_tiet_TH"));
                monHoc.setHsChuyenCan(rs.getDouble("hs_chuyen_can"));
                monHoc.setHsKiemTra(rs.getDouble("hs_kiem_tra"));
                monHoc.setHsBaiTapLon(rs.getDouble("hs_btl"));
                monHoc.setHsThucHanh(rs.getDouble("hs_thuc_hanh"));
                monHoc.setHsCuoiKy(rs.getDouble("hs_cuoi_ki"));
                monHoc.setMonDK(rs.getString("mon_dk"));
                monHoc.setBoMon(rs.getString("bo_mon"));
                monHoc.setKhoa(rs.getString("khoa"));
                monHocs.add(monHoc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return monHocs;
    }

    @Override
    public Optional<MonHoc> save(MonHoc monHoc) {
        if (monHoc.getMonHocId()!=null){
            Optional<MonHoc> opt = findById(monHoc.getMonHocId());
            if(opt.isPresent()){
                String sql = "UPDATE mon_hoc " +
                        "SET ma_mon = ?, ten_mon = ?,so_tin_chi = ?, so_tiet_LT = ?, so_tiet_BT = ?, so_tiet_TH = ?, hs_btl = ?, hs_cuoi_ki = ?, mon_dk = ?, khoa = ? "
                        + "WHERE id = ?";
                try {
                    PreparedStatement statement = connectDB.getConnection().prepareStatement(sql);
                    statement.setString(1, monHoc.getMaMon());
                    statement.setString(2, monHoc.getTenMon());
                    statement.setInt(3, monHoc.getSoTinChi());
                    statement.setInt(4, monHoc.getSoTietLT());
                    statement.setInt(5, monHoc.getSoTietBT());
                    statement.setInt(6, monHoc.getSoTietTH());
                    statement.setDouble(7, monHoc.getHsBaiTapLon());
                    statement.setDouble(8, monHoc.getHsCuoiKy());
                    statement.setString(9, monHoc.getMonDK());
                    statement.setString(10, monHoc.getKhoa());
                    statement.setInt(11, monHoc.getMonHocId());
                    statement.executeUpdate();
                    return findById(monHoc.getMonHocId());
                } catch (Exception e) {
                    e.printStackTrace();
                    return Optional.empty();
                }
            }
        }

        String sql = "INSERT INTO mon_hoc(ma_mon,ten_mon,so_tin_chi,so_tiet_LT,so_tiet_BT,so_tiet_TH,hs_chuyen_can,hs_kiem_tra,hs_thuc_hanh,hs_btl,hs_cuoi_ki,mon_dk,bo_mon,khoa)"
                + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement statement = connectDB.getConnection().prepareStatement(sql);
            statement.setString(1, monHoc.getMaMon());
            statement.setString(2, monHoc.getTenMon());
            statement.setInt(3, monHoc.getSoTinChi());
            statement.setInt(4, monHoc.getSoTietLT());
            statement.setInt(5, monHoc.getSoTietBT());
            statement.setInt(6, monHoc.getSoTietTH());
            statement.setDouble(7, monHoc.getHsChuyenCan());
            statement.setDouble(8, monHoc.getHsKiemTra());
            statement.setDouble(9, monHoc.getHsThucHanh());
            statement.setDouble(10, monHoc.getHsBaiTapLon());
            statement.setDouble(11, monHoc.getHsCuoiKy());
            statement.setString(12, monHoc.getMonDK());
            statement.setString(13, monHoc.getBoMon());
            statement.setString(14, monHoc.getKhoa());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }

        return Optional.of(getAll().get(getAll().size() - 1));

    }

    @Override
    public Optional<MonHoc> findById(Integer id) {
        MonHoc monHoc = new MonHoc();
        String query = "SELECT * FROM mon_hoc WHERE id = " + id;
        try {
            PreparedStatement statement = connectDB.getConnection().prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                monHoc.setMonHocId(rs.getInt("id"));
                monHoc.setMaMon(rs.getString("ma_mon"));
                monHoc.setTenMon(rs.getString("ten_mon"));
                monHoc.setSoTinChi(rs.getInt("so_tin_chi"));
                monHoc.setSoTietLT(rs.getInt("so_tiet_LT"));
                monHoc.setSoTietBT(rs.getInt("so_tiet_BT"));
                monHoc.setSoTietTH(rs.getInt("so_tiet_TH"));
                monHoc.setHsChuyenCan(rs.getDouble("hs_chuyen_can"));
                monHoc.setHsKiemTra(rs.getDouble("hs_kiem_tra"));
                monHoc.setHsBaiTapLon(rs.getDouble("hs_btl"));
                monHoc.setHsThucHanh(rs.getDouble("hs_thuc_hanh"));
                monHoc.setHsCuoiKy(rs.getDouble("hs_cuoi_ki"));
                monHoc.setMonDK(rs.getString("mon_dk"));
                monHoc.setBoMon(rs.getString("bo_mon"));
                monHoc.setKhoa(rs.getString("khoa"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (monHoc.getMonHocId() == null) {
            return Optional.empty();
        }
        return Optional.of(monHoc);
    }

}
