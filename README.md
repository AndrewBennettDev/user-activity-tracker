# User Activity Tracker

This is a Java Spring Boot project designed to simulate a lightweight data platform service, similar to what you might find at Twilio. It ingests user activity events (like `SMS_SENT`, `CALL_PLACED`, etc.), stores them in a relational database, and exposes a REST API for querying and analytics.

## Features

- `POST /events` — Accepts and stores user activity events
- `GET /events` — Filters events by userId, eventType, or timestamp range
- `GET /events/paged` — Supports pagination and sorting for large datasets
- Validates input with Jakarta Bean Validation
- Stores metadata as a dynamic key-value map
- Built with Spring Boot, JPA, and H2 (or PostgreSQL)

---

## Sample Event Payload

```json
{
  "userId": "abc123",
  "eventType": "SMS_SENT",
  "timestamp": "2025-06-25T14:00:00Z",
  "metadata": {
    "to": "+15555551234",
    "status": "delivered"
  }
}
