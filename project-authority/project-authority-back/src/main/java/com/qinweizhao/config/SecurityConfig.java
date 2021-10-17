package com.qinweizhao.config;

import com.qinweizhao.filter.JwtAuthenticationFilter;
import com.qinweizhao.filter.MyAuthenticationFilter;
import com.qinweizhao.handler.MyAccessDeniedHandler;
import com.qinweizhao.handler.MyAuthenticationEntryPoint;
import com.qinweizhao.handler.MyLogoutSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;
import java.util.Collections;

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

    @Resource
    private JwtAuthenticationFilter jwtAuthenticationFilter;


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
     * 认证管理器
     *
     * @return AuthenticationManager
     */
    @Override
    public AuthenticationManager authenticationManagerBean() {
        return new ProviderManager(Collections.singletonList(daoAuthenticationProvider()));
    }

    /**
     * 认证提供者
     *
     * @return AuthenticationProvider
     */
    private AuthenticationProvider daoAuthenticationProvider() {
        return new DaoAuthenticationProvider();
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
            "/sys/user/captcha"
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

                // 开启对 cors 的支持
                .cors()

                // 禁用 session
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                // 退出
                .and()
                .logout()
                .logoutSuccessHandler(myLogoutSuccessHandler)
                .permitAll()

                // 拦截规则
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS)
                .permitAll()
                .anyRequest()
                .authenticated()

                // 异常
                .and()
                .exceptionHandling()
                // 授权
                .accessDeniedHandler(myAccessDeniedHandler)
                // 认证
                .authenticationEntryPoint(myAuthenticationEntryPoint)

                // 自定义过滤器
                .and()
                .addFilter(new MyAuthenticationFilter(this.authenticationManager()))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
