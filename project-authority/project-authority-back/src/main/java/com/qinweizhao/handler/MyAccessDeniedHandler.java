package com.qinweizhao.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author qinweizhao
 * @since 2021/9/25
 */
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) {
        String contextPath = httpServletRequest.getContextPath();
        log.info(contextPath);
    }
}