FROM eclipse-temurin:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} identidade-acesso.jar
EXPOSE 8080 
EXPOSE 5005
ENTRYPOINT ["java", "-jar", "identidade-acesso.jar"]
