FROM openjdk:11-jdk-slim
VOLUME /tmp
EXPOSE 3001
ARG JAR_FILE=build/libs/demo-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]