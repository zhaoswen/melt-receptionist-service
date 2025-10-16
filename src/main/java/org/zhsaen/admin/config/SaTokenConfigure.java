package org.zhsaen.admin.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {
    // 注册 Sa-Token 拦截器，打开注解式鉴权功能
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册 Sa-Token 拦截器，打开注解式鉴权功能 
        registry.addInterceptor(new SaInterceptor(handle -> {
                    // 指定一条 match 规则
                    // 对未登录的用户进行拦截
                    StpUtil.checkLogin();
                }))
                // 指定拦截的路径
                 .addPathPatterns("/api/**")
                // 排除不需要拦截的路径
                .excludePathPatterns("/api/auth/**");

    }
}