---
name: implement-feature
description: Implement a bounded backend feature using the API contract, tests, and repository conventions.
---

# Implement a feature

1. Read `AGENTS.md`, `docs/architecture.md`, and `docs/api.md`.
2. Restate the requested behavior and acceptance criteria. If the contract is ambiguous, identify the smallest safe assumption.
3. Update `docs/api.md` first when HTTP behavior changes.
4. Implement the smallest vertical slice: request/response, service behavior, persistence as needed.
5. Add focused tests for success and relevant failure paths.
6. Run `mvn test`. If dependencies or environment prevent it, report the exact blocker; do not claim tests passed.
7. Review `git diff` for scope, secrets, compatibility, and doc/test consistency. Report files changed, verification, and remaining risks.
