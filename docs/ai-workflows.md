# AI workflows

这些是可复制到 Codex / Claude Code 的短任务模板。把具体需求替换进去即可。

## 新功能（推荐使用 `.agents/skills/implement-feature/SKILL.md`）

> 实现 <业务能力>。验收条件：<可观察行为列表>。先检查仓库约定和 API 契约；契约有变化时先更新 `docs/api.md`。补充成功与失败测试，运行 `mvn test`，最后总结改动、验证结果和未覆盖风险。不要扩展到需求以外的重构。

## 前后端并行协作

> 先为 <功能> 定义请求、响应、状态码和错误结构，更新 `docs/api.md` 并给出可复制的 JSON 示例。暂不改变实现代码。列出前端可先行开发的部分及待确认项。

> 基于 `docs/api.md` 中 <端点> 实现后端。确保状态码、字段和错误响应与契约一致，增加 MockMvc 测试并运行 `mvn test`。

## Bug 修复

> 复现 <问题>，先写一个失败测试锁定现象，再做最小修复。运行相关测试和 `mvn test`，说明根因以及为何修复覆盖该场景。

## 评审

> 只审查当前 diff，不直接改代码。按严重程度列出正确性、数据一致性、API 兼容性、安全和测试缺口；每条指出文件/行与具体触发条件。没有发现问题时说明检查范围。

## 工具边界

`.claude/settings.local.example.json` 展示可选本地 hook；将其复制为 `.claude/settings.local.json` 后才生效。该 hook 只调用 Maven 的 validate 阶段，不做网络调用或改写文件。团队共享质量门禁由 GitHub Actions 的 `mvn test` 承担。
