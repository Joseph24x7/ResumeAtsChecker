# ------------ Stage 1: Build ------------
FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /app

# Install Node.js and npm
RUN apt-get update && apt-get install -y curl && \
    curl -fsSL https://deb.nodesource.com/setup_18.x | bash - && \
    apt-get install -y nodejs && \
    npm --version && node --version

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source code
COPY src ./src

# Copy the frontend directory
COPY frontend ./frontend

# Install frontend dependencies
RUN cd frontend && npm install

# Install bootstrap
RUN cd frontend && npm install bootstrap

# Build the application
RUN mvn clean package -DskipTests

# ------------ Stage 2: Run ------------
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy the jar from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose the port
EXPOSE 8080

# Run the app
CMD ["java", "-jar", "app.jar"]