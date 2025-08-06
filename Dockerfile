FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
COPY target/identidade-acesso-0.0.1-SNAPSHOT.jar sistemagestaomanutencaoindustrial.jar
EXPOSE 5005
ENTRYPOINT exec java $JAVA_OPTS -jar sistemagestaomanutencaoindustrial.jar
