package org.wuyou.core.exception;

/**
 * @author origami
 * @date 2023/10/13 13:22
 */
public interface ErrorInfo {

    /**
     * 获取错误码
     *
     * @return {@code String}
     */
    String getErrorCode();

    /**
     * 获取错误消息
     *
     * @return {@code String}
     */
    String getErrorMsg();
}
