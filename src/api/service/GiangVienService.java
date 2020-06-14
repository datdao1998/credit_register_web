package api.service;

import api.model.entity.GiangVien;
import common.exception.FPException;

import java.util.List;
import java.util.Optional;

public interface GiangVienService {

    Optional<GiangVien> findById(Integer id) throws FPException;

    Optional<GiangVien> update(Integer id, GiangVien giangVien) throws FPException;

    List<GiangVien> search(String khoa, String nganh, String hocVi, String boMon);

}
