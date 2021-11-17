package com.qinweizhao.common.exception;

import com.qinweizhao.common.entity.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

/**
 * @author qinweizhao
 * @since 2021/9/27
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    /**
     * IO 异常
     *
     * @param e e
     * @return CommonResponse
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IOException.class)
    public R exception(IOException e) {
        log.error("全局异常===》IOException异常：", e);
        return R.failure(e.getMessage());
    }

    /**
     * 异常
     *
     * @param e e
     * @return CommonResponse
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public R exception(Exception e) {
        log.error("全局异常===》Exception异常：", e);
        return R.failure(e.getMessage());
    }
}
