# Stage 1: Build the JAR using Gradle
FROM gradle:8.3.3-jdk21 AS build
WORKDIR /app

# Copy Gradle wrapper and build files
COPY gradle gradle
COPY gradlew .
COPY build.gradle .
COPY settings.gradle .

# Copy source code
COPY src src

# Build the fat JAR
RUN ./gradlew clean bootJar --no-daemon

# Stage 2: Run the app
FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app

# Copy JAR from build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Expose default Spring Boot port
EXPOSE 8080

# Run the app
ENTRYPOINT ["java","-jar","app.jar"]