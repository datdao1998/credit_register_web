package api.model.dao;

import api.model.entity.MonHoc;

import java.util.List;
import java.util.Optional;

public interface MonHocDAO extends BaseDAO<MonHoc> {

    List<MonHoc> findByName(String name);

    Optional<MonHoc> findByCode(String code);

}
