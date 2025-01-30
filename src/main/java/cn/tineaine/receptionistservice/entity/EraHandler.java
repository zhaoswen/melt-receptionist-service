package cn.tineaine.receptionistservice.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.List;

@Data
public class EraHandler {
    // @TableId(value = "id", type = IdType.AUTO)
    private String id;
    // 每一个Handler的路径都是唯一的，一旦冲突引擎也会冲突
    // id就是handler路径，前端需要翻译为handler
    private String handler;
    private String name;
    private String description;
    @TableField(exist = false)
    private List<EraHandlerParam> params;
    @TableField(value = "group_id")
    private String groupId;
}
