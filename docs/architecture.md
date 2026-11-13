# Architecture & collaboration

## Scope

`order-service` owns order creation and lookup. It is deliberately a small vertical slice, not a production distributed deployment. The boundaries (HTTP controller, application service, repository) make behavior easy to change and test without introducing a message broker or service discovery before they are needed.

## Request path

```text
Frontend / HTTP client -> OrderController -> OrderService -> OrderRepository -> H2
                                  |                                  |
                            validation DTO                     OrderEntity
```

The controller accepts a request DTO; persistence uses an entity. Validation is at the HTTP boundary. `ApiExceptionHandler` keeps errors consistent. Current status is `CREATED`.

## Frontend/backend collaboration

`api.md` is the shared contract. Frontend work can start from the examples and status/error definitions without waiting for implementation. For parallel changes, agree on the contract first; keep endpoint and JSON names stable, and update this document in the same change as any breaking behavior. When the API grows, generate OpenAPI from controller annotations and check in the generated spec only if it becomes the team's chosen contract source.

## AI-assisted iteration

Give the coding agent a bounded task with acceptance criteria (for example: “Add order cancellation; only CREATED orders may transition; return 409 otherwise”). Ask it to inspect relevant docs/code, implement the smallest slice, add focused tests, run `mvn test`, and summarize changed files and limitations. Review the diff and API contract before merging. `AGENTS.md` provides shared rules; `.claude/CLAUDE.md` points Claude Code to the same rules. The git hook is intentionally optional/local so it cannot block a developer unexpectedly; CI is the shared gate.

## Next production steps

Replace H2 with PostgreSQL and migrations, add authentication and idempotency for writes, define observability and timeouts, and introduce async messaging only when a cross-service workflow requires it.
