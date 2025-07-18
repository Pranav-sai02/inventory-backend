# ✅ Use official OpenJDK 17 image
FROM openjdk:17-jdk-slim

# ✅ Set working directory
WORKDIR /app

# ✅ Copy necessary files first
COPY .mvn .mvn
COPY mvnw pom.xml ./

# ✅ Make mvnw executable (this line fixes your issue)
RUN chmod +x mvnw

# ✅ Preload dependencies
RUN ./mvnw dependency:go-offline -B

# ✅ Copy full project
COPY . .

# ✅ Build the Spring Boot app
RUN ./mvnw clean package -DskipTests

# ✅ Expose port
EXPOSE 8080

# ✅ Run the app
CMD ["java", "-jar", "target/avoota_inventory-0.0.1-SNAPSHOT.jar"]

