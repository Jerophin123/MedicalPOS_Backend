# Backend Deployment Guide

This folder contains the deployment-ready backend code for the Medical Store POS system.

## Prerequisites

- Java 17 or higher
- Maven 3.6+ (or use included Maven wrapper)
- PostgreSQL database

## Quick Start

### Local Development

1. Copy `.env.example` to `.env` and fill in your values:
   ```bash
   cp .env.example .env
   ```

2. Update `.env` with your database credentials:
   ```
   DATABASE_URL=jdbc:postgresql://localhost:5432/medical_store_pos
   DATABASE_USERNAME=postgres
   DATABASE_PASSWORD=your_password
   JWT_SECRET=your-super-secret-jwt-key-min-32-characters
   ```

3. Build and run:
   ```bash
   ./mvnw clean package
   java -jar target/pos-backend-*.jar
   ```

## Configuration

The application uses environment variables for configuration. See `.env.example` for all available options.

### Required Environment Variables

- `DATABASE_URL` - PostgreSQL connection URL
- `DATABASE_USERNAME` - Database username
- `DATABASE_PASSWORD` - Database password
- `JWT_SECRET` - Secret key for JWT tokens (minimum 32 characters)

### Optional Environment Variables

- `PORT` - Server port (default: 8080)
- `CORS_ALLOWED_ORIGINS` - Comma-separated list of allowed CORS origins
- `DDL_AUTO` - Hibernate DDL mode (default: update)
- `SHOW_SQL` - Show SQL queries in logs (default: false)

## Cloud Platform Deployment

This backend is ready to deploy on:
- **Render** - See `DEPLOYMENT_PLATFORMS.md` for detailed instructions
- **Railway** - See `DEPLOYMENT_PLATFORMS.md` for detailed instructions  
- **Vercel** - See `DEPLOYMENT_PLATFORMS.md` for detailed instructions

### Quick Platform Setup

**Render:**
- Uses `render.yaml` for configuration
- Set environment variables in Render dashboard
- Build command: `./mvnw clean package -DskipTests`
- Start command: `java -jar target/pos-backend-*.jar`

**Railway:**
- Uses `railway.json` for configuration
- Automatically detects Java and builds
- Link PostgreSQL service for database connection

**Vercel:**
- Uses `vercel.json` for configuration
- Requires external PostgreSQL database
- May have limitations for long-running Spring Boot apps

## Docker Deployment

### Build Docker Image
```bash
docker build -t medical-store-pos-backend .
```

### Run Docker Container
```bash
docker run -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=jdbc:postgresql://host:port/database \
  -e SPRING_DATASOURCE_USERNAME=username \
  -e SPRING_DATASOURCE_PASSWORD=password \
  medical-store-pos-backend
```

## Environment Variables

You can override configuration using environment variables:
- `SPRING_DATASOURCE_URL`
- `SPRING_DATASOURCE_USERNAME`
- `SPRING_DATASOURCE_PASSWORD`
- `SPRING_SECURITY_JWT_SECRET`
- `SERVER_PORT`

## Database Setup

1. Create a PostgreSQL database
2. The application will automatically create tables on first run (if JPA auto-ddl is enabled)
3. Default users will be created by `DataInitializer`:
   - Admin: `admin` / `password123`
   - Other roles with default password: `password123`

## Security Notes

- Change default JWT secret in production
- Change default user passwords immediately after first deployment
- Use strong database passwords
- Enable HTTPS in production
- Configure CORS properly for your frontend domain

## API Documentation

Once running, access Swagger UI at:
```
http://localhost:8080/swagger-ui.html
```

## Notes

- `target` folder is excluded from this deployment package
- Run `mvn clean package` to build before deployment
- Ensure PostgreSQL is running and accessible
- Configure firewall rules to allow connections to the application port

