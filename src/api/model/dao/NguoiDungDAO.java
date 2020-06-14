package api.model.dao;

import api.model.entity.NguoiDung;

import java.util.List;
import java.util.Optional;

public interface NguoiDungDAO extends BaseDAO<NguoiDung> {

    Optional<NguoiDung> findByUserName(String userName);

    List<NguoiDung> search(String userName, String name, String vaiTro);

}
