# HELP.md

## Backend for Mobile Games

This repository contains a Spring Boot backend designed to support iOS and Android games by providing authentication, security, and other essential services.

## Technology Stack

- **Spring Boot 3.4.2**
- **Database:** MySQL
- **Dependencies:**
  - spring-boot-starter-data-jpa
  - spring-boot-starter-security
  - spring-boot-starter-validation
  - spring-boot-starter-web
  - mysql-connector-j
  - lombok
  - spring-boot-starter-tomcat
  - spring-boot-starter-test
  - spring-security-test
  - jjwt-api, jjwt-impl, jjwt-jackson (for JWT authentication)
  - spring-boot-starter-mail
  - springdoc-openapi-starter-webmvc-ui (for API documentation)
  - google-auth-library-oauth2-http
  - google-api-client

## API

The backend exposes a REST API with the following endpoints:

### Whitelisted Endpoints (Public)
- `POST /api/auth/register` - Register a new user
- `POST /api/auth/login` - Login and receive a JWT token
- `GET /api/auth/test` - Test endpoint
- `POST /api/auth/forgot-password` - Request password reset
- `POST /api/auth/reset-password` - Reset password
- `POST /api/auth/refresh-token` - Get a new JWT token

### Secured Endpoints (Require Authentication)
- `GET /api/auth/dummySecure` - Example secured endpoint

## Environment Prerequisites

### System Software
- **Java JRE** (compatible with Spring Boot 3.4.2)
- **Apache Tomcat** (if running outside embedded Tomcat)

### Database
- MySQL Database with name: `LootGeneratorDB`

### Required Environment Variables
Ensure the following environment variables are set:

- `DB_USERNAME` - MySQL database username
- `DB_PASSWORD` - MySQL database password
- `MAIL_SERVICE_KEY` - API key for email service
- `JWT_KEY` - Secret key for JWT authentication
- `GOOGLE_CLIENT_ID` - OAuth Client ID for Google authentication

## Getting Started

To be updated with setup instructions...

## Contributing

To be updated with contribution guidelines...

## Deployment

To be updated with deployment details...

---
This document is a work in progress and will be updated as more details become available.

