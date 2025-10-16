package org.zhsaen.admin.service;

import org.zhsaen.admin.entity.AdminUser;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public interface AdminUserService {
    /**
     * 根据用户名查找用户
     * @param username 用户名
     * @return 用户对象
     */
    AdminUser findByUsername(String username);
    
    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return 登录结果
     */
    boolean login(String username, String password);
    
    /**
     * 用户登出
     */
    void logout();
    
    /**
     * 获取当前登录用户信息
     * @return 当前用户
     */
    AdminUser getCurrentUser();
    
    /**
     * 创建用户
     * @param adminUser 用户信息
     * @return 创建的用户
     */
    AdminUser createAdminUser(AdminUser adminUser);
    
    /**
     * 根据ID删除用户
     * @param id 用户ID
     * @return 是否删除成功
     */
    boolean deleteAdminUserById(Integer id);
    
    /**
     * 更新用户信息
     * @param adminUser 用户信息
     * @return 更新后的用户
     */
    AdminUser updateAdminUser(AdminUser adminUser);
    
    /**
     * 根据ID获取用户
     * @param id 用户ID
     * @return 用户信息
     */
    AdminUser getAdminUserById(Integer id);
    
    /**
     * 获取所有用户
     * @return 用户列表
     */
    List<AdminUser> getAllAdminUsers();
    
    /**
     * 分页获取用户列表
     * @param page 分页对象
     * @return 用户分页数据
     */
    IPage<AdminUser> getAdminUserPage(Page<AdminUser> page);
    
    /**
     * 更新用户状态
     * @param id 用户ID
     * @param status 用户状态
     * @return 是否更新成功
     */
    boolean updateUserStatus(Integer id, String status);
}