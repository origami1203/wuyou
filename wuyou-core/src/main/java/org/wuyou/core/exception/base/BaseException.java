package org.wuyou.core.exception.base;

import lombok.Getter;

/**
 * 异常基类
 *
 * @author origami
 * @date 2023/10/11 18:32
 */
@Getter
public class BaseException extends RuntimeException {
    private static final String DEFAULT_ERROR_CODE = "-1";

    private final String code;

    public BaseException(String message) {
        super(message);
        this.code = DEFAULT_ERROR_CODE;
    }

    public BaseException(String code, String message) {
        super(message);
        this.code = code;
    }

}
