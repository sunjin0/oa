package com.example.oa.entity;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class R{
    private String code;
    private String message;
    private Object data;

    public static String OK(Object result) {
        return JSON.toJSONString(new R("200", "操作成功", result));
    }

    public static String OK(String status, Object result) {
        return JSON.toJSONString(new R(status, "操作成功", result));
    }

    public static String Worm(String message) {
        return JSON.toJSONString(new R("200", "出错误了", message));
    }

    public static String Error(String error) {
        return JSON.toJSONString(new R("500", "系统错误", error));
    }
}
