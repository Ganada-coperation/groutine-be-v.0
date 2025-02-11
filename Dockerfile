FROM openjdk:17-jdk-slim
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} groutine-v0.jar
ENTRYPOINT ["java","-jar","/groutine-v0.jar"]