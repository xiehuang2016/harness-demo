# Agent Working Agreement

- Read `README.md` and relevant `docs/` before changing behavior.
- Keep changes small and scoped; preserve Java 8 compatibility.
- Treat `docs/api.md` as the frontend/backend contract. Update it when API behavior changes.
- Add or update focused tests for behavior changes; run `mvn test` before reporting completion.
- Do not add dependencies without a clear need. Never commit secrets or generated files.
- Summarize changes, verification, and known limits in the final response.
