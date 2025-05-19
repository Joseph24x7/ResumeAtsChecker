# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR file into the container
COPY target/resumeatschecker.jar app.jar

# Expose the port the application runs on
EXPOSE 8001

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]