package com.qinweizhao.common.enums;

import org.springframework.lang.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * 请求方式
 *
 * @author qinweizhao
 * @since 2021/9/25
 */
public enum HttpMethod {

    /**
     * http 请求类型
     */
    GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACE;

    /**
     * 存储容器
     */
    private static final Map<String, HttpMethod> MAPPINGS = new HashMap<>(16);


    static {
        for (HttpMethod httpMethod : values()) {
            MAPPINGS.put(httpMethod.name(), httpMethod);
        }
    }

    /**
     * @param method 请求类型
     * @return HttpMethod
     */
    @Nullable
    public static HttpMethod resolve(@Nullable String method) {
        return (method != null ? MAPPINGS.get(method) : null);
    }

    /**
     * @param method 请求类型
     * @return boolean
     */
    public boolean matches(String method) {
        return (this == resolve(method));
    }
}
