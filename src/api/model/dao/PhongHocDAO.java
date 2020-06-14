package api.model.dao;

import api.model.entity.PhongHoc;

import java.util.Optional;

public interface PhongHocDAO extends BaseDAO<PhongHoc> {

    Optional<PhongHoc> findByName(String name);

}
