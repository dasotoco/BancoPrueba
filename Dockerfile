FROM openjdk:17-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=build/libs/BancoPrueba-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} BancoPrueba.jar
ENTRYPOINT ["java","-jar","/BancoPrueba.jar"]
EXPOSE 8080/tcp
