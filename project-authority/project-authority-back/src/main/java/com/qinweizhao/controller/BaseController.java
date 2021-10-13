package com.qinweizhao.controller;

import com.qinweizhao.common.entity.CommonResponse;

/**
 * @author qinweizhao
 * @since 2021/9/24
 */
public class BaseController {


    public CommonResponse getCommonResponse(boolean b){
        return b?CommonResponse.success("成功"):CommonResponse.failure("失败");
    }
}
