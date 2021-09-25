package com.qinweizhao.filter;

import com.qinweizhao.enums.HttpMethod;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author qinweizhao
 * @since 2021/9/25
 */
public class CaptchaFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) {
        if (HttpMethod.POST.matches(httpServletRequest.getMethod())) {
            // 校验
            validate(httpServletRequest);
        }
    }

    private void validate(HttpServletRequest httpServletRequest) {
        httpServletRequest.getParameter("code");
    }
}
