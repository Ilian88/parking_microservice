FROM gradle:8.5.0-jdk21-alpine AS builder

WORKDIR /app

COPY gradlew .
COPY gradle gradle
COPY build.gradle.kts settings.gradle.kts .
COPY src ./src

RUN chmod +x gradlew

RUN ./gradlew bootJar -x test

# -------------------------------------------

FROM eclipse-temurin:21-jre-alpine

ARG JAR_FILE=build/libs/*.jar

COPY --from=builder /app/${JAR_FILE} app.jar

USER nobody

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

