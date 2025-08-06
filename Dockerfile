FROM openjdk:21-jdk-slim

COPY target/sumanback.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active=testing"]