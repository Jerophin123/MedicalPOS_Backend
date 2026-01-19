# Medical Store POS - Backend

Production-ready Spring Boot backend for Medical Store POS system.

## Prerequisites

- Java 17 or higher
- Maven 3.6+
- PostgreSQL database

## Local Development

1. Copy `.env.example` to `.env`:
```bash
cp .env.example .env
```

2. Update `.env` file with your database credentials and configuration

3. Install dependencies:
```bash
mvn clean install
```

4. Run the application:
```bash
# Spring Boot automatically reads environment variables from the system
# For local development, you can:

# Option 1: Set environment variables manually in your IDE or system

# Option 2: Use a .env loader (Linux/Mac):
export $(cat .env | grep -v '^#' | xargs) && mvn spring-boot:run

# Option 3: Use dotenv-cli (cross-platform):
npm install -g dotenv-cli
dotenv -e .env mvn spring-boot:run

# Option 4: Use IntelliJ IDEA - it automatically loads .env files
# Option 5: Use VS Code with the "DotENV" extension
```

**Note:** Spring Boot natively supports reading from environment variables. Deployment platforms (Railway, Render, etc.) set environment variables directly, which Spring Boot will read automatically.

## Deployment

### Railway

1. Connect your GitHub repository
2. Railway will auto-detect the Java project
3. Set environment variables:
   - `DATABASE_URL`
   - `DATABASE_USERNAME`
   - `DATABASE_PASSWORD`
   - `JWT_SECRET`
   - `PORT` (default: 8080)

### Render

1. Create a new Web Service
2. Connect your repository
3. Build command: `mvn clean package -DskipTests`
4. Start command: `java -jar target/pos-backend-*.jar`
5. Set environment variables (see render.yaml)

### Docker

```bash
docker build -t pos-backend .
docker run -p 8080:8080 pos-backend
```

## Environment Variables

All environment variables can be set in the `.env` file or as system environment variables.

### Required Variables
- `DATABASE_URL` - PostgreSQL connection URL
- `DATABASE_USERNAME` - Database username
- `DATABASE_PASSWORD` - Database password
- `JWT_SECRET` - Secret key for JWT token generation

### Optional Variables
- `PORT` - Server port (default: 8080)
- `DATABASE_POOL_SIZE` - Connection pool size (default: 20)
- `DATABASE_MIN_IDLE` - Minimum idle connections (default: 5)
- `DATABASE_CONNECTION_TIMEOUT` - Connection timeout in ms (default: 30000)
- `JWT_EXPIRATION` - JWT token expiration in milliseconds (default: 86400000)
- `DDL_AUTO` - Hibernate DDL mode (default: validate, use `update` for development)
- `SHOW_SQL` - Show SQL queries (default: false)
- `SPRING_PROFILES_ACTIVE` - Active profile (default: production)
- `CORS_ALLOWED_ORIGINS` - CORS allowed origins (use `0.0.0.0` or `*` for all, or comma-separated list)
- `LOG_LEVEL` - Logging level (default: INFO)
- `HIBERNATE_SQL_LOG` - Enable Hibernate SQL logging (default: false)

## API Documentation

Once deployed, access Swagger UI at: `http://your-domain/api/swagger-ui.html`

