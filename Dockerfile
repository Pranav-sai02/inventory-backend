# Use a JDK image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy Maven/Gradle project and build it
COPY . .

# Build the Spring Boot app (only if you don't already build it separately)
RUN ./mvnw clean package -DskipTests

# Expose port
EXPOSE 8080

# Run the JAR file
CMD ["java", "-jar", "target/your-app-name.jar"]
