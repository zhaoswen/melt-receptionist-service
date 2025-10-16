package org.zhsaen.admin.exception;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import org.zhsaen.admin.entity.Response;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 全局异常拦截（拦截未登录）
    @ExceptionHandler(NotLoginException.class)
    public Response<String> handlerNotLoginException(NotLoginException nle) {
        // 打印堆栈，方便排查
        nle.printStackTrace(); 
        
        // 返回给前端的错误信息
        return Response.error("用户未登录或登录已过期，请重新登录", 401);
    }

    // 全局异常拦截（拦截角色权限不足）
    @ExceptionHandler(NotRoleException.class)
    public Response<String> handlerNotRoleException(NotRoleException nre) {
        nre.printStackTrace();
        return Response.error("用户角色权限不足", 403);
    }

    // 全局异常拦截（拦截权限不足）
    @ExceptionHandler(NotPermissionException.class)
    public Response<String> handlerNotPermissionException(NotPermissionException npe) {
        npe.printStackTrace();
        return Response.error("用户权限不足", 403);
    }

    // 全局异常拦截（拦截其他所有异常）
    @ExceptionHandler(Exception.class)
    public Response<String> handlerException(Exception e) {
        e.printStackTrace();
        return Response.error("服务器内部错误：" + e.getMessage(), 500);
    }
}