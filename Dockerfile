# Use OpenJDK 21 base image (matches your Java version)
FROM eclipse-temurin:21-jdk-jammy

# Set environment variables (optional)
ENV JAVA_OPTS=""

# Set working directory in container
WORKDIR /app

# Copy the Spring Boot fat JAR into the container
COPY target/myapp.jar app.jar

# Expose the port your app runs on (default 8080)
EXPOSE 8080

# Run the Spring Boot app
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]