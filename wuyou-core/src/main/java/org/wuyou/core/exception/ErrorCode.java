package org.wuyou.core.exception;

import lombok.AllArgsConstructor;

/**
 * 错误码
 *
 * @author origami
 * @date 2023/10/12 17:02
 */
@AllArgsConstructor
public enum ErrorCode implements ErrorInfo {

    SYSTEM_ERROR("-1", "系统错误"),
    USER_ERROR("A0001", "用户端错误"),
    USER_PARAM_ERROR("A0100", "用户请求参数有误"),
    USER_PERMISSION_ERROR("A0300", "用户权限异常"),
    SYSTEM_EXECUTE_ERROR("B0001", "系统执行错误"),
    THIRD_PARTY_ERROR("C0001", "调用第三方服务出错"),
    DATABASE_SERVICE_ERROR("C0300", " 数据库服务出错");

    private final String code;
    private final String msg;

    @Override
    public String getErrorCode() {
        return code;
    }

    @Override
    public String getErrorMsg() {
        return msg;
    }
}
