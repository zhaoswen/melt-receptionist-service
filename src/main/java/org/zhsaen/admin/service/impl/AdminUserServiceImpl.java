package org.zhsaen.admin.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import org.zhsaen.admin.dao.AdminUserDao;
import org.zhsaen.admin.entity.AdminUser;
import org.zhsaen.admin.service.AdminUserService;
import org.zhsaen.admin.util.PasswordUtil;
import org.zhsaen.admin.util.AESUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

@Slf4j
@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private AdminUserDao adminUserDao;

    @Override
    public AdminUser findByUsername(String username) {
        return adminUserDao.selectByUsername(username);
    }

    @Override
    public boolean login(String username, String password) {
        AdminUser adminUser = findByUsername(username);
        if (adminUser == null) {
            log.info("User not found: {}", username);
            return false;
        }

        // 检查用户状态，如果用户被禁用则不允许登录
        if (adminUser.getStatus() != null && "inactive".equals(adminUser.getStatus())) {
            log.info("User is disabled: {}", username);
            return false;
        }

        log.info("Found user: {}, password in DB: {}", username, adminUser.getPassword());

        try {
            // 尝试使用AES解密数据库中的密码进行验证
            String decryptedPassword = AESUtil.decrypt(adminUser.getPassword());
            System.out.println("Decrypted password: " + decryptedPassword);
            // if (password.equals(decryptedPassword)) {
            if (PasswordUtil.matches(decryptedPassword, password)) {
                // 登录成功，使用Sa-Token保存会话
                StpUtil.login(adminUser.getId());
                log.info("Login successful for user: {}", username);
                return true;
            } else {
                log.info("Password does not match for user: {}", username);
            }
        } catch (Exception e) {
            log.error("AES密码验证失败: {}", e.getMessage());
        }
        return false;
    }

    @Override
    public void logout() {
        StpUtil.logout();
    }

    @Override
    public AdminUser getCurrentUser() {
            Integer userId = Integer.parseInt(StpUtil.getLoginIdAsString());
            return adminUserDao.selectById(userId);
    }

    @Override
    public AdminUser createAdminUser(AdminUser adminUser) {
        // 使用AESUtil对密码进行加密
        if (adminUser.getPassword() != null && !adminUser.getPassword().isEmpty()) {
            String encryptedPassword = AESUtil.encrypt(adminUser.getPassword());
            adminUser.setPassword(encryptedPassword);
            log.info("密码已成功加密");
        }

        // 存储加密后的密码
        adminUserDao.insertAdminUser(adminUser);
        return adminUser;
    }

    @Override
    public boolean deleteAdminUserById(Integer id) {
        int result = adminUserDao.deleteById(id);
        return result > 0;
    }

    @Override
    public AdminUser updateAdminUser(AdminUser adminUser) {
        // 如果更新包含密码，使用AESUtil对密码进行加密
        if (adminUser.getPassword() != null && !adminUser.getPassword().isEmpty()) {
            String encryptedPassword = AESUtil.encrypt(adminUser.getPassword());
            adminUserDao.updatePassword(adminUser.getId(), encryptedPassword);
            log.info("用户密码已成功加密更新");
        }
        adminUserDao.updateAdminUser(adminUser);
        return adminUserDao.selectById(adminUser.getId());
    }
    
    @Override
    public boolean updateUserStatus(Integer id, String status) {
        int result = adminUserDao.updateUserStatus(id, status);
        return result > 0;
    }

    @Override
    public AdminUser getAdminUserById(Integer id) {
        return adminUserDao.selectById(id);
    }

    @Override
    public List<AdminUser> getAllAdminUsers() {
        return adminUserDao.selectList(null);
    }

    @Override
    public IPage<AdminUser> getAdminUserPage(Page<AdminUser> page) {
        return adminUserDao.selectPage(page, null);
    }
}