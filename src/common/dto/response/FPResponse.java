package common.dto.response;

import lombok.Data;

import java.io.Serializable;

/**
 * Author : Nguyen Viet Anh
 * Email: anhnv@vnpay.vn
 */

@Data
public class FPResponse<R> implements Serializable {

    private static final long serialVersionUID = 6781743431223652297L;

    protected String code;

    protected String message;

    protected R data;
}
