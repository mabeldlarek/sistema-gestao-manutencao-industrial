# ---------- STAGE 1: BUILD ----------
FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /app
COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# ---------- STAGE 2: RUNTIME ----------
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# copia o jar gerado na etapa build
COPY --from=build /app/target/*.jar app.jar

ENV JAVA_TOOL_OPTIONS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005"

EXPOSE 8080
EXPOSE 5005

ENTRYPOINT ["java", "-jar", "app.jar"]
