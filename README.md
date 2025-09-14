# Airport Information API

A production-grade Spring Boot application that provides airport information by fetching data from external aviation APIs. The application implements advanced patterns including circuit breakers, caching strategies, and request deduplication for high availability and performance.

## Features

- **Circuit Breaker Pattern**: Resilience4j integration for handling external API failures
- **Dual Caching Strategy**: Configurable local (Caffeine) or Redis caching
- **SingleFlight Pattern**: Prevents duplicate concurrent requests to external APIs
- **Request Deduplication**: Efficient handling of multiple identical requests
- **Docker Support**: Ready for containerized deployment
- **Production Ready**: Comprehensive error handling, logging, and monitoring

## Architecture

### Design Patterns Implemented

- **Adapter Pattern**: Pluggable cache implementations (Local/Redis)
- **Circuit Breaker Pattern**: Fault tolerance for external API calls
- **SingleFlight Pattern**: Request deduplication and performance optimization
- **Decorator Pattern**: Layered caching with fallback strategies
- **Strategy Pattern**: Configurable caching strategies

### Technology Stack

- **Java 21**
- **Spring Boot 3.5.5**
- **Resilience4j** - Circuit breaker and fault tolerance
- **Caffeine** - High-performance local caching
- **Redis** - Distributed caching
- **Docker & Docker Compose** - Containerization
- **Swagger/OpenAPI** - API documentation

## Quick Start

### Prerequisites

- Java 21+
- Maven 3.6+
- Docker & Docker Compose (for containerized deployment)

### Local Development

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd take-home
   ```

2. **Run with local cache**
   ```bash
   mvn spring-boot:run
   ```

3. **Access the application**
   - API: http://localhost:8080/api/v1/airports/{code}

### Docker Deployment

1. **Start with Docker Compose**
   ```bash
   docker-compose up --build
   ```

   This will start:
   - Application on port 8080
   - Redis on port 6379

2. **Test the API**
   ```bash
   curl http://localhost:8080/api/v1/airports/JFK
   ```

## API Documentation

### Endpoints

#### Get Airport Information
```http
GET /api/v1/airports/{code}
```

**Parameters:**
- `code` (path) - Airport code (e.g., JFK, LAX, LHR)

**Response:**
```json
[
  {
    "code": "JFK",
    "name": "John F. Kennedy International Airport",
    "city": "New York",
    "country": "United States"
  }
]
```

**Status Codes:**
- `200` - Success
- `400` - Invalid airport code
- `500` - Internal server error or external API failure

## Configuration

### Application Properties

#### External API Configuration
```properties
# External API URLs
airport.api.base-url=https://api.aviationapi.com/v1
airport.api.airports-endpoint=${airport.api.base-url}/airports
```

#### Cache Configuration
```properties
# Cache type: "local" or "redis"
airport.cache.type=local

# Local cache settings (Caffeine)
airport.cache.local.max-size=1000
airport.cache.local.expire-after-write=PT30M
airport.cache.local.expire-after-access=PT15M

# Redis cache settings
airport.cache.redis.expire-after-write=PT30M
```

#### Circuit Breaker Configuration
```properties
# Circuit breaker settings
resilience4j.circuitbreaker.instances.airport-api.failure-rate-threshold=50
resilience4j.circuitbreaker.instances.airport-api.minimum-number-of-calls=5
resilience4j.circuitbreaker.instances.airport-api.sliding-window-size=10
resilience4j.circuitbreaker.instances.airport-api.wait-duration-in-open-state=30s
resilience4j.circuitbreaker.instances.airport-api.permitted-number-of-calls-in-half-open-state=3

# Timeout configuration
resilience4j.timelimiter.instances.airport-api.timeout-duration=10s
```

### Environment-Specific Configuration

#### Local Development (application.properties)
- Uses local Caffeine cache
- Connects to external API directly

#### Docker/Production (application-docker.properties)
- Uses Redis for distributed caching
- Same circuit breaker settings
- Optimized logging levels


## Architecture Details

### Request Flow

1. **Client Request** → Controller
2. **Service Layer** → Input validation
3. **Cached Query** → Check cache (Local/Redis)
4. **Cache Miss** → Remote Query with SingleFlight
5. **Circuit Breaker** → External API call
6. **Response Caching** → Store in cache
7. **Response** → Return to client

### Caching Strategy

#### Local Cache (Caffeine)
- **Use Case**: Single instance deployments, development
- **Benefits**: Ultra-fast access, no network overhead
- **Configuration**: Max size, TTL, access-based expiration

#### Redis Cache
- **Use Case**: Multi-instance deployments, production
- **Benefits**: Distributed, persistent, scalable
- **Configuration**: TTL, JSON serialization

### Circuit Breaker States

- **CLOSED**: Normal operation, requests pass through
- **OPEN**: Circuit is open, requests fail fast with fallback
- **HALF_OPEN**: Testing recovery, limited requests allowed

### SingleFlight Pattern

Prevents duplicate concurrent requests:
- Multiple requests for same airport code → Single external API call
- Waiting requests receive the same response
- Reduces external API load and improves performance

## Monitoring and Observability

### Logging

The application provides comprehensive logging:

```properties
# Enable detailed logging
logging.level.com.sporty.take_home.airport=INFO
logging.level.io.github.resilience4j=INFO
```

### Circuit Breaker Events

Monitor circuit breaker state changes:
- State transitions (CLOSED → OPEN → HALF_OPEN)
- Failure rate exceeded warnings
- Call not permitted notifications

### Cache Metrics

Track cache performance:
- Cache hits/misses
- Cache size and evictions
- TTL effectiveness

## Testing

### Unit Tests
```bash
./mvnw test -Dtest="AirportControllerTest,AirportServiceTest"
```

## Production Deployment

### Docker Compose Production Setup

```yaml
version: '3.8'
services:
  app:
    build: .
    ports:
      - "8080:8080"
    environment:
      - SPRING_PROFILES_ACTIVE=docker
      - AIRPORT_CACHE_TYPE=redis
      - REDIS_HOST=redis
    depends_on:
      - redis
    restart: unless-stopped

  redis:
    image: redis:7-alpine
    ports:
      - "6379:6379"
    volumes:
      - redis_data:/data
    restart: unless-stopped

volumes:
  redis_data:
```

### Health Checks

Spring Boot Actuator endpoints (if enabled):
- `/actuator/health` - Application health
- `/actuator/metrics` - Application metrics
- `/actuator/circuitbreakers` - Circuit breaker status


### Debug Logging

Enable debug logging for troubleshooting:
```properties
logging.level.com.sporty.take_home.airport=DEBUG
logging.level.io.github.resilience4j=DEBUG
```


ps: docs generated with help of AI along with implementation of cache adapter. single flight implementation also reuse from other  existing generic lib codebase.