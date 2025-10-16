package org.zhsaen.admin.entity;

import lombok.Data;

import java.util.List;

@Data
public class EraExtension {
    // 插件基础信息
    private EraExtensionInfo info;
    // 插件处理器配置
    private List<EraHandlerGroup> handlers;
    // 插件服务配置
    private List<EraService> services;
}
