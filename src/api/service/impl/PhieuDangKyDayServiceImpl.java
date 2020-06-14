package api.service.impl;

import api.model.dao.PhieuDangKyDayDAO;
import api.model.dao.impl.PhieuDangKyDayDAOImpl;
import api.model.entity.LopHocPhan;
import api.model.entity.PhieuDangKyDay;
import api.service.PhieuDangKyDayService;
import common.exception.FPException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PhieuDangKyDayServiceImpl implements PhieuDangKyDayService {

    private PhieuDangKyDayDAO phieuDangKyDayDAO = new PhieuDangKyDayDAOImpl();

    @Override
    public Optional<PhieuDangKyDay> findById(Integer id) throws FPException {
        Optional<PhieuDangKyDay> opt = phieuDangKyDayDAO.findById(id);
        if (!opt.isPresent()) throw new FPException.NotFoundEntityException();

        return opt;
    }

    @Override
    public Optional<PhieuDangKyDay> update(Integer id, PhieuDangKyDay phieuDangKyDay) throws FPException {
        Optional<PhieuDangKyDay> opt = phieuDangKyDayDAO.findById(id);
        if (!opt.isPresent()) throw new FPException.NotFoundEntityException();

        phieuDangKyDay.setPhieuDangKyDayId(id);
        return phieuDangKyDayDAO.save(phieuDangKyDay);
    }

    @Override
    public List<PhieuDangKyDay> searchBySubjectAndSemester(String subjectCode, Integer hocKy) {
        List<PhieuDangKyDay> phieuDangKyDays = phieuDangKyDayDAO.getAll();
        List<PhieuDangKyDay> list = new ArrayList<>();
        for(PhieuDangKyDay phieuDangKyDay:phieuDangKyDays){
            List<LopHocPhan> lopHocPhans = phieuDangKyDay.getListLopHocPhan();
            for(LopHocPhan lopHocPhan:lopHocPhans){
                if(phieuDangKyDay.getHocKy().equals(hocKy) && lopHocPhan.getMonHoc().getMaMon().equals(subjectCode)){
                    list.add(phieuDangKyDay);
                }
            }
        }
        List<PhieuDangKyDay> resultList = new ArrayList<>();
        resultList = list.stream().distinct().collect(Collectors.toList());
        return resultList;
    }
}
