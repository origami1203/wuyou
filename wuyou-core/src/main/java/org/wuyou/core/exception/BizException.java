package org.wuyou.core.exception;

import org.wuyou.core.exception.base.BaseException;

/**
 * 业务异常
 *
 * @author origami
 * @date 2023/10/12 8:29
 */
public class BizException extends BaseException {

    public BizException(String message) {
        super(message);
    }

}
