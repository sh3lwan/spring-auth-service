# 🔐 Authentication Service (Spring Boot)

This is a **JWT-based authentication service** built with **Spring Boot**.  
It handles **user authentication, role-based access control (RBAC), and token validation**.  
Designed to work in a **microservices architecture**, supporting **Redis caching** for permissions.

---

## 🚀 Features
✅ **JWT Authentication** – Generates access & refresh tokens.  
✅ **Microservice Ready** – Can authenticate users for **Go services** or any other microservices.  
✅ **Dockerized Deployment** – Easily deployable using **Docker Compose**.
---

## 📜 **API Endpoints**
### 🔹 **Authentication**
| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/auth/register` | Register a new user |
| `POST` | `/api/auth/login` | Authenticate user & get JWT |

### 🔹 **Protected Routes**
| Method | Endpoint               |
|--------|------------------------|
| `GET`  | `/api/users`           |
---

## ⚙️ **Setup & Installation**
### **1️⃣ Clone the Repository**
```sh
git clone https://github.com/your-username/auth-service.git
cd auth-service
```

### **2️⃣ Configure Environment Variables**
Rename **`application.example.yml`** → **`application.yml`**  
Edit the **JWT secret key**, database, and Redis settings:
```yaml
spring:
  security:
    jwt:
      secretKey: 'your-jwt-secret'
      expirationTime: '36000'
  datasource:
    url: jdbc:postgresql://postgres:5432/authdb
    username: root
    password: password
  jpa:
    hibernate:
      ddl-auto: update
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
```

### **3️⃣ Run with Docker**
```sh
docker-compose up --build
```
---

## 🛠 **Docker Compose Setup**
A complete **Docker Compose** setup is included to run:
- 🚀 `auth-service` → **Spring Boot Authentication API**
- 🛢️ `postgres-db` → **PostgreSQL Database**
---

## 🔑 **Authentication Workflow**
### **1️⃣ User Login**
Request:
```sh
curl -X POST http://localhost:8080/api/auth/login \
     -H "Content-Type: application/json" \
     -d '{"username":"testuser", "password":"password123"}'
```
Response:
```json
{
  "token": "eyJhbGciOiJIUz...",
  "message": "logged"
}
```

### **2️⃣ Access Protected Route**
```sh
curl -X GET http://localhost:8080/api/users \
     -H "Authorization: Bearer YOUR_ACCESS_TOKEN"
--
```
---

## 🛠 **Technologies Used**
- **Spring Boot 3.0**
- **Spring Security**
- **JWT (JSON Web Tokens)**
- **PostgreSQL**
- **Docker & Docker Compose**
---

## 📜 **License**
This project is **MIT Licensed**. Feel free to use and modify it.

---

## 🤝 **Contributions**
- Fork & create a **pull request**.
- Open an **issue** if you find bugs or have suggestions.
- Improve documentation! 🚀
