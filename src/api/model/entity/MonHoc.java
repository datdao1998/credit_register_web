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
public class MonHoc  {

    private Integer monHocId;

    private String maMon;

    private String tenMon;

    private Integer soTinChi;

    private Integer soTietLT;

    private Integer soTietBT;

    private Integer soTietTH;

    private Double hsChuyenCan;

    private Double hsKiemTra;

    private Double hsThucHanh;

    private Double hsBaiTapLon;

    private Double hsCuoiKy;

    private String monDK;

    private String boMon;

    private String khoa;

}
