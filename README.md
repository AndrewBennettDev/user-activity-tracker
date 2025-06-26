# User Activity Tracker

This Spring Boot microservice simulates a real-time event ingestion and analytics system, similar to what you'd find on a modern data platform like Twilio's.

It supports event ingestion via REST and Kafka, stores events in a relational database, and provides filterable APIs for retrieval. This project was created as a ramp-up for a backend software engineering role focused on distributed systems and real-time data processing.

## Features

- `POST /events`: Accepts user activity events via REST
- `POST /kafka/events`: Publishes events to a Kafka topic (`user-events`)
- Kafka consumer listens on `user-events` and persists messages
- Filter events by `userId`, `eventType`, and timestamp range
- Paginated and sortable responses via `/events/paged`
- In-memory H2 database for development
- Docker Compose setup for running Kafka locally

## Tech Stack

- Java 17
- Spring Boot 3.x
- Apache Kafka (via Spring Kafka)
- Spring Web + Spring Data JPA
- H2 (dev) with planned migration to PostgreSQL
- Gradle
- Docker + Docker Compose
- Lombok (optional)

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
```
## Running Locally

If you want to run this locally you can run a simple `docker compose up` from the main directory
and start a local kafka cluster with zookeeper. Once this is running you can either click the play
button in your favorite IDE or run `./gradlew bootRun`. If everything is running and healthy you
can try a curl command to hit the kafka events endpoint to check that everything is working:

```
curl -X POST http://localhost:8080/kafka/events \
  -H "Content-Type: application/json" \
  -d '{
    "userId": "externalService",
    "eventType": "SMS_DELIVERED",
    "timestamp": "2025-06-25T18:00:00Z",
    "metadata": {
      "region": "us-west",
      "status": "delivered"
    }
  }
```