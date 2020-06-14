package api.model.dao;

import api.model.entity.GiangVien;

import java.util.List;

public interface GiangVienDAO extends BaseDAO<GiangVien> {

    List<GiangVien> search(String khoa, String nganh, String hocVi, String boMon);

}

