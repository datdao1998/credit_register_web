package api.service;

import api.model.entity.MonHoc;
import common.exception.FPException;

import java.util.List;
import java.util.Optional;

public interface MonHocService {

    Optional<MonHoc> findById(Integer id) throws  FPException;

    List<MonHoc> searchByName(String name);

    Optional<MonHoc> searchByCode(String code);

    List<MonHoc> searchByFacultyAndSemester(String khoa, Integer hocKy);

}
