package api.service;

import api.model.entity.PhongHoc;
import common.exception.FPException;

import java.util.List;
import java.util.Optional;

public interface PhongHocService {

    Optional<PhongHoc> findById(Integer id) throws  FPException;

    Optional<PhongHoc> search(String name);

}
