# Quantum Admin 后台服务项目

> Quantum Admin Service
>
> Open Source Licensed: GPL v3
>
> Receptionist 可以为 Quantum Studio 提供开发、调试符号配置，引擎版本管理
>
> 此项目基于Spring Boot 3 构建，前端项目请参考 [Quantum Admin](https://github.com/eyresimpson/receptionist-admin)

## 快速开始

### Docker

### Docker Compose

### 直接运行

**你需要在你的服务器上安装 JDK/JRE 21**

在当前项目的发行版中直接下载打包好的Jar文件，并按照下面的命令启动

#### 使用内置 H2 数据库

```shell
# 启动服务，指定为UTF8
java -Dfile.encoding=utf-8 -jar receptionist-service-0.1.0.jar
```

#### 使用 MySQL 数据库

如果你对性能有一定要求，或者习惯使用MySQL，可以使用MySQL数据库的方式

**你需要在你的服务器上部署MySQL，并手动导入sql文件夹中的sql **

在当前项目的发行版中直接下载打包好的Jar文件，并按照下面的命令启动

```shell
# 启动服务，指定为UTF8
java -Dfile.encoding=utf-8 -jar receptionist-service-0.1.0.jar
```

### 自行编译

Melt Receptionist 项目完全开源，你可以直接下载，并进行修改然后部署到你的服务器

## API文档

项目集成了Swagger UI，可以通过以下地址访问API文档：

- Swagger UI界面: http://localhost:8988/api/swagger-ui.html
- JSON格式API文档: http://localhost:8988/api/v3/api-docs

## 用户认证

本项目使用 Sa-Token 进行用户认证管理，提供了以下接口：

- `POST /api/auth/login` - 用户登录
- `POST /api/auth/logout` - 用户登出
- `GET /api/auth/info` - 获取当前用户信息

默认用户：
- 用户名：admin
- 密码：admin

## 用户管理

除了基本的认证功能外，系统还提供了完整的用户管理接口：

- `POST /api/user` - 创建用户
- `DELETE /api/user/{id}` - 删除用户
- `PUT /api/user` - 更新用户信息
- `GET /api/user/{id}` - 获取指定用户信息
- `GET /api/user` - 获取所有用户列表

注意：用户管理接口需要先进行登录认证，且不支持普通用户自我删除。