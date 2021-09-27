package com.qinweizhao.generator;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class AuthorityApplicationTests {

    @Resource
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Resource
    DefaultKaptcha defaultKaptcha;

    /**
     * 获取密码
     */
    @Test
    void contextLoads() {
        String encode = bCryptPasswordEncoder.encode("123456");
        assertNull(encode);
        boolean matches = bCryptPasswordEncoder.matches("123456", "$2a$10$H48FBiVBYHX.4n9Afp73N.885w9gaQZxs4Zj50MkvsolEIyK5gnfC");
        System.out.println(matches);
        System.out.println("encode = " + encode);
    }

    @Test
    void getKaptcha() throws IOException {
        OutputStream os = new FileOutputStream("/ss.text");
        String text = defaultKaptcha.createText();
        BufferedImage image = defaultKaptcha.createImage(text);
        assertNull(image);
        assert false;
        ImageIO.write(image, "jpg", os);
        os.flush();
        System.out.println(image);

    }


    @Test
    void testRandom() {
        // 乱码（中文环境）
        System.out.println(RandomStringUtils.random(10));
    }

}
