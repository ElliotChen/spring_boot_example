FROM openjdk:14-jdk-alpine
VOLUME /tmp
EXPOSE 8080

ARG JAR_FILE
ADD ${JAR_FILE} springapm.jar
ENTRYPOINT ["java", "-jar", "springapm.jar"]