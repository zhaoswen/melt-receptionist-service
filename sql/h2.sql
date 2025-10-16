-- H2 Database SQL Script

-- ----------------------------
-- Table structure for admin_user
-- ----------------------------
DROP TABLE IF EXISTS admin_user;
CREATE TABLE IF NOT EXISTS admin_user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '用户ID',
    username VARCHAR(50) NOT NULL COMMENT '用户名',
    password VARCHAR(100) NOT NULL COMMENT '密码',
    nickname VARCHAR(50) DEFAULT NULL COMMENT '昵称',
    email VARCHAR(100) DEFAULT NULL COMMENT '邮箱',
    status VARCHAR(20) DEFAULT 'active' NOT NULL COMMENT '用户状态：active-启用，inactive-禁用',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    UNIQUE(username)
);

-- ----------------------------
-- Records of admin_user
-- ----------------------------
INSERT INTO admin_user (id, username, password, nickname, email)
VALUES (1, 'root', '1uWhf6YqnBoPp9bHH27za26+QeEKmUs8Y/bsKW03ZkmMXKFAcgDA/ihECMLCeJojgqpe6Ijsb0HU3BDA4gpX0Q==', '管理员', 'admin@example.com')
ON DUPLICATE KEY UPDATE username=username;

-- ----------------------------
-- Table structure for era_extension_info
-- ----------------------------
DROP TABLE IF EXISTS era_extension_info;
CREATE TABLE era_extension_info (
    id VARCHAR(255) NOT NULL COMMENT '插件ID',
    name VARCHAR(255) NOT NULL COMMENT '插件名称',
    description TEXT COMMENT '插件描述',
    version VARCHAR(50) DEFAULT NULL COMMENT '插件版本',
    author VARCHAR(255) DEFAULT NULL COMMENT '插件作者',
    email VARCHAR(255) DEFAULT NULL COMMENT '作者邮箱',
    homepage VARCHAR(255) DEFAULT NULL COMMENT '插件主页',
    update_date TIMESTAMP DEFAULT NULL COMMENT '更新时间',
    license VARCHAR(255) DEFAULT NULL COMMENT '授权协议',
    PRIMARY KEY (id)
);

-- ----------------------------
-- Records of era_extension_info
-- ----------------------------
INSERT INTO era_extension_info (id, name, description, version, author, email, homepage, update_date, license)
VALUES ('simx', 'Simx 基础库', 'Simx 引擎默认附带的处理器，此内容请勿禁用与卸载，可能引起不可预知的问题', '0.1.0',
        'zhaosw', 'hi@tineaine.cn', 'http://simx.zhaosw.site', '2025-01-10 14:20:06', 'MIT');

-- ----------------------------
-- Table structure for era_handler
-- ----------------------------
DROP TABLE IF EXISTS era_handler;
CREATE TABLE era_handler (
    handler VARCHAR(255) NOT NULL COMMENT '处理器路径',
    name VARCHAR(255) DEFAULT NULL COMMENT '处理器名称',
    description TEXT COMMENT '处理器描述',
    group_id VARCHAR(255) NOT NULL COMMENT '处理器组ID',
    id VARCHAR(255) NOT NULL COMMENT 'ID',
    PRIMARY KEY (id)
);

-- ----------------------------
-- Records of era_handler
-- ----------------------------
INSERT INTO era_handler (handler, name, description, group_id, id)
VALUES ('simx.core.debug.print', '打印信息', '在控制台打印一段数据', 'simx.core', '1');
INSERT INTO era_handler (handler, name, description, group_id, id)
VALUES ('simx.core.var.set', '设置变量', '创建或修改流变量', 'simx.core', '2');
INSERT INTO era_handler (handler, name, description, group_id, id)
VALUES ('simx.core.debug.node', '节点调试', '调试节点的信息', 'simx.core', '3');
INSERT INTO era_handler (handler, name, description, group_id, id)
VALUES ('simx.core.var.remove', '移除变量', '移除流程中的某个变量', 'simx.core', '4');
INSERT INTO era_handler (handler, name, description, group_id, id)
VALUES ('simx.core.var.remove_all', '清空变量', '清空流中的所有变量', 'simx.core', '5');
INSERT INTO era_handler (handler, name, description, group_id, id)
VALUES ('simx.core.var.watch', '变量监听', '监听指定变量的变化', 'simx.core', '6');
INSERT INTO era_handler (handler, name, description, group_id, id)
VALUES ('simx.core.flow.sub_flow', '子流程', '调度指定的子流程', 'simx.core', '7');
INSERT INTO era_handler (handler, name, description, group_id, id)
VALUES ('simx.core.flow.post_flow', '流调用', '通过蓝图调用新的流', 'simx.core', '8');
INSERT INTO era_handler (handler, name, description, group_id, id)
VALUES ('simx.core.flow.timeout', '流等待', '让当前流的执行暂停一段时间', 'simx.core', 'm67n6i5l');

