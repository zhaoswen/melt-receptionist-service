package org.zhsaen.admin.entity;

import lombok.Data;

@Data
public class Response<T> {
    // 消息
    private String message;
    // 成功状态
    private Boolean success;
    // 状态码
    private Integer code;
    // 数据
    private T data;

    // 静态工厂方法创建成功的响应
    public static <T> Response<T> ok(T data) {
        return ok(data, "success");
    }

    // 静态工厂方法创建成功地响应，并允许自定义消息
    public static <T> Response<T> ok(T data, String message) {
        Response<T> response = new Response<>();
        response.setSuccess(true);
        response.setCode(200);
        response.setMessage(message);
        response.setData(data);
        return response;
    }

    // 静态工厂方法创建失败的响应
    public static <T> Response<T> error(String message, Integer code) {
        Response<T> response = new Response<>();
        response.setSuccess(false);
        response.setCode(code);
        response.setMessage(message);
        response.setData(null);
        return response;
    }
}