package api.service.impl;

import api.model.dao.LopHocPhanDAO;
import api.model.dao.MonHocDAO;
import api.model.dao.PhieuDangKyDayDAO;
import api.model.dao.impl.LopHocPhanDAOImpl;
import api.model.dao.impl.MonHocDAOImpl;
import api.model.dao.impl.PhieuDangKyDayDAOImpl;
import api.model.entity.LopHocPhan;
import api.model.entity.MonHoc;
import api.model.entity.PhieuDangKyDay;
import api.service.MonHocService;
import common.exception.FPException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MonHocServiceImpl implements MonHocService {

    private MonHocDAO monHocDAO = new MonHocDAOImpl();

    @Override
    public Optional<MonHoc> findById(Integer id) throws FPException {
        Optional<MonHoc> opt = monHocDAO.findById(id);
        if (!opt.isPresent()) throw new FPException.NotFoundEntityException();
        return opt;
    }

    @Override
    public List<MonHoc> searchByName(String name) {
        return monHocDAO.findByName(name);
    }

    @Override
    public Optional<MonHoc> searchByCode(String code) {
        return monHocDAO.findByCode(code);
    }

    @Override
    public List<MonHoc> searchByFacultyAndSemester(String khoa, Integer hocKy) {
        PhieuDangKyDayDAO phieuDangKyDayDAO = new PhieuDangKyDayDAOImpl();
        List<PhieuDangKyDay> phieuDangKyDays = phieuDangKyDayDAO.getAll();
        List<MonHoc> monHocs = new ArrayList<>();
        for(PhieuDangKyDay phieuDangKyDay : phieuDangKyDays){
            if(phieuDangKyDay.getHocKy().equals(hocKy) && phieuDangKyDay.getListLopHocPhan().get(0).getMonHoc().getBoMon().equals(khoa))
                 monHocs.add(phieuDangKyDay.getListLopHocPhan().get(0).getMonHoc());
        }
        List<MonHoc> list = monHocs.stream().distinct().collect(Collectors.toList());
        return list;
    }
}
