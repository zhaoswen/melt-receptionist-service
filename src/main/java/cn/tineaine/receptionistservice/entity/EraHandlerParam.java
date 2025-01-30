package cn.tineaine.receptionistservice.entity;

import lombok.Data;

@Data
public class EraHandlerParam {
    // @TableId(value = "id", type = IdType.AUTO)
    private String id;
    private String paramKey;
    private String paramName;
    private String paramDescription;
    private String paramType;
    private String paramDefaultValue;
    private String paramOptions;
    private boolean paramRequire;
    private String paramHandlerId;
}

