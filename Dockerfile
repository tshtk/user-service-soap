FROM maven:3.8-openjdk-8-slim AS build
WORKDIR /app/build
COPY . .
RUN mvn -B package

FROM openjdk:8
WORKDIR /app/bin
COPY --from=build /app/build/target/user-service.jar app.jar
CMD ["java", "-jar", "app.jar"]