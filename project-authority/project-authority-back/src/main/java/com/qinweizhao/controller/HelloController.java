package com.qinweizhao.controller;

import com.qinweizhao.common.entity.CommonResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qinweizhao
 * @since 2021/9/26
 */
@RestController
public class HelloController {

    @GetMapping("/home")
    public CommonResponse hello() {
        return CommonResponse.success("hello world");
    }
}
