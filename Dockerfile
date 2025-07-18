# Use official Maven image to build the app
FROM maven:3.9.4-eclipse-temurin-17 as builder

# Set working directory
WORKDIR /app

# Copy everything into the container
COPY . .

# Build the app (skip tests for faster builds)
RUN mvn clean package -DskipTests

# ----

# Use a lightweight JDK image to run the app
FROM eclipse-temurin:17-jdk-jammy

# Set working directory
WORKDIR /app

# Copy the built JAR from the builder image
COPY --from=builder /app/target/*.jar app.jar

# Expose port (Render will inject PORT variable)
EXPOSE 8080

# Run the app
CMD ["java", "-jar", "app.jar"]
