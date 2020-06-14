package api.model.dao;

import api.model.entity.NguoiDung;
import api.model.entity.SinhVien;

import java.util.List;
import java.util.Optional;

public interface SinhVienDAO extends BaseDAO<SinhVien> {

    List<SinhVien> findByStudentCode(String code);

}
