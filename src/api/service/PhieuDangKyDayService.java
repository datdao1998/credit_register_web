package api.service;

import api.model.entity.PhieuDangKyDay;
import common.exception.FPException;

import java.util.List;
import java.util.Optional;

public interface PhieuDangKyDayService {

    Optional<PhieuDangKyDay> findById(Integer id) throws  FPException;

    Optional<PhieuDangKyDay> update(Integer id, PhieuDangKyDay phieuDangKyDay) throws FPException;

    List<PhieuDangKyDay> searchBySubjectAndSemester(String subjectCode, Integer hocKy);

}
