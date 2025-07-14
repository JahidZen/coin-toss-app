# Stage 1: Build the application's JAR file using JDK 21
FROM eclipse-temurin:21-jdk-jammy as builder
WORKDIR /app

# Copy Gradle wrapper and build files
COPY gradlew build.gradle settings.gradle ./
COPY gradle ./gradle

# Download dependencies (makes rebuilds faster)
RUN ./gradlew dependencies

# Copy the source code
COPY src ./src

# Build the app (skip tests for faster build)
RUN ./gradlew build -x test

# Stage 2: Create the final, smaller image to run the application with JRE 21
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Copy the built JAR from the previous stage
COPY --from=builder /app/build/libs/*.jar app.jar

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
