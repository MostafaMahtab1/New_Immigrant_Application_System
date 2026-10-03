# Phase 1: Build the artifact using Maven and an ARM64-friendly JDK image
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
# Compile code and run your 11 unit tests
RUN mvn clean package -DskipTests=false

# Phase 2: Create a minimal, lightweight runtime image using active Eclipse Temurin images
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
# Grabs the compiled .jar file from Phase 1
COPY --from=build /app/target/immigrant-app-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
