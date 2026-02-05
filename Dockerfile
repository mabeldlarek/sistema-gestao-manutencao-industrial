# ---------- STAGE 1: BUILD ----------
FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /app
COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# ---------- STAGE 2: RUNTIME ----------
FROM eclipse-temurin:17-jdk-alpine

ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS

WORKDIR /app

# copia o jar gerado no stage de build
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
EXPOSE 5005

ENTRYPOINT exec java $JAVA_OPTS -jar app.jar
