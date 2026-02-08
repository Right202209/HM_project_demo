# HMPT - 员工管理系统 (Human Management Platform)

这是一个基于 Spring Boot 3 + MyBatis 的员工管理系统后端项目。

## 技术栈

- **后端框架**: Spring Boot 3.4.0
- **持久层**: MyBatis 3.0.3
- **数据库**: MySQL 8.0
- **连接池**: Druid 1.2.23
- **安全认证**: JWT (JSON Web Token)
- **文件存储**: 阿里云 OSS / MinIO
- **工具类**: Lombok, Hutool, Fastjson
- **分页插件**: PageHelper

## 项目结构

```text
.
├── sql/            # 数据库初始化脚本
├── docker-compose.yml # Docker 编排配置
├── nginx-1.22.0-tlias/ # 前端服务器及静态资源
├── src/
│   └── main/
│       ├── java/org/qqhru/hmpt/
│       │   ├── config/         # 配置类
│       │   ├── controller/     # 控制器
│       │   ├── dto/            # DTO
│       │   ├── exception/      # 全局异常处理
│       │   ├── interceptor/    # 拦截器
│       │   ├── mapper/         # Mapper 接口
│       │   ├── pojo/           # 实体类
│       │   ├── service/        # 业务逻辑
│       │   ├── utils/          # 工具类
│       │   └── vo/             # VO
│       └── resources/
│           ├── mapper/         # MyBatis XML 映射
│           └── application.yml # 配置文件
└── pom.xml
```

## 快速开始

### 1. 数据库准备
执行 `sql/dept.sql` 初始化数据库。

### 2. 配置修改
修改 `src/main/resources/application.yml` 中的数据库连接信息、阿里云 OSS 或 MinIO 配置。

### 3. 启动项目
```bash
./mvnw spring-boot:run
```

## 优化记录 (Changelog)

### [2026-02-09] - 项目优化与重构

#### 优化内容
- **配置管理**:
    - 将硬编码在代码中的阿里云 OSS 密钥、JWT 密钥及过期时间迁移至 `application.yml`。
    - 支持通过环境变量覆盖敏感配置（如 `OSS_ACCESS_KEY_ID`）。
- **异常处理**:
    - 重构 `MyException` 为全局异常处理器，增加对 `NullPointerException`、`DuplicateKeyException` 等特定异常的处理。
    - 统一异常返回格式，提升系统健壮性。
- **代码规范**:
    - 引入 `@Slf4j` 取代 `System.out` 和 `System.err`，规范日志输出。
    - 移除控制器中冗余的 `try-catch` 块，交由全局异常处理器统一处理。
    - 使用 `@RequestMapping` 简化控制器路径配置。
- **性能优化**:
    - 完善 Druid 连接池配置（初始连接数、最大活跃数、等待时间等）。
    - 优化 `EmpController` 中的批量删除逻辑，使用 Java 8 Stream 简化代码。
- **安全性**:
    - 规范化 `LoginInterceptor` 的令牌校验逻辑。
- **清理**:
    - 移除代码和配置文件中大量的注释块及过期代码。

#### 修复
- 修复了 `WebConfig` 中硬编码的本地磁盘映射路径（`D:/upload/`），避免跨平台运行问题。
