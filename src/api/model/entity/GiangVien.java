
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
public class GiangVien extends NguoiDung {

    private Integer giangVienId;

    private Integer tblNguoiDungId;

    private String tenGiangVien;

    private String khoa;

    private String nganh;

    private String hocVi;

    private String boMon;

}
