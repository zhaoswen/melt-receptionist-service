package org.zhsaen.admin.controller;

import cn.dev33.satoken.stp.StpUtil;
import org.zhsaen.admin.entity.Response;
import org.zhsaen.admin.entity.AdminUser;
import org.zhsaen.admin.service.AdminUserService;
import org.zhsaen.admin.dao.AdminUserDao;
import org.zhsaen.admin.util.AESUtil;
import org.zhsaen.admin.util.PasswordUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

@Slf4j
@RestController
@RequestMapping("/user")
@Tag(name = "用户管理", description = "用户相关接口")
public class AdminUserController {

    @Autowired
    private AdminUserService adminUserService;
    
    @Autowired
    private AdminUserDao adminUserDao;

    @Data
    static class LoginRequest {
        private String username;
        private String password;
    }

    @Data
    static class LoginResponse {
        private String token;
        private AdminUser user;
    }
    
    @Data
    static class PasswordChangeRequest {
        private String oldPassword;
        private String newPassword;
    }

    /**
     * 创建用户
     * @param adminUser 用户信息
     * @return 创建的用户
     */
    @PostMapping
    @Operation(summary = "创建用户", description = "创建一个新的用户")
    public Response<AdminUser> createAdminUser(@RequestBody AdminUser adminUser) {
        // 检查用户名是否为空
        if (adminUser.getUsername() == null || adminUser.getUsername().isEmpty()) {
            return Response.error("用户名不能为空", 400);
        }
        
        // 检查用户名是否已存在
        AdminUser existingAdminUser = adminUserService.findByUsername(adminUser.getUsername());
        if (existingAdminUser != null) {
            return Response.error("用户名已存在", 400);
        }
        
        // 检查密码是否为空（现在密码已经是加密的）
        if (adminUser.getPassword() == null || adminUser.getPassword().isEmpty()) {
            return Response.error("密码不能为空", 400);
        }
        
        // 创建用户（密码已经在前端加密，后端直接存储）
        AdminUser createdAdminUser = adminUserService.createAdminUser(adminUser);
        // 隐藏密码
        createdAdminUser.setPassword(null);
        return Response.ok(createdAdminUser, "用户创建成功");
    }

    /**
     * 更新用户信息
     * @param adminUser 用户信息
     * @return 更新后的用户
     */
    @PutMapping
    @Operation(summary = "更新用户信息", description = "更新用户信息")
    public Response<AdminUser> updateAdminUser(@RequestBody AdminUser adminUser) {
        // 更新用户
        AdminUser updatedAdminUser = adminUserService.updateAdminUser(adminUser);
        if (updatedAdminUser != null) {
            // 隐藏密码
            updatedAdminUser.setPassword(null);
            return Response.ok(updatedAdminUser, "用户更新成功");
        } else {
            return Response.error("用户不存在", 404);
        }
    }

    /**
     * 根据ID获取用户信息
     * @param id 用户ID
     * @return 用户信息
     */
    @GetMapping("/detail/{id}")
    @Operation(summary = "获取用户信息", description = "根据用户ID获取用户信息")
    public Response<AdminUser> getAdminUserById(@Parameter(description = "用户ID") @PathVariable Integer id) {
        AdminUser adminUser = adminUserService.getAdminUserById(id);
        if (adminUser != null) {
            // 隐藏密码
            adminUser.setPassword(null);
            return Response.ok(adminUser);
        } else {
            return Response.error("用户不存在", 404);
        }
    }

    /**
     * 获取所有用户列表
     * @return 用户列表
     */
    @GetMapping
    @Operation(summary = "获取所有用户", description = "获取系统中所有用户的列表")
    public Response<List<AdminUser>> getAllAdminUsers() {
        List<AdminUser> adminUsers = adminUserService.getAllAdminUsers();
        // 隐藏所有用户的密码
        for (AdminUser adminUser : adminUsers) {
            adminUser.setPassword(null);
        }
        return Response.ok(adminUsers);
    }

    /**
     * 分页获取用户列表
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return 分页用户列表
     */
    @GetMapping("/page")
    @Operation(summary = "分页获取用户", description = "分页获取用户列表")
    public Response<IPage<AdminUser>> getAdminUserPage(
            @Parameter(description = "页码") @RequestParam(defaultValue = "1") Integer pageNum,
            @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Integer pageSize) {
        
        Page<AdminUser> page = new Page<>(pageNum, pageSize);
        IPage<AdminUser> adminUserPage = adminUserService.getAdminUserPage(page);
        // 隐藏所有用户的密码
        for (AdminUser adminUser : adminUserPage.getRecords()) {
            adminUser.setPassword(null);
        }
        return Response.ok(adminUserPage);
    }

    /**
     * 删除用户
     * @param id 用户ID
     * @return 删除结果
     */
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除用户", description = "根据用户ID删除用户")
    public Response<String> deleteAdminUser(@Parameter(description = "用户ID") @PathVariable Integer id) {        
        boolean result = adminUserService.deleteAdminUserById(id);
        if (result) {
            return Response.ok(null, "用户删除成功");
        } else {
            return Response.error("用户不存在", 404);
        }
    }
    
    /**
     * 更新用户状态
     * @param id 用户ID
     * @param status 用户状态
     * @return 更新结果
     */
    @PutMapping("/{id}/status")
    @Operation(summary = "更新用户状态", description = "根据用户ID更新用户状态")
    public Response<String> updateUserStatus(@Parameter(description = "用户ID") @PathVariable Integer id, @RequestParam String status) {
        // 验证状态值是否有效
        if (!"active".equals(status) && !"inactive".equals(status)) {
            return Response.error("无效的状态值，只支持active或inactive", 400);
        }
        
        boolean result = adminUserService.updateUserStatus(id, status);
        if (result) {
            return Response.ok(null, "用户状态更新成功");
        } else {
            return Response.error("用户不存在", 404);
        }
    }
    
    /**
     * 修改密码
     * @param passwordChangeRequest 密码修改请求
     * @return 修改结果
     */
    @PutMapping("/change-password")
    @Operation(summary = "修改密码", description = "修改当前登录用户的密码")
    public Response<String> changePassword(@RequestBody PasswordChangeRequest passwordChangeRequest) {
        try {
            // 获取当前登录用户ID
            Integer userId = Integer.parseInt(StpUtil.getLoginIdAsString());
            
            // 获取当前用户信息
            AdminUser adminUser = adminUserService.getAdminUserById(userId);
            if (adminUser == null) {
                return Response.error("用户不存在", 404);
            }
            
            // 验证旧密码
            String decryptedPassword = AESUtil.decrypt(adminUser.getPassword());
            // 输出 decryptedPassword
            log.info("解密后的密码: {}", decryptedPassword);
            if (!decryptedPassword.equals(passwordChangeRequest.getOldPassword())) {
                return Response.error("原密码错误", 400);
            }
            
            // 验证新密码长度
            if (passwordChangeRequest.getNewPassword().length() < 6) {
                return Response.error("新密码长度不能少于6个字符", 400);
            }
            
            // 加密新密码并更新
            String encryptedNewPassword = AESUtil.encrypt(passwordChangeRequest.getNewPassword());
            boolean result = adminUserDao.updatePassword(userId, encryptedNewPassword) > 0;
            
            if (result) {
                return Response.ok(null, "密码修改成功");
            } else {
                return Response.error("密码修改失败", 500);
            }
        } catch (Exception e) {
            log.error("修改密码失败: {}", e.getMessage());
            return Response.error("修改密码失败: " + e.getMessage(), 500);
        }
    }
}