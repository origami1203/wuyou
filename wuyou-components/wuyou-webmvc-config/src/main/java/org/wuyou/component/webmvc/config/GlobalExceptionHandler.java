package org.wuyou.component.webmvc.config;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.wuyou.core.R;
import org.wuyou.core.exception.BizException;
import org.wuyou.core.exception.ErrorCode;
import org.wuyou.core.exception.base.BaseException;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler implements InitializingBean {

    @Value("${spring.profiles.active:prod}")
    private String profile;

    /**
     * 处理程序的未知异常
     *
     * @param e e
     * @return {@code R<Void>}
     */
    @ExceptionHandler(Throwable.class)
    public R<Void> exception(Throwable e) {
        logIfNecessary(e);
        return R.failed(ErrorCode.SYSTEM_ERROR);
    }

    /**
     * 用户输入的json错误
     *
     * @param e e
     * @return {@code R<Void>}
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public R<Void> jsonException(HttpMessageNotReadableException e) {
        logIfNecessary(e);
        return R.failed(ErrorCode.USER_PARAM_ERROR.getErrorCode(), "json错误");
    }

    /**
     * bean验证相关的异常
     *
     * @param e e
     * @return {@code R<Void>}
     */
    @ExceptionHandler({ConstraintViolationException.class, ServletRequestBindingException.class, BindException.class})
    public R<Void> validationException(Exception e) {
        R<Void> result = R.failed(ErrorCode.USER_PARAM_ERROR);

        if (e instanceof ConstraintViolationException ex) {
            return result.setMsg(ex.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(";")));
        }

        if (e instanceof BindException ex) {
            return result.setMsg(ex.getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(";")));
        }
        return result.setMsg(e.getMessage());
    }

    /**
     * 业务异常
     *
     * @param e e
     * @return {@code R<Void>}
     */
    @ExceptionHandler(BizException.class)
    public R<Void> bizException(BizException e) {
        logIfNecessary(e);
        return R.failed(e.getCode(), e.getMessage());
    }

    /**
     * 除业务异常外的其他自定义异常
     *
     * @param e E
     * @return {@code R<Void>}
     */
    @ExceptionHandler(BaseException.class)
    public R<Void> baseException(BaseException e) {
        logIfNecessary(e);
        return R.failed(e.getCode(), e.getMessage());
    }

    /**
     * 如有必要，记录日志
     *
     * @param e E
     */
    private void logIfNecessary(Throwable e) {
        log.error(e.getMessage());
        if (log.isDebugEnabled() && !"dev".equals(profile)) {
            log.debug("堆栈详情:[{}]", e.getMessage(), e);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.debug("全局异常处理已配置");
        log.debug("当前profile环境为[{}]", profile);
    }

}
