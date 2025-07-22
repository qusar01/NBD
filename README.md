# NBD Microservice

Backend Java microservice for managing parents, children, babysitters, and rental assignments.

## Features

- **Data Storage**  
  - Primary persistence in MongoDB with custom BSON codecs and unique‐ID generation.  
  - Secondary cache layer in Redis for low‐latency reads via the Repository Cache decorator.

- **Event Streaming**  
  - Apache Kafka integration with multi‐node cluster setup (3 brokers).  
  - Custom producers (e.g. `KafkaChildProducer`) to publish domain events.

- **Object Mapping & API**  
  - Entity–JSON binding via JSON‑B (Yasson) and Lombok for DTOs and models.  
  - Managers (`ParentManager`, `ChildManager`, `BabysitterManager`, `RentManager`) implement business logic.

- **Testing**  
  - Unit tests with JUnit 5.  
  - Integration tests against running Redis and Kafka instances (ensure containers are up).

- **Containerized Environment**  
  - Docker Compose orchestration for MongoDB, Redis, Kafka (with Zookeeper quorum), and Schema Registry.  
  - Custom `Dockerfile` and overrides for MongoDB and Kafka configuration.

## Tech Stack

Java • MongoDB • Redis • Apache Kafka • JSON‑B • Lombok • Maven • Docker • Docker Compose • JUnit
