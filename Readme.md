# Receptionist 后台服务项目

> Melt Receptionist
>
> Open Source Licensed: GPL v3
>
> Receptionist 可以为 Simx Engine、Simx Era Design、Brilliance IDE 等应用通过API的形式提供扩展配置更新、在线修复与安装
>
> 此项目基于Spring Boot 3 构建，前端项目请参考 [Receptionist Admin](https://github.com/eyresimpson/receptionist-admin)

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