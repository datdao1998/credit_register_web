package api.service.impl;

import api.model.dao.PhongHocDAO;
import api.model.dao.impl.PhongHocDAOImpl;
import api.model.entity.PhongHoc;
import api.service.PhongHocService;
import common.exception.FPException;

import java.util.List;
import java.util.Optional;

public class PhongHocServiceImpl implements PhongHocService {

    private PhongHocDAO phongHocDAO = new PhongHocDAOImpl();

    @Override
    public Optional<PhongHoc> findById(Integer id) throws FPException {
        Optional<PhongHoc> opt = phongHocDAO.findById(id);
        if (!opt.isPresent()) throw new FPException.NotFoundEntityException();
        return opt;
    }


    @Override
    public Optional<PhongHoc> search(String name) {
        return phongHocDAO.findByName(name);
    }
}
