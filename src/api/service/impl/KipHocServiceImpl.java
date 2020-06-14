package api.service.impl;

import api.model.dao.KipHocDAO;
import api.model.dao.impl.KipHocDAOImpl;
import api.model.entity.KipHoc;
import api.service.KipHocService;
import common.exception.FPException;

import java.util.Optional;

public class KipHocServiceImpl implements KipHocService {

    private KipHocDAO kipHocDAO = new KipHocDAOImpl();

    @Override
    public Optional<KipHoc> findById(Integer id) throws FPException {
        Optional<KipHoc> opt = kipHocDAO.findById(id);
        if (!opt.isPresent()) throw new FPException.NotFoundEntityException();
        return opt;
    }

    @Override
    public Optional<KipHoc> update(Integer id, KipHoc kipHoc) throws FPException {
        Optional<KipHoc> opt = kipHocDAO.findById(id);
        if (!opt.isPresent()) throw new FPException.NotFoundEntityException();

        kipHoc.setKipHocId(id);
        return kipHocDAO.save(kipHoc);
    }
}
