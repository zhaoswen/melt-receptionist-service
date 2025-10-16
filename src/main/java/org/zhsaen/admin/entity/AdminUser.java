package org.zhsaen.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("admin_user")
public class AdminUser {
    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String status; // 用户状态：active-启用，inactive-禁用
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}