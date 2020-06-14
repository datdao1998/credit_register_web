package api.service.impl;

import api.model.dao.PhieuDangKyHocDAO;
import api.model.dao.SinhVienDAO;
import api.model.dao.impl.PhieuDangKyHocDAOImpl;
import api.model.dao.impl.SinhVienDAOImpl;
import api.model.entity.PhieuDangKyHoc;
import api.service.PhieuDangKyHocService;
import common.constant.FPConstant;
import common.exception.FPException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PhieuDangKyHocServiceImpl implements PhieuDangKyHocService {

    PhieuDangKyHocDAO phieuDangKyHocDAO = new PhieuDangKyHocDAOImpl();

    @Override
    public Optional<PhieuDangKyHoc> create(PhieuDangKyHoc phieuDangKyHoc) throws FPException {
        Optional<PhieuDangKyHoc> opt = phieuDangKyHocDAO.findById(phieuDangKyHoc.getPhieuDangKyHocId());
        if(opt.isPresent()) throw new FPException.DuplicateEntityException();
        phieuDangKyHoc.setTrangThai(FPConstant.FormStatus.LEARNING);
        phieuDangKyHoc.setPhieuDangKyHocId(null);
        return phieuDangKyHocDAO.save(phieuDangKyHoc);
    }

    @Override
    public Optional<PhieuDangKyHoc> findById(Integer id) throws FPException {
        Optional<PhieuDangKyHoc> opt = phieuDangKyHocDAO.findById(id);
        if (!opt.isPresent()) throw new FPException.NotFoundEntityException();
        return opt;
    }

    @Override
    public Optional<PhieuDangKyHoc> update(Integer id, PhieuDangKyHoc phieuDangKyHoc) throws FPException {
        Optional<PhieuDangKyHoc> opt = phieuDangKyHocDAO.findById(id);
        if (!opt.isPresent()) throw new FPException.NotFoundEntityException();

        System.out.println("service " + phieuDangKyHoc.getTrangThai());
        if (phieuDangKyHoc.getTrangThai()!=null && !phieuDangKyHoc.getTrangThai().isEmpty() && !phieuDangKyHoc.getTrangThai().equals(FPConstant.FormStatus.LEARNING) && !phieuDangKyHoc.getTrangThai().equals(FPConstant.FormStatus.LEARNED) && !phieuDangKyHoc.getTrangThai().equals(FPConstant.FormStatus.CANCELED)){
            throw new FPException.StatusInvalidException();
        }
        phieuDangKyHoc.setPhieuDangKyHocId(id);
        return phieuDangKyHocDAO.save(phieuDangKyHoc);
    }

    @Override
    public List<PhieuDangKyHoc> searchAvailableForm(String maSV, Integer hocKy, String trangThai) {
        SinhVienDAO sinhVienDAO = new SinhVienDAOImpl();
        List<PhieuDangKyHoc> phieuDangKyHocs = phieuDangKyHocDAO.getByStatus(trangThai);
        List<PhieuDangKyHoc> list = new ArrayList<>();
        for(PhieuDangKyHoc phieuDangKyHoc : phieuDangKyHocs){
            if(phieuDangKyHoc.getPhieuDangKyDay().getHocKy().equals(hocKy) && phieuDangKyHoc.getSinhVien().getMaSV().equals(maSV))
                list.add(phieuDangKyHoc);
        }
        return list;
    }
}
