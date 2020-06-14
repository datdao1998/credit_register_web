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
@NoArgsConstructor
@AllArgsConstructor
public class PhongHoc {

    private Integer phongHocId;

    private String tenPhong;

    private String viTri;

    private Integer sucChua;

    private String moTa;

}
