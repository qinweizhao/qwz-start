package com.qinweizhao.generator;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

@SpringBootTest
class AuthorityApplicationTests {

    @Resource
    BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 获取密码
     */
    @Test
    void contextLoads() {
        String encode = bCryptPasswordEncoder.encode("123456");
        boolean matches = bCryptPasswordEncoder.matches("123456", "$2a$10$H48FBiVBYHX.4n9Afp73N.885w9gaQZxs4Zj50MkvsolEIyK5gnfC");
        System.out.println(matches);
        System.out.println("encode = " + encode);
    }

}
