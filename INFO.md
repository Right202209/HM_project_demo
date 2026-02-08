This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Commands

### Build & Package
- Build project: `./mvnw clean install`
- Compile: `./mvnw compile`
- Package as JAR: `./mvnw package`

### Testing
- Run all tests: `./mvnw test`
- Run a single test: `./mvnw test -Dtest=TestClassName`
- Run specific test method: `./mvnw test -Dtest=TestClassName#methodName`

### Running
- Run Spring Boot application: `./mvnw spring-boot:run`
- Main Entry Point: `src/main/java/org/qqhru/hmpt/HmptApplication.java`

## Architecture

### Backend: Spring Boot + MyBatis
- **Controller**: `org.qqhru.hmpt.controller` - Handles REST API requests. Uses `@RequestMapping` and `@Slf4j`.
- **Service Layer**: `org.qqhru.hmpt.service` (interfaces) and `org.qqhru.hmpt.service.impl` (implementations).
- **Mapper Layer**: `org.qqhru.hmpt.mapper` - MyBatis mapper interfaces.
- **Mapper XML**: `src/main/resources/org/qqhru/hmpt/mapper/` - SQL implementations.
- **POJOs/Entities**: `org.qqhru.hmpt.pojo` - Maps to database tables (Dept, Emp).
- **DTOs/VOs**:
    - `org.qqhru.hmpt.dto`: `EmpDto` for data transfer.
    - `org.qqhru.hmpt.vo`: `ResultVo` for unified API responses, `PageResult` for pagination.
- **Security**: JWT-based authentication using `LoginInterceptor` and `JwtUtils`. Configured in `WebConfig`. Keys are in `application.yml`.
- **Exception Handling**: Global exception handler in `org.qqhru.hmpt.exception.MyException`.
- **File Storage**: Supports Aliyun OSS (`AliOSSUtils`) and MinIO (`MinIOUtils`). Configured in `application.yml`.

### Frontend
- Pre-built frontend assets are located in `nginx-1.22.0-tlias/html/`, managed via an Nginx server included in the repository.

### Database
- **MySQL**: Connection settings in `src/main/resources/application.yml`.
- **Druid**: Connection pool configuration.
- **PageHelper**: Used for database pagination.

### Key Configuration Files
- `pom.xml`: Maven dependencies (Spring Boot 3.4.0, MyBatis, JWT, OSS, MinIO, Lombok, Hutool).
- `src/main/resources/application.yml`: Application configuration (datasource, mybatis, minio).
