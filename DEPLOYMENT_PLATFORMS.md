# Platform-Specific Deployment Guides

This document provides step-by-step instructions for deploying the Medical Store POS Backend to various cloud platforms.

## Table of Contents
- [Render](#render)
- [Railway](#railway)
- [Vercel](#vercel)
- [Environment Variables](#environment-variables)

---

## Render

### Prerequisites
- Render account
- PostgreSQL database (Render provides managed PostgreSQL)

### Deployment Steps

1. **Create a New Web Service**
   - Go to [Render Dashboard](https://dashboard.render.com)
   - Click "New +" → "Web Service"
   - Connect your Git repository or upload the `Backend_DeployReady` folder

2. **Configure Build Settings**
   - **Name**: `medical-store-pos-backend`
   - **Environment**: `Java`
   - **Build Command**: `./mvnw clean package -DskipTests`
   - **Start Command**: `java -jar target/pos-backend-*.jar`

3. **Set Environment Variables**
   Go to Environment tab and add:
   ```
   DATABASE_URL=jdbc:postgresql://your-render-db-host:5432/your-db-name
   DATABASE_USERNAME=your-db-username
   DATABASE_PASSWORD=your-db-password
   JWT_SECRET=your-super-secret-jwt-key-min-32-characters
   PORT=8080
   SPRING_PROFILES_ACTIVE=production
   DDL_AUTO=update
   SHOW_SQL=false
   CORS_ALLOWED_ORIGINS=https://your-frontend-domain.com
   ```

4. **Create PostgreSQL Database**
   - Click "New +" → "PostgreSQL"
   - Note the connection details
   - Update `DATABASE_URL`, `DATABASE_USERNAME`, and `DATABASE_PASSWORD` in your service

5. **Deploy**
   - Click "Create Web Service"
   - Render will build and deploy automatically

### Render-Specific Notes
- Render automatically provides a `PORT` environment variable
- Use the `render.yaml` file for infrastructure-as-code deployment
- Database connection string format: `jdbc:postgresql://host:port/database`

---

## Railway

### Prerequisites
- Railway account
- Railway CLI (optional)

### Deployment Steps

1. **Create a New Project**
   - Go to [Railway Dashboard](https://railway.app)
   - Click "New Project"
   - Select "Deploy from GitHub repo" or "Empty Project"

2. **Add PostgreSQL Database**
   - Click "+ New" → "Database" → "PostgreSQL"
   - Railway will automatically create connection variables

3. **Deploy Application**
   - Click "+ New" → "GitHub Repo" (or upload code)
   - Select your repository with `Backend_DeployReady` folder
   - Railway will auto-detect Java and use `railway.json`

4. **Configure Environment Variables**
   Railway automatically provides:
   - `DATABASE_URL` (from PostgreSQL service)
   - `DATABASE_USERNAME`
   - `DATABASE_PASSWORD`
   
   Add manually:
   ```
   JWT_SECRET=your-super-secret-jwt-key-min-32-characters
   SPRING_PROFILES_ACTIVE=production
   DDL_AUTO=update
   SHOW_SQL=false
   CORS_ALLOWED_ORIGINS=https://your-frontend-domain.com
   ```

5. **Deploy**
   - Railway will automatically build and deploy
   - Check the "Deployments" tab for build logs

### Railway-Specific Notes
- Railway automatically injects database credentials
- The `railway.json` file configures build and start commands
- Use Railway's service linking for database connection

---

## Vercel

### Prerequisites
- Vercel account
- External PostgreSQL database (Vercel doesn't provide managed PostgreSQL)

### Deployment Steps

1. **Install Vercel CLI** (optional)
   ```bash
   npm i -g vercel
   ```

2. **Deploy via Dashboard**
   - Go to [Vercel Dashboard](https://vercel.com)
   - Click "Add New" → "Project"
   - Import your Git repository
   - Select the `Backend_DeployReady` folder as root

3. **Configure Build Settings**
   - **Framework Preset**: Other
   - **Build Command**: `./mvnw clean package -DskipTests`
   - **Output Directory**: `target`
   - **Install Command**: (leave empty)

4. **Set Environment Variables**
   Go to Settings → Environment Variables:
   ```
   DATABASE_URL=jdbc:postgresql://your-db-host:5432/your-db-name
   DATABASE_USERNAME=your-db-username
   DATABASE_PASSWORD=your-db-password
   JWT_SECRET=your-super-secret-jwt-key-min-32-characters
   PORT=8080
   SPRING_PROFILES_ACTIVE=production
   DDL_AUTO=update
   SHOW_SQL=false
   CORS_ALLOWED_ORIGINS=https://your-frontend-domain.com
   ```

5. **Deploy**
   - Click "Deploy"
   - Vercel will build and deploy your application

### Vercel-Specific Notes
- Vercel uses serverless functions, which may have limitations for long-running Spring Boot apps
- Consider using Vercel for API routes only
- For full Spring Boot apps, Render or Railway are recommended

---

## Environment Variables

### Required Variables

| Variable | Description | Example |
|----------|-------------|---------|
| `DATABASE_URL` | PostgreSQL connection URL | `jdbc:postgresql://host:5432/dbname` |
| `DATABASE_USERNAME` | Database username | `postgres` |
| `DATABASE_PASSWORD` | Database password | `your_password` |
| `JWT_SECRET` | Secret key for JWT tokens (min 32 chars) | `your-super-secret-key-here` |

### Optional Variables

| Variable | Description | Default |
|----------|-------------|---------|
| `PORT` | Server port | `8080` |
| `DATABASE_POOL_SIZE` | Connection pool max size | `20` |
| `DATABASE_MIN_IDLE` | Minimum idle connections | `5` |
| `DATABASE_CONNECTION_TIMEOUT` | Connection timeout (ms) | `30000` |
| `JWT_EXPIRATION` | JWT token expiration (ms) | `86400000` (24 hours) |
| `DDL_AUTO` | Hibernate DDL mode | `update` |
| `SHOW_SQL` | Show SQL queries in logs | `false` |
| `SPRING_PROFILES_ACTIVE` | Active Spring profile | `production` |
| `CORS_ALLOWED_ORIGINS` | Allowed CORS origins (comma-separated) | `http://localhost:4200` |

### Generating JWT Secret

Generate a secure JWT secret (minimum 32 characters):

**Using OpenSSL:**
```bash
openssl rand -base64 32
```

**Using Node.js:**
```bash
node -e "console.log(require('crypto').randomBytes(32).toString('base64'))"
```

**Using Python:**
```bash
python -c "import secrets; print(secrets.token_urlsafe(32))"
```

---

## Post-Deployment Checklist

- [ ] Verify database connection
- [ ] Test API endpoints
- [ ] Update frontend API URL
- [ ] Configure CORS for your frontend domain
- [ ] Change default admin password
- [ ] Enable HTTPS/SSL
- [ ] Set up monitoring/logging
- [ ] Configure backup strategy for database
- [ ] Review security settings
- [ ] Test authentication flow

---

## Troubleshooting

### Database Connection Issues
- Verify database credentials
- Check database firewall/security groups
- Ensure database is accessible from deployment platform
- Verify connection string format

### Build Failures
- Check Java version (requires Java 17+)
- Verify Maven wrapper permissions (`chmod +x mvnw`)
- Review build logs for specific errors

### CORS Issues
- Update `CORS_ALLOWED_ORIGINS` with your frontend URL
- Ensure no trailing slashes in URLs
- Check browser console for CORS errors

### Port Issues
- Most platforms provide `PORT` automatically
- Verify your application reads from `PORT` environment variable
- Check platform-specific port requirements

---

## Support

For platform-specific issues, refer to:
- [Render Documentation](https://render.com/docs)
- [Railway Documentation](https://docs.railway.app)
- [Vercel Documentation](https://vercel.com/docs)

