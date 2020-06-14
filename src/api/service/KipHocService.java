package api.service;

import api.model.entity.KipHoc;
import common.exception.FPException;

import java.util.Optional;

public interface KipHocService {

    Optional<KipHoc> findById(Integer id) throws  FPException;

    Optional<KipHoc> update(Integer id, KipHoc kipHoc) throws FPException;

}
