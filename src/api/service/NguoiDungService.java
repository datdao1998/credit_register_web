package api.service;

import api.model.entity.NguoiDung;
import common.exception.FPException;

import java.util.List;
import java.util.Optional;

public interface NguoiDungService {

    Optional<NguoiDung> create(NguoiDung nguoiDung) throws FPException;

    Optional<NguoiDung> findById(Integer id) throws  FPException;

    Optional<NguoiDung> update(Integer id, NguoiDung nguoiDung) throws FPException;

    List<NguoiDung> search(String tenDangNhap, String ten, String vaiTro);

}
