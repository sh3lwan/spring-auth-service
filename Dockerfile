# ---- Stage 1: Cache Dependencies ----
FROM maven:3.9.5-eclipse-temurin-17 AS dependencies

WORKDIR /app

# Copy pom.xml and download dependencies only (no src code yet, to leverage cache)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# ---- Stage 2: Build the Application ----
FROM maven:3.9.5-eclipse-temurin-17 AS builder

WORKDIR /app

# Copy cached dependencies
COPY --from=dependencies /root/.m2 /root/.m2

# Copy actual source code
COPY . .

# Package the application (skip tests for faster builds)
RUN mvn clean package -DskipTests

# ---- Stage 3: Run the Application ----
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy JAR file from the builder stage
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

# Start the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]