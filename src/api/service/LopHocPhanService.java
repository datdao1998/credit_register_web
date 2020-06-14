package api.service;

import api.model.entity.LopHocPhan;
import common.exception.FPException;

import java.util.List;
import java.util.Optional;

public interface LopHocPhanService {

    Optional<LopHocPhan> findById(Integer id) throws  FPException;

}
