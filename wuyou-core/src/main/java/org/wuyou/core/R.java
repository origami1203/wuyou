package org.wuyou.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.wuyou.core.exception.ErrorCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * 统一返回模型
 *
 * @author origami
 * @date 2023/10/12 16:57
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class R<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 3436193278972216239L;
    private static final String SUCCESS_CODE = "00000";
    private static final String SUCCESS_MSG = "操作成功";

    /**
     * 状态码
     */
    private String code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 数据
     */
    private T data;

    public static R<Void> ok() {
        return ok(null);
    }

    public static <T> R<T> ok(T data) {
        return new R<T>().setCode(SUCCESS_CODE).setMsg(SUCCESS_MSG).setData(data);
    }

    public static <T> R<T> failed(String msg) {
        return new R<>(ErrorCode.SYSTEM_ERROR.getErrorCode(), msg, null);
    }

    public static <T> R<T> failed(String code, String msg) {
        return new R<>(code, msg, null);
    }

    public static <T> R<T> failed(ErrorCode errorCodeEnum) {
        return new R<>(errorCodeEnum.getErrorCode(), errorCodeEnum.getErrorMsg(), null);
    }

}
