package api.service.impl;

import api.model.dao.SinhVienDAO;
import api.model.dao.impl.SinhVienDAOImpl;
import api.model.entity.SinhVien;
import api.service.SinhVienService;
import common.exception.FPException;

import java.util.List;
import java.util.Optional;

public class SinhVienServiceImpl implements SinhVienService {

    private SinhVienDAO sinhVienDAO = new SinhVienDAOImpl();

    @Override
    public Optional<SinhVien> findById(Integer id) throws FPException {
        Optional<SinhVien> opt = sinhVienDAO.findById(id);
        if (!opt.isPresent()) throw new FPException.NotFoundEntityException();
        return opt;
    }

    @Override
    public Optional<SinhVien> update(Integer id, SinhVien sinhVien) throws FPException {
        Optional<SinhVien> opt = sinhVienDAO.findById(id);
        if (!opt.isPresent()) throw new FPException.NotFoundEntityException();

        sinhVien.setSinhVienId(id);
        return sinhVienDAO.save(sinhVien);
    }

    @Override
    public List<SinhVien> searchByCode(String code) {
        return sinhVienDAO.findByStudentCode(code);
    }

}
