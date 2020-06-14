package api.service;

import api.model.entity.PhieuDangKyHoc;
import common.exception.FPException;

import java.util.List;
import java.util.Optional;

public interface PhieuDangKyHocService {

    Optional<PhieuDangKyHoc> create(PhieuDangKyHoc phieuDangKyHoc) throws FPException;

    Optional<PhieuDangKyHoc> findById(Integer id) throws  FPException;

    Optional<PhieuDangKyHoc> update(Integer id, PhieuDangKyHoc phieuDangKyHoc) throws FPException;

    List<PhieuDangKyHoc> searchAvailableForm(String maSV, Integer hocKy, String trangThai);

}
