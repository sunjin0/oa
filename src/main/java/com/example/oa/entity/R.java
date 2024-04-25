package com.example.oa.entity;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class R {
    private String code;
    private String message;
    private Object data;

    public static R OK(Object result) {
        return new R("200", "操作成功", result);
    }

    public static R OK(String status, Object result) {
        return new R(status, "操作成功", result);
    }

    public static R Worm(String message) {
        return new R("200", "出错误了", message);
    }

    public static R Error(String error) {
        return new R("500", "系统错误", error);
    }
}