-- ----------------------------
-- Table structure for era_handler_group
-- ----------------------------
DROP TABLE IF EXISTS era_handler_group;
CREATE TABLE era_handler_group (
    id VARCHAR(255) NOT NULL COMMENT '处理器ID',
    name VARCHAR(255) DEFAULT NULL COMMENT '处理器名称',
    extension_id VARCHAR(255) NOT NULL COMMENT '关联插件id',
    PRIMARY KEY (id),
    INDEX (extension_id)
);

-- ----------------------------
-- Records of era_handler_group
-- ----------------------------
INSERT INTO era_handler_group (id, name, extension_id)
VALUES ('simx.core', 'simx 核心', 'simx');

-- ----------------------------
-- Table structure for era_handler_param
-- ----------------------------
DROP TABLE IF EXISTS era_handler_param;
CREATE TABLE era_handler_param (
    param_key VARCHAR(255) NOT NULL COMMENT '参数键名',
    param_name VARCHAR(255) NOT NULL COMMENT '参数显示名',
    param_description VARCHAR(255) DEFAULT NULL COMMENT '参数描述',
    param_type VARCHAR(255) NOT NULL COMMENT '参数类型',
    param_default_value VARCHAR(255) DEFAULT '' COMMENT '默认值',
    param_require BOOLEAN DEFAULT FALSE COMMENT '是否必需',
    id VARCHAR(255) NOT NULL COMMENT '参数ID',
    param_handler_id VARCHAR(255) NOT NULL COMMENT '关联处理器ID',
    param_options TEXT COMMENT '参数选项列表',
    PRIMARY KEY (id)
);

-- ----------------------------
-- Records of era_handler_param
-- ----------------------------
INSERT INTO era_handler_param (param_key, param_name, param_description, param_type, param_default_value,
                             param_require, id, param_handler_id, param_options)
VALUES ('val', '默认值', '变量的默认值', 'String', 'hello', FALSE, '0', '2', '[]');
INSERT INTO era_handler_param (param_key, param_name, param_description, param_type, param_default_value,
                             param_require, id, param_handler_id, param_options)
VALUES ('text', '文本', '要打印的文本内容，支持表达式', 'String', 'Hello World', TRUE, '1', '1', '[]');
INSERT INTO era_handler_param (param_key, param_name, param_description, param_type, param_default_value,
                             param_require, id, param_handler_id, param_options)
VALUES ('sec', '等待时间', '要等待的时间，单位秒', 'Number', '1', TRUE, '1881982575057678338', 'm67n6i5l', '[]');
INSERT INTO era_handler_param (param_key, param_name, param_description, param_type, param_default_value,
                             param_require, id, param_handler_id, param_options)
VALUES ('var_name', '参数名称', '要移除的参数名称', 'String', 'var', FALSE, '2', '4', '[]');
INSERT INTO era_handler_param (param_key, param_name, param_description, param_type, param_default_value,
                             param_require, id, param_handler_id, param_options)
VALUES ('level', '日志等级', '日志打印等级', 'Select', 'info', FALSE, '3', '1',
        '[{"label":"信息","value":"info"},{"label":"警告","value":"warn"},{"label":"错误","value":"fail"}]');
INSERT INTO era_handler_param (param_key, param_name, param_description, param_type, param_default_value,
                             param_require, id, param_handler_id, param_options)
VALUES ('key', '变量名', '变量的名称，不能重复', 'String', 'key', TRUE, '4', '2', '[]');
INSERT INTO era_handler_param (param_key, param_name, param_description, param_type, param_default_value,
                             param_require, id, param_handler_id, param_options)
VALUES ('steps', '步骤内容', '要发起的子流程的数据', 'String', '', TRUE, '5', '7', '[]');
INSERT INTO era_handler_param (param_key, param_name, param_description, param_type, param_default_value,
                             param_require, id, param_handler_id, param_options)
VALUES ('flow_path', '蓝图路径', '要发起的蓝图路径', 'String', '', TRUE, '6', '8', '[]');

-- ----------------------------
-- Table structure for era_service
-- ----------------------------
DROP TABLE IF EXISTS era_service;
CREATE TABLE era_service (
    id VARCHAR(255) NOT NULL,
    name VARCHAR(255) DEFAULT NULL,
    description TEXT,
    extension_id VARCHAR(255) NOT NULL COMMENT '关联插件id',
    PRIMARY KEY (id)
);