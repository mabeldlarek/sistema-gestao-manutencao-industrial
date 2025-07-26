FROM eclipse-temurin:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} identidade-acesso.jar
ENTRYPOINT ["java","-jar","/identidade-acesso.jar"]
