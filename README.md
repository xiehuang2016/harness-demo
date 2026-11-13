# Harness Demo

一个面向快速迭代的 Java 后端微服务工程样例。项目以“订单服务”演示从需求到验证的开发闭环，并用轻量级仓库约定让 Codex / Claude Code 能快速理解、修改和交付。

## 技术选型

- Java 8、Spring Boot 2.7、Maven
- Spring Web、Bean Validation、Actuator
- H2 内存数据库、Spring Data JPA
- JUnit 5、MockMvc

## 快速开始

需要 JDK 8+、Maven 3.6+。在仓库根目录执行：

```bash
mvn spring-boot:run
```

服务默认监听 `8080`。创建订单：

```bash
curl -X POST http://localhost:8080/api/orders \
  -H 'Content-Type: application/json' \
  -d '{"customerId":"cust-001","amount":19.90}'
```

查询订单：`GET http://localhost:8080/api/orders/{id}`。健康检查：`GET /actuator/health`。H2 控制台：`/h2-console`，JDBC URL `jdbc:h2:mem:harness`，用户名 `sa`，密码为空。

## 工程设计

这是一个可独立运行的微服务切片：HTTP API → 应用服务 → Repository → H2。按业务能力分包，DTO 与持久化实体隔离；统一错误响应，输入校验，订单状态转换集中在领域实体。H2 便于克隆即跑，后续可将 Repository 替换为 PostgreSQL，并将服务间调用抽为客户端/API 契约。

## Harness Engineering

目标不是堆叠提示词，而是让每次改动范围清楚、反馈快速、结果可复核。

- `AGENTS.md`：仓库级工作约定和完成标准。
- `.claude/`：Claude Code 自动读取的同一份项目约定，以及提交前轻量 hook。
- `docs/`：架构、API 和协作流程，作为前后端共同的接口事实来源。
- Maven 测试与 `scripts/verify.ps1`：快速反馈入口；CI 运行相同验证。

### 推荐迭代流程

1. 将需求拆成 API、行为、验收条件；先更新 `docs/api.md` 中的契约。
2. 后端按 Controller → Service → Repository 实现，并补对应测试。
3. 前端可直接依据请求/响应示例并行开发；契约变更先同步文档，再改实现。
4. 运行 `mvn test`（或 `./scripts/verify.ps1`），检查 diff，更新文档后提交。

本地环境要求 JDK 8+、Maven 3.6+；当前开发机若为 Maven 3.5，升级 Maven 后再构建。

## 仓库布局

```text
AGENTS.md                  # Codex/通用 agent 约定
.claude/                   # Claude Code 配置
docs/                      # 架构与 API 契约
src/main/java/             # Spring Boot 服务
src/test/java/             # API 集成测试
scripts/                   # 本地验证脚本
.github/workflows/ci.yml   # GitHub Actions
```
