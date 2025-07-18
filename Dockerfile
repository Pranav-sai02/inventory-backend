# ✅ Use official OpenJDK 17 image
FROM openjdk:17-jdk-slim

# ✅ Set working directory
WORKDIR /app

# ✅ Copy Maven wrapper and POM separately for caching
COPY .mvn .mvn
COPY mvnw pom.xml ./

# ✅ Give permission to mvnw
RUN chmod +x mvnw

# ✅ Preload dependencies (caching)
RUN ./mvnw dependency:go-offline -B

# ✅ Copy full project AFTER caching dependencies
COPY . .

# ✅ Make sure mvnw is still executable after full copy
RUN chmod +x mvnw  # 🔥 THIS is what you're missing

# ✅ Build app
RUN ./mvnw clean package -DskipTests

# ✅ Expose port
EXPOSE 8080

# ✅ Run the JAR
CMD ["java", "-jar", "target/avoota_inventory-0.0.1-SNAPSHOT.jar"]
