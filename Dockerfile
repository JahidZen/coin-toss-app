# Stage 1: Build JAR
FROM eclipse-temurin:21-jdk-jammy AS builder
WORKDIR /app

# Copy only what’s needed for dependency download
COPY gradlew build.gradle.kts settings.gradle.kts ./
COPY gradle ./gradle
RUN ./gradlew build -x test --no-daemon || true

# Copy the rest later
COPY src ./src
RUN ./gradlew build -x test --no-daemon

# Stage 2: Run
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
