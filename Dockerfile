# Stage 1: Build the application's JAR file
FROM eclipse-temurin:21-jdk-jammy as builder
WORKDIR /app

COPY gradlew ./
COPY gradle ./gradle
COPY build.gradle.kts settings.gradle.kts ./

# Download dependencies first to leverage Docker layer caching
RUN ./gradlew dependencies

# Copy source code and build the application
COPY src ./src
RUN ./gradlew build -x test --no-daemon

# Stage 2: Create the final, smaller image to run the application
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]