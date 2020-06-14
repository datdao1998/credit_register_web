package api.model.dao;

import api.model.entity.PhieuDangKyHoc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface PhieuDangKyHocDAO extends BaseDAO<PhieuDangKyHoc> {

    List<PhieuDangKyHoc> getByStatus(String trangThai);

}
