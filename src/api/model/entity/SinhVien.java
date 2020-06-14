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
@AllArgsConstructor
@NoArgsConstructor
public class SinhVien extends NguoiDung {

    private Integer sinhVienId;

    private Integer tblNguoiDungId;

    private String maSV;

    private String khoa;

    private String nganh;

    private Double gpa;

    private Integer stcTichLuy;

    private String hocLuc;

    private String lop;

}
