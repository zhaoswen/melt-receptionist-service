package org.zhsaen.admin.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @description: Era 服务描述配置文件
 * @author tineaine
 */
@Data
public class EraService {
    @TableId(value = "id", type = IdType.AUTO)
    Integer id;
    String name;
    String description;
}
