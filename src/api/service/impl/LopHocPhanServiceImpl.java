package api.service.impl;

import api.model.dao.LopHocPhanDAO;
import api.model.dao.PhieuDangKyHocDAO;
import api.model.dao.impl.LopHocPhanDAOImpl;
import api.model.dao.impl.PhieuDangKyHocDAOImpl;
import api.model.entity.LopHocPhan;
import api.model.entity.PhieuDangKyHoc;
import api.service.LopHocPhanService;
import common.constant.FPConstant;
import common.exception.FPException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LopHocPhanServiceImpl implements LopHocPhanService {

    private LopHocPhanDAO lopHocPhanDAO = new LopHocPhanDAOImpl();

    @Override
    public Optional<LopHocPhan> findById(Integer id) throws FPException {
        Optional<LopHocPhan> opt = lopHocPhanDAO.findById(id);
        if (!opt.isPresent()) throw new FPException.NotFoundEntityException();
        return opt;
    }


}
