# Base JDK 17
FROM eclipse-temurin:17-jdk-alpine

# Variável para o jar gerado pelo Maven
ARG JAR_FILE=target/*.jar

# Copia o jar para dentro do container
COPY ${JAR_FILE} /identidade-acesso.jar

# Expõe porta da aplicação e porta do debug
EXPOSE 8080 5005

# Inicia a aplicação (não adiciona JDWP aqui, será via docker-compose)
ENTRYPOINT ["java", "-jar", "/identidade-acesso.jar"]