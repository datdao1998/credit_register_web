/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.model.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author User
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhieuDangKyHoc {

    private Integer phieuDangKyHocId;

    private SinhVien sinhVien;

    private PhieuDangKyDay phieuDangKyDay;

    private String thoiGianDangKy;

    private Double hocPhi;

    private String trangThai;

}
