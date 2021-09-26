package com.qinweizhao.filter;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qinweizhao.entity.SysUserDetails;
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
     * 登录 url
     */
    private static final String LOGIN_URL = "/login";

    /**
     * 无参构造器
     */
    public MyAuthenticationFilter() {
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
        try (ServletInputStream inputStream = request.getInputStream()) {
            String s = IoUtil.read(inputStream).toString();
            JSONObject jsonObject = JSON.parseObject(s);
            String username = jsonObject.getString("username");
            String password = jsonObject.getString("password");
            System.out.println(jsonObject);
            SysUserDetails sysUserDetails = new SysUserDetails();
            return new UsernamePasswordAuthenticationToken(username, password, sysUserDetails.getAuthorities());
        } catch (IOException e) {
            System.out.println(e.getClass());
            e.printStackTrace();
        }
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
}
