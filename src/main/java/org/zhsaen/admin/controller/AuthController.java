package org.zhsaen.admin.controller;

import cn.dev33.satoken.stp.StpUtil;
import org.zhsaen.admin.entity.Response;
import org.zhsaen.admin.entity.AdminUser;
import org.zhsaen.admin.service.AdminUserService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Slf4j
@RestController
@RequestMapping("/auth")
@Tag(name = "认证管理", description = "管理员认证相关的操作接口")
public class AuthController {
    
    @Autowired
    private AdminUserService adminUserService;
    
    /**
     * 用户登录接口
     * @param userLoginReq 登录请求参数
     * @return 登录结果
     */
    @PostMapping("/login")
    @Operation(summary = "用户登录", description = "用户登录接口")
    public Response<String> login(@RequestBody UserLoginReq userLoginReq) {
        if (userLoginReq.getUsername() == null || userLoginReq.getUsername().isEmpty()) {
            return Response.error("用户名不能为空", 400);
        }
        
        if (userLoginReq.getPassword() == null || userLoginReq.getPassword().isEmpty()) {
            return Response.error("密码不能为空", 400);
        }
        
        boolean loginResult = adminUserService.login(userLoginReq.getUsername(), userLoginReq.getPassword());
        if (loginResult) {
            return Response.ok(StpUtil.getTokenValue(), "登录成功");
        } else {
            return Response.error("用户名或密码错误", 401);
        }
    }
    
    /**
     * 用户登出接口
     * @return 登出结果
     */
    @PostMapping("/logout")
    @Operation(summary = "用户登出", description = "用户登出接口")
    public Response<String> logout() {
        adminUserService.logout();
        return Response.ok(null, "登出成功");
    }
    
    /**
     * 获取当前用户信息
     * @return 当前用户信息
     */
    @GetMapping("/info")
    @Operation(summary = "获取当前用户信息", description = "获取当前登录用户的信息")
    public Response<AdminUser> getCurrentUserInfo() {
        
        AdminUser user = adminUserService.getCurrentUser();
        if (user == null) {
            return Response.error("用户不存在", 404);
        }
        
        // 隐藏密码字段
        user.setPassword(null);
        return Response.ok(user);
    }
    
    /**
     * 登录请求参数类
     */
    @Data
    public static class UserLoginReq {
        private String username;
        private String password;
    }
}