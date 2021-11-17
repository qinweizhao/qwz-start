package com.qinweizhao.modules.sys.controller;

import com.qinweizhao.common.entity.R;

/**
 * @author qinweizhao
 * @since 2021/9/24
 */
public class BaseController {


    public R getR(boolean b) {
        return b ? R.success("成功") : R.failure("失败");
    }
}
