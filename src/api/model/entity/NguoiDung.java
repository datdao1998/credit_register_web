/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author User
 */

@Data
@AllArgsConstructor
@NoArgsConstructor

public class NguoiDung  {

    private Integer nguoiDungId;

    private String tenDangNhap;

    private String matKhau;

    private String hoTen;

    private String ngaySinh;

    private String gioiTinh;

    private String diaChi;

    private String vaiTro;

}
