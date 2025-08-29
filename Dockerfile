FROM openjdk:21-jdk-slim

COPY ./Su-Man-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active=production"]