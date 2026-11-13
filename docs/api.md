# Orders API v1

Base URL: `http://localhost:8080`. JSON uses UTF-8. Amount is a positive decimal with at most two fraction digits.

## Create order

`POST /api/orders` — body:

```json
{"customerId":"cust-001","amount":19.90}
```

Returns `201 Created` and the created resource:

```json
{"id":1,"customerId":"cust-001","amount":19.90,"status":"CREATED","createdAt":"2026-11-13T08:00:00Z"}
```

## Get order

`GET /api/orders/{id}` returns `200 OK` with the same resource shape.

## Errors

Errors use `{ "status": 400, "error": "Bad Request", "message": "Invalid request" }`. Invalid input returns 400; unknown IDs return 404. Health check is `GET /actuator/health`.
