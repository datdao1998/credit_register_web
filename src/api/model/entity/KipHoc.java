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
public class KipHoc {

    private Integer kipHocId;

    private Integer gioBatDau;

    private Integer gioKetThuc;

    private Integer thu;

}
