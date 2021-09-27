package com.qinweizhao.config;

import com.qinweizhao.filter.MyAuthenticationFilter;
import com.qinweizhao.handler.MyAccessDeniedHandler;
import com.qinweizhao.handler.MyAuthenticationEntryPoint;
import com.qinweizhao.handler.MyLogoutSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

/**
 * @author qinweizhao
 * @since 2021/9/25
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserDetailsService sysUserDetailsService;

    @Resource
    private MyAccessDeniedHandler myAccessDeniedHandler;

    @Resource
    private MyLogoutSuccessHandler myLogoutSuccessHandler;

    @Resource
    private MyAuthenticationEntryPoint myAuthenticationEntryPoint;


    /**
     * 密码编码器
     *
     * @return BCryptPasswordEncoder
     */
    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * 认证
     *
     * @param auth auth
     * @throws Exception e
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(sysUserDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    /**
     * @param web web
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(URL_WHITELIST);
    }

    /**
     * 资源过滤
     */
    private static final String[] URL_WHITELIST = {
            "/css/**",
            "/js/**",
            "/index.html",
            "/img/**",
            "/fonts/**",
            "/favicon.ico",
            "/captcha"
    };

    /**
     * 关键配置
     *
     * @param http http
     * @throws Exception e
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 禁用 csrf
                .csrf().disable()

                // 禁用 cors
                .cors().disable()

                // 禁用 session
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                // 拦截规则
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()

                // 退出
                .and()
                .logout()
                .logoutSuccessHandler(myLogoutSuccessHandler)

                // 异常
                .and()
                .exceptionHandling()
                // 授权
                .accessDeniedHandler(myAccessDeniedHandler)
                // 认证
                .authenticationEntryPoint(myAuthenticationEntryPoint)

                // 自定义过滤器
                .and()
                .addFilter(new MyAuthenticationFilter(this.authenticationManager()));
    }

}