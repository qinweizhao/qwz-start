package com.qinweizhao.filter;

import com.alibaba.fastjson.JSONObject;
import com.qinweizhao.common.entity.Constant;
import com.qinweizhao.common.exception.UserException;
import com.qinweizhao.enums.HttpMethod;
import com.qinweizhao.util.GuavaCacheUtils;
import com.qinweizhao.util.IoUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

/**
 * @author qinweizhao
 * @since 2021/9/25
 */
public class MyAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    /**
     * 日志记录
     */
    private final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * 登录 url
     */
    private static final String LOGIN_URL = "/login";

    /**
     * 认证管理器
     */
    private final AuthenticationManager authenticationManager;

    /**
     * 无参构造器
     */
    public MyAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        setFilterProcessesUrl(LOGIN_URL);
    }


    /**
     * 尝试认证
     *
     * @param request  request
     * @param response response
     * @return Authentication
     * @throws AuthenticationException e
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!HttpMethod.POST.matches(request.getMethod())) {
            throw new AuthenticationServiceException("不支持身份验证方法:" + request.getMethod());
        }
        JSONObject jsonObject = IoUtils.parseRequestToJsonObject(request);
        String captcha = jsonObject.getString(Constant.LOGIN_CODE);
        if (StringUtils.isEmpty(captcha)) {
            throw new UserException("请输入验证码");
        }
        boolean b = this.validateCaptcha(captcha);
        if (!b) {
            throw new UserException("验证码错误");
        }
        String username = jsonObject.getString(Constant.LOGIN_USER);
        username = username != null ? username : "";
        username = username.trim();
        String password = jsonObject.getString(Constant.LOGIN_PASS);
        password = password != null ? password : "";

        // 判断 账号 密码 验证码 是否为空
        log.info("当前登录账户：{}", jsonObject);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        return authenticationManager.authenticate(token);
    }


    /**
     * 登录成功
     *
     * @param request    request
     * @param response   response
     * @param chain      chain
     * @param authResult authResult
     * @throws IOException      e
     * @throws ServletException e
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        if (log.isDebugEnabled()) {
            log.debug("登录成功");
        }
        super.successfulAuthentication(request, response, chain, authResult);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
        if (log.isDebugEnabled()) {
            log.debug("登录失败");
        }
        log.info("登录失败");
    }

    /**
     * 校验验证码
     *
     * @param captcha 验证码
     */
    private boolean validateCaptcha(String captcha) {
        ConcurrentMap<String, String> stringStringConcurrentMap = GuavaCacheUtils.CACHE.asMap();
        Collection<String> values = stringStringConcurrentMap.values();
        boolean b = values.contains(captcha);
        if (b) {
            // 当验证通过后，及时删除当前验证码
            for (Map.Entry<String, String> entry : stringStringConcurrentMap.entrySet()) {
                if (String.valueOf(entry.getValue()).equals(captcha)) {
                    GuavaCacheUtils.CACHE.invalidate(entry.getKey());
                }
            }
        }
        return b;
    }
}
