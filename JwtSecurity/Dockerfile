FROM maven:3.8.2-jdk-11
RUN mvn clean install package

FROM openjdk:11
WORKDIR /app
COPY /target/JwtSecurity-0.0.1-SNAPSHOT.jar .
CMD ["java", "-jar", "JwtSecurity-0.0.1-SNAPSHOT.jar"]

EXPOSE 8080