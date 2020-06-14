package api.service.impl;

import api.model.dao.GiangVienDAO;
import api.model.dao.impl.GiangVienDAOImpl;
import api.model.entity.GiangVien;
import api.service.GiangVienService;
import common.exception.FPException;

import java.util.List;
import java.util.Optional;

public class GiangVienServiceImpl implements GiangVienService {


    private GiangVienDAO giangVienDAO = new GiangVienDAOImpl();

    @Override
    public Optional<GiangVien> findById(Integer id) throws FPException {
        Optional<GiangVien> opt = giangVienDAO.findById(id);
        if (!opt.isPresent()) throw new FPException.NotFoundEntityException();
        return opt;
    }

    @Override
    public Optional<GiangVien> update(Integer id, GiangVien giangVien) throws FPException {
        Optional<GiangVien> opt = giangVienDAO.findById(id);
        if (!opt.isPresent()) throw new FPException.NotFoundEntityException();

        giangVien.setGiangVienId(id);
        return giangVienDAO.save(giangVien);
    }

    @Override
    public List<GiangVien> search(String khoa, String nganh, String hocVi, String boMon) {
        return giangVienDAO.search(khoa, nganh, hocVi, boMon);
    }
}
