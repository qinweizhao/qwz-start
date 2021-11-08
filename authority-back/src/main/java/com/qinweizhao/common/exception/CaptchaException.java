package com.qinweizhao.common.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author qinweizhao
 * @since 2021/9/27
 */
public class CaptchaException extends AuthenticationException {

    public CaptchaException(String message) {
        super(message);
    }

}

