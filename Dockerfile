# ✅ Use official OpenJDK 17 image
FROM openjdk:17-jdk-slim

# ✅ Set working directory
WORKDIR /app

# ✅ Copy only necessary files first to leverage Docker cache
COPY .mvn .mvn
COPY mvnw pom.xml ./

# ✅ Give execute permission to mvnw
RUN chmod +x mvnw

# ✅ Preload dependencies (caching layer)
RUN ./mvnw dependency:go-offline -B

# ✅ Now copy the full source code
COPY . .

# ✅ Build the Spring Boot app
RUN ./mvnw clean package -DskipTests

# ✅ Expose port 8080
EXPOSE 8080

# ✅ Run the fat JAR
CMD ["java", "-jar", "target/avoota_inventory-0.0.1-SNAPSHOT.jar"]
