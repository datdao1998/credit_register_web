package api.model.dao;

import api.model.entity.LopHocPhan;

import java.util.List;

public interface LopHocPhanDAO extends BaseDAO<LopHocPhan> {

    List<LopHocPhan> getByPhieuDangKyDay(Integer phieuDangKyDayId);

}
