package com.qinweizhao.filter;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        //if (HttpMethod.POST.matches(request.getMethod())&&request.getContextPath().equals(MediaType.APPLICATION_JSON_UTF8_VALUE)){
        try (ServletInputStream inputStream = request.getInputStream()) {
            String s = IoUtil.read(inputStream).toString();
            JSONObject jsonObject = JSON.parseObject(s);
            String username = jsonObject.getString("username");
            String password = jsonObject.getString("password");
            log.info(String.valueOf(jsonObject));
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
            return authenticationManager.authenticate(token);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // }

        return null;
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
        System.out.println("成功");
        super.successfulAuthentication(request, response, chain, authResult);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        System.out.println(failed.getCause() + failed.getMessage());
        log.info("登录失败");
    }
}
