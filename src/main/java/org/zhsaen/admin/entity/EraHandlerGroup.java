package org.zhsaen.admin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.List;

// 处理器组，代表一系列处理器的集合，类似于分类
@Data
public class EraHandlerGroup {
    private String id;
    private String name;
    private String extensionId;
    @TableField(exist = false)
    private List<EraHandler> func;
}
