package api.service;

import api.model.entity.SinhVien;
import common.exception.FPException;

import java.util.List;
import java.util.Optional;

public interface SinhVienService {

    Optional<SinhVien> findById(Integer id) throws FPException;

    Optional<SinhVien> update(Integer id, SinhVien sinhVien) throws FPException;

    List<SinhVien> searchByCode(String code);
}
