package org.zhsaen.admin.entity;

import lombok.Data;

import java.util.Date;

@Data
public class EraExtensionInfo {
    private String id;
    private String name;
    private String description;
    private String version;
    private String author;
    private String email;
    private String homepage;
    private Date updateDate;
    private String license;
}
