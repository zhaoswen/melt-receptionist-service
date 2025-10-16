package org.zhsaen.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.zhsaen.admin.entity.AdminUser;

@Mapper
public interface AdminUserDao extends BaseMapper<AdminUser> {
    
    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 用户对象
     */
    @Select("SELECT * FROM admin_user WHERE username = #{username}")
    AdminUser selectByUsername(@Param("username") String username);
    
    /**
     * 插入用户（处理密码加密）
     * @param adminUser 用户对象
     * @return 插入影响的行数
     */
    @Insert("INSERT INTO admin_user(username, password, nickname, email, create_time, update_time) " +
            "VALUES(#{username}, #{password}, #{nickname}, #{email}, NOW(), NOW())")
    int insertAdminUser(AdminUser adminUser);
    
    /**
     * 更新用户信息
     * @param adminUser 用户对象
     * @return 更新影响的行数
     */
    @Update("UPDATE admin_user SET username=#{username}, nickname=#{nickname}, email=#{email}, update_time=NOW() WHERE id=#{id}")
    int updateAdminUser(AdminUser adminUser);
    
    /**
     * 更新用户密码
     * @param id 用户ID
     * @param password 加密后的密码
     * @return 更新影响的行数
     */
    @Update("UPDATE admin_user SET password=#{password}, update_time=NOW() WHERE id=#{id}")
    int updatePassword(@Param("id") Integer id, @Param("password") String password);
    
    /**
     * 更新用户状态
     * @param id 用户ID
     * @param status 用户状态
     * @return 更新影响的行数
     */
    @Update("UPDATE admin_user SET status=#{status}, update_time=NOW() WHERE id=#{id}")
    int updateUserStatus(@Param("id") Integer id, @Param("status") String status);
}