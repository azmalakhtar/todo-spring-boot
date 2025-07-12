# Stage 1: Build the app
FROM maven:3.9.6-eclipse-temurin-17 as builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Run the app
FROM openjdk:17
COPY --from=builder /app/target/todo.jar todo.jar
ENTRYPOINT ["java", "-jar", "/todo.jar"]

