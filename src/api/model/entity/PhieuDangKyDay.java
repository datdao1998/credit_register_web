/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.model.entity;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author User
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhieuDangKyDay {

    private Integer phieuDangKyDayId;

    private Integer siSo;

    private Integer soSinhVienDangKy;

    private String maLop;

    private Integer nhomLop;

    private List<LopHocPhan> listLopHocPhan;

    private Integer hocKy;

}
