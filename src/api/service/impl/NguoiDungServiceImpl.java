package api.service.impl;

import api.model.dao.NguoiDungDAO;
import api.model.dao.impl.NguoiDungDAOImpl;
import api.model.entity.NguoiDung;
import api.service.NguoiDungService;
import common.exception.FPException;

import java.util.List;
import java.util.Optional;

public class NguoiDungServiceImpl implements NguoiDungService {


    private NguoiDungDAO nguoiDungDAO = new NguoiDungDAOImpl();

    @Override
    public Optional<NguoiDung> create(NguoiDung nguoiDung) throws FPException {
        Optional<NguoiDung> opt = nguoiDungDAO.findByUserName(nguoiDung.getTenDangNhap());
        if(opt.isPresent()) throw new FPException.DuplicateEntityException();
        return nguoiDungDAO.save(nguoiDung);
    }

    @Override
    public Optional<NguoiDung> findById(Integer id) throws FPException {
        Optional<NguoiDung> opt = nguoiDungDAO.findById(id);
        if (!opt.isPresent()) throw new FPException.NotFoundEntityException();

        return opt;
    }

    @Override
    public Optional<NguoiDung> update(Integer id, NguoiDung nguoiDung) throws FPException {
        Optional<NguoiDung> opt = nguoiDungDAO.findById(id);
        if (!opt.isPresent()) throw new FPException.NotFoundEntityException();

        nguoiDung.setNguoiDungId(id);
        return nguoiDungDAO.save(nguoiDung);
    }

    @Override
    public List<NguoiDung> search(String tenDangNhap, String ten, String vaiTro) {
        return nguoiDungDAO.search(tenDangNhap,ten,vaiTro);
    }
}
